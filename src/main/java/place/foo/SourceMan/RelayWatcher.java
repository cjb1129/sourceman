package main.java.place.foo.SourceMan;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.StandardWatchEventKinds.*;

public class RelayWatcher implements Runnable {

    private WatchService watchService;
    private final Pattern pattern;
    private final String path;

    private HangmanGame game;
    private Config config;

    public RelayWatcher(String path) {
        this.pattern = Pattern.compile("bind \"RALT\" \"([^\"]*)\"");
        this.path = path;
    }

    public void setGame(HangmanGame game) {
        this.game = game;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public void run() {
        try {
            watchService = FileSystems.getDefault().newWatchService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path cfgPath = Paths.get(path);
        try {
            cfgPath.register(watchService, ENTRY_CREATE, ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean poll = true;
        while (poll) {
            WatchKey key;
            try {
                key = watchService.take();
            } catch (ClosedWatchServiceException | InterruptedException  e) {
                continue;
            }
            if (key != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.context().toString().equals("sourceman_relay.cfg")) {
                        File relayCfg = new File(cfgPath + "\\sourceman_relay.cfg");
                        if (relayCfg.exists()) {
                            List<String> relayList = null;
                            try {
                                relayList = Files.readAllLines(Paths.get(cfgPath + "\\sourceman_relay.cfg"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (relayList != null) {
                                for (String s : relayList) {
                                    Matcher m = pattern.matcher(s);
                                    while (m.find()) {
                                        String match = m.group(1).replaceAll("[^A-Za-z0-9]","");
                                        if (match.length() == 1) {
                                            if (game.getLives() > 0) {
                                                game.guessLetter(match.toLowerCase().charAt(0));
                                            }
                                        } else if (match.equals("reset")) {
                                            game.restart();
                                        }
                                        config.writeSourceManRenderCFG(game);
                                    }
                                }
                            }
                            if (!relayCfg.delete()) {
                                System.out.println("Error deleting relay file.");
                            }
                        }
                    }
                }
            }
            if (key != null) {
                poll = key.reset();
            }
        }
    }

    public void kill() throws IOException {
        watchService.close();
    }
}
