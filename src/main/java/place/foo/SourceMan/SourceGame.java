package main.java.place.foo.SourceMan;

public class SourceGame {

    private String name;
    private int id;
    private String directory;
    private String libraryName;

    public SourceGame() {}

    public SourceGame(String name, int id, String directory, String libraryName) {
        this.name = name;
        this.id = id;
        this.directory = directory;
        this.libraryName = libraryName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String toString() {
        return getName();
    }
}
