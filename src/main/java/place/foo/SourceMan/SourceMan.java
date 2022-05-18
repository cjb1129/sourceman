package main.java.place.foo.SourceMan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SourceMan {

    /**
     * Program name
     */
    public static final String NAME = "SourceMan";

    /**
     * Program version
     */
    public static final String VERSION = "1.0.0";

    private List<SourceGame> games;

    Config config;
    SourceGame currentGame;
    RelayWatcher relayWatcher;
    Thread watcherThread;

    public static void main(String[] args) {
        new GUI(NAME + " " + VERSION);
    }

    public SourceMan() {
        games = new ArrayList<>();
        //loadGames();

        //config.removeSourceManCFG();

        HangmanGame game = new HangmanGame();

        config = new Config("D:\\Steam\\steamapps\\common\\Counter-Strike Global Offensive\\csgo\\cfg");

        config.writeSourceManCFG();
        config.writeSourceManRenderCFG(game);
        config.writeDelayCFG();

        relayWatcher = new RelayWatcher("C:\\Program Files (x86)\\Steam\\userdata\\120016282\\730\\local\\cfg");
        relayWatcher.setGame(game);
        relayWatcher.setConfig(config);

        watcherThread = new Thread(relayWatcher);
        watcherThread.start();
    }

    /**
     * Finds source games from the computers task list
     * @throws IOException if tasklist.exe cannot be located
     */
    @Deprecated
    public void findCurrentGame() throws IOException {
        String line;
        StringBuilder pidInfo = new StringBuilder();

        Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");

        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

        while ((line = input.readLine()) != null) {
            pidInfo.append(line);
        }
        input.close();
        System.out.println(pidInfo);
        if(pidInfo.toString().contains("csgo.exe"))
        {
            currentGame = games.get(0);
        } else {
            System.out.println("No source games running.");
        }
    }

    /**
     * Kills the relay watcher thread, and removes the SourceMan config files
     * @throws IOException if the config files have already been removed, or if they cannot be located
     */
    public void kill() throws IOException {
        if (!watcherThread.isInterrupted()) {
            watcherThread.interrupt();
            relayWatcher.kill();
        }
        config.removeSourceManCFG();
        config.removeSourceManRenderCFG();
        config.removeDelayCFG();
    }

    public void loadGames() {
        SourceGame csgo = new SourceGame();
        csgo.setName("Counter-Strike: Global Offensive");
        csgo.setId(730);
        csgo.setDirectory("common\\Counter-Strike Global Offensive\\");
        csgo.setLibraryName("csgo");
        games.add(csgo);
    }
}
