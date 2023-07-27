import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class MusicPlayer {
    private AdvancedPlayer player;
    private boolean isPaused;
    private String currentSongFilePath;

    public void play(String filePath) {
        try {
            isPaused = false;
            currentSongFilePath = filePath;
            InputStream fis = new FileInputStream(filePath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new AdvancedPlayer(bis);
            new Thread(() -> {
                try {
                    player.play();
                } catch (Exception e) {
                    // Handle exceptions
                    e.printStackTrace();
                }
            }).start();
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    public void pause() {
        if (player != null) {
            if (!isPaused) {
                try {
                    player.stop();
                } catch (Exception e) {
                    // Handle exceptions
                }
                isPaused = true;
            } else {
                // Resume playback if paused
                playCurrentSong();
            }
        }
    }

    public void stop() {
        if (player != null) {
            player.stop();
            isPaused = false;
        }
    }

    private void playCurrentSong() {
        // Resume playback from where it was paused
        new Thread(() -> {
            try {
                InputStream fis = new FileInputStream(currentSongFilePath);
                BufferedInputStream bis = new BufferedInputStream(fis);
                player = new AdvancedPlayer(bis);
                player.play();
            } catch (Exception e) {
                // Handle exceptions
            }
        }).start();
    }
}
