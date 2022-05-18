package main.java.place.foo.SourceMan;

import java.io.*;

public class Config {

    private final String cfgPath;

    public Config(String cfgPath) {
        this.cfgPath = cfgPath;
    }

    public void writeDelayCFG() {
        try {
            FileWriter delayCFG = new FileWriter(cfgPath + "\\delay.cfg");
            for (int i=0;i<1000;i++) {
                delayCFG.write("echo \"\";\n");
            }
            delayCFG.close();
            System.out.println("delay.cfg written");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeDelayCFG() {
        File delayCFG = new File(cfgPath + "\\delay.cfg");
        if (delayCFG.exists()) {
            if (delayCFG.delete()) {
                System.out.println("Removed delay.cfg successfully");
            } else {
                System.out.println("Could not remove delay.cfg");
            }
        }
    }

    public void writeSourceManCFG() {
        try {
            FileWriter sourceManCFG = new FileWriter(cfgPath + "\\sourceman.cfg");
            sourceManCFG.write("alias sourceman_rcfg \"host_writeconfig cfg/sourceman_relay.cfg;exec delay;clear;exec sourceman_render\"\n");
            sourceManCFG.write("alias reset \"bind ralt reset; sourceman_rcfg\"\n");
            sourceManCFG.write("alias a \"bind ralt a; sourceman_rcfg\"\n");
            sourceManCFG.write("alias b \"bind ralt b; sourceman_rcfg\"\n");
            sourceManCFG.write("alias c \"bind ralt c; sourceman_rcfg\"\n");
            sourceManCFG.write("alias d \"bind ralt d; sourceman_rcfg\"\n");
            sourceManCFG.write("alias e \"bind ralt e; sourceman_rcfg\"\n");
            sourceManCFG.write("alias f \"bind ralt f; sourceman_rcfg\"\n");
            sourceManCFG.write("alias g \"bind ralt g; sourceman_rcfg\"\n");
            sourceManCFG.write("alias h \"bind ralt h; sourceman_rcfg\"\n");
            sourceManCFG.write("alias i \"bind ralt i; sourceman_rcfg\"\n");
            sourceManCFG.write("alias j \"bind ralt j; sourceman_rcfg\"\n");
            sourceManCFG.write("alias k \"bind ralt k; sourceman_rcfg\"\n");
            sourceManCFG.write("alias l \"bind ralt l; sourceman_rcfg\"\n");
            sourceManCFG.write("alias m \"bind ralt m; sourceman_rcfg\"\n");
            sourceManCFG.write("alias n \"bind ralt n; sourceman_rcfg\"\n");
            sourceManCFG.write("alias o \"bind ralt o; sourceman_rcfg\"\n");
            sourceManCFG.write("alias p \"bind ralt p; sourceman_rcfg\"\n");
            sourceManCFG.write("alias q \"bind ralt q; sourceman_rcfg\"\n");
            sourceManCFG.write("alias r \"bind ralt r; sourceman_rcfg\"\n");
            sourceManCFG.write("alias s \"bind ralt s; sourceman_rcfg\"\n");
            sourceManCFG.write("alias t \"bind ralt t; sourceman_rcfg\"\n");
            sourceManCFG.write("alias u \"bind ralt u; sourceman_rcfg\"\n");
            sourceManCFG.write("alias v \"bind ralt v; sourceman_rcfg\"\n");
            sourceManCFG.write("alias w \"bind ralt w; sourceman_rcfg\"\n");
            sourceManCFG.write("alias x \"bind ralt x; sourceman_rcfg\"\n");
            sourceManCFG.write("alias y \"bind ralt y; sourceman_rcfg\"\n");
            sourceManCFG.write("alias z \"bind ralt z; sourceman_rcfg\"\n");
            sourceManCFG.write("clear\n");
            sourceManCFG.write("exec sourceman_render\n");
            sourceManCFG.close();
            System.out.println("sourceman.cfg written");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeSourceManCFG() {
        File sourceManCFG = new File(cfgPath + "\\sourceman.cfg");
        if (sourceManCFG.exists()) {
            if (sourceManCFG.delete()) {
                System.out.println("Removed sourceman.cfg successfully");
            } else {
                System.out.println("Could not remove sourceman.cfg");
            }
        }
    }

    public void writeSourceManRenderCFG(HangmanGame game) {
        try {
            FileWriter sourceManRenderCFG = new FileWriter(cfgPath + "\\sourceman_render.cfg");
            sourceManRenderCFG.write("echo \"\"\n");
            sourceManRenderCFG.write("echo \"`=======````` Lives: " + game.getLives() +"\"\n");
            sourceManRenderCFG.write("echo \"`|`````|`````\"\n");
            if (game.getLives() >= 6) {
                sourceManRenderCFG.write("echo \"`|```````````\"\n");
            } else {
                sourceManRenderCFG.write("echo \"`|`````o`````\"\n");
            }
            if (game.getLives() < 3) {
                sourceManRenderCFG.write("echo \"`|````/T\\````\"\n");
            } else if (game.getLives() == 3){
                sourceManRenderCFG.write("echo \"`|`````T\\````\"\n");
            } else if (game.getLives() == 4){
                sourceManRenderCFG.write("echo \"`|`````T`````\"\n");
            } else {
                sourceManRenderCFG.write("echo \"`|```````````\"\n");
            }
            if (game.getLives() < 1) {
                sourceManRenderCFG.write("echo \"`|````_^_````\"\n");
            } else if (game.getLives() == 1) {
                sourceManRenderCFG.write("echo \"`|`````^_````\"\n");
            } else if (game.getLives() < 5) {
                sourceManRenderCFG.write("echo \"`|`````^`````\"\n");
            } else {
                sourceManRenderCFG.write("echo \"`|```````````\"\n");
            }
            sourceManRenderCFG.write("echo \"_|_``````````\"\necho \"\"\n");
            sourceManRenderCFG.write("echo \"" + game.getObfuscatedWord() + "\"\necho \"\"\n");
            if (!game.isGameOver()) {
                sourceManRenderCFG.write("echo \"" + game.getUsedLetters() + "\"\n");
            } else {
                if (game.getLives() > 0) {
                    sourceManRenderCFG.write("echo \"You win! Type `reset` to play again.\"");
                } else {
                    sourceManRenderCFG.write("echo \"You lose! The word was " + game.getWord() + ". Type `reset` to try again.\"");
                }
            }
            sourceManRenderCFG.close();
            System.out.println("sourceman_render.cfg written");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeSourceManRenderCFG() {
        File sourceManRenderCFG = new File(cfgPath + "\\sourceman_render.cfg");
        if (sourceManRenderCFG.exists()) {
            if (sourceManRenderCFG.delete()) {
                System.out.println("Removed sourceman_render.cfg successfully");
            } else {
                System.out.println("Could not remove sourceman_render.cfg");
            }
        }
    }
}
