import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javazoom.jl.player.advanced.AdvancedPlayer;

public class MusicPlayerGUI extends JFrame {
    private JList<String> songList;
    private JTextArea lyricsTextArea;
    private JButton playButton;
    private JButton pauseButton;
    private JButton stopButton;

    private List<Song> songs;
    private MusicPlayer player;

    public MusicPlayerGUI() {
        songs = new ArrayList<>();
        player = new MusicPlayer();

        // Initialize your songs and add them to the 'songs' list here
        // For example: songs.add(new Song("Song Title", "file_path.mp3", "Lyrics go here", "image_file_path.png"));

        songList = new JList<>(getSongTitles());
        songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        songList.addListSelectionListener(e -> displaySongDetails());

        lyricsTextArea = new JTextArea(10, 30);
        lyricsTextArea.setEditable(false);

        playButton = new JButton("Play");
        playButton.addActionListener(e -> playSelectedSong());

        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> pauseSelectedSong());

        stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> stopSelectedSong());

        JPanel controlsPanel = new JPanel();
        controlsPanel.add(playButton);
        controlsPanel.add(pauseButton);
        controlsPanel.add(stopButton);

        JPanel mainPanel = new JPanel();
        mainPanel.add(new JScrollPane(songList));
        mainPanel.add(lyricsTextArea);
        mainPanel.add(controlsPanel);

        add(mainPanel);

        setTitle("Music Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String[] getSongTitles() {
        String[] titles = new String[songs.size()];
        for (int i = 0; i < songs.size(); i++) {
            titles[i] = songs.get(i).getTitle();
        }
        return titles;
    }

    private void displaySongDetails() {
        int selectedIndex = songList.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < songs.size()) {
            Song selectedSong = songs.get(selectedIndex);
            lyricsTextArea.setText(selectedSong.getLyrics());
            // You can also display the relevant image if available
            // Example: loadImage(selectedSong.getImageFilePath());
        }
    }

    private void playSelectedSong() {
        int selectedIndex = songList.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < songs.size()) {
            Song selectedSong = songs.get(selectedIndex);
            player.play(selectedSong.getFilePath());
        }
    }

    private void pauseSelectedSong() {
        player.pause();
    }

    private void stopSelectedSong() {
        player.stop();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MusicPlayerGUI::new);
    }
}
