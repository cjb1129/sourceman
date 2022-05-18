package main.java.place.foo.SourceMan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

public class HangmanGame {
    private String[] wordArray;
    private String word;

    private int lives;
    private int correct;
    private HashSet<Character> used;

    public HangmanGame() {
        try {
            loadWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initialize();
    }

    /**
     * Reinitializes the game with a new word
     */
    public void restart() {
        initialize();
    }

    /**
     * Picks a random word from wordArray, and resets lives, used letters, and guesses
     */
    public void initialize() {
        this.word = wordArray[(int)(Math.random() * wordArray.length)];
        used = new HashSet<>();
        lives = 6;
        correct = 0;
    }

    public void setConfig() {
    }

    /**
     * Guesses a letter, as long as it hasn't been guessed already
     * @param c character to guess
     */
    public void guessLetter(char c) {
        c = Character.toLowerCase(c);
        if (!used.contains(c)) {
            used.add(c);
            if (word.indexOf(c) == -1) {
                lives--;
            } else {
                correct += word.replaceAll("[^"+ c +"]", "").length();
            }
        }
    }

    /**
     *
     * @return the obfuscated word, with underscores representing unguessed letters
     */
    public String getObfuscatedWord() {
        StringBuilder obfuscated = new StringBuilder();
        for (char c : getWord().toCharArray()) {
            if (used.contains(c)) {
                obfuscated.append(c);
            } else {
                obfuscated.append("_");
            }
            obfuscated.append(" ");
        }
        return obfuscated.toString().trim();
    }

    /**
     *
     * @return if the word has been guessed, or if the player is out of lives
     */
    public boolean isGameOver() {
        if (lives < 1) {
            return true;
        } else return word.length() == correct;
    }

    /**
     *
     * @return lives remaining, if any
     */
    public int getLives() {
        return lives;
    }

    /**
     *
     * @return the word to be guessed
     */
    public String getWord() {
        return word;
    }

    /**
     *
     * @return a hashset of the current used characters
     */
    public HashSet<Character> getUsedLetters() {
        return used;
    }

    /**
     * Loads the words from the wordlist.txt file into wordArray
     * @throws IOException when the file cannot be found or cannot be loaded
     */
    public void loadWords() throws IOException {
        Path path = Paths.get("src/main/resources/wordlist.txt");
        List<String> wordList = Files.readAllLines(path);
        wordArray = wordList.toArray(new String[0]);
    }
}
