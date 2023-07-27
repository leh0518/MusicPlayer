public class Song {
    private String title;
    private String filePath;
    private String lyrics;
    private String imageFilePath;

    public Song(String title, String filePath, String lyrics, String imageFilePath) {
        this.title = title;
        this.filePath = filePath;
        this.lyrics = lyrics;
        this.imageFilePath = imageFilePath;
    }

    public String getTitle() {
        return title;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getLyrics() {
        return lyrics;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }
}
