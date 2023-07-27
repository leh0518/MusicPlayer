import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUI {
    private JFrame frame;
    private JList<String> songList;
    private JTextArea lyricsTextArea;
    private JLabel albumCoverLabel;
    private JButton playButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JButton chooseSongButton; // New button to choose a song from the list

    private List<Song> songs;
    private MusicPlayer player;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public GUI() {
        songs = new ArrayList<>();
        player = new MusicPlayer();

        // Sample Songs (Replace the placeholder file paths with actual file paths)
        addSong("ETA", "songs/NewJeansETA.mp3", getLyrics1(), "images/NewJeans-800x800.jpg");
        addSong("Song 2", "path_to_song_2.mp3", getLyrics2(), "path_to_image_2.png");
        addSong("Song 3", "path_to_song_3.mp3", getLyrics3(), "path_to_image_3.png");
        addSong("Song 4", "path_to_song_4.mp3", getLyrics4(), "path_to_image_4.png");
        addSong("Song 5", "path_to_song_5.mp3", getLyrics5(), "path_to_image_5.png");

        songList = new JList<>(getSongTitles());
        songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        songList.addListSelectionListener(e -> displaySongDetails());

        lyricsTextArea = new JTextArea(10, 30);
        lyricsTextArea.setEditable(false);

        albumCoverLabel = new JLabel();
        albumCoverLabel.setPreferredSize(new Dimension(200, 200));
        albumCoverLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        playButton = new JButton("Play");
        playButton.addActionListener(e -> playSelectedSong());

        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> pauseSelectedSong());

        stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> stopSelectedSong());

        chooseSongButton = new JButton("Choose Song");
        chooseSongButton.addActionListener(e -> chooseSong());

        JPanel controlsPanel = new JPanel();
        controlsPanel.add(playButton);
        controlsPanel.add(pauseButton);
        controlsPanel.add(stopButton);
        controlsPanel.add(chooseSongButton); // Adding the new button to the controls panel

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JScrollPane(songList), BorderLayout.CENTER);
        leftPanel.add(controlsPanel, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(lyricsTextArea, BorderLayout.CENTER);
        rightPanel.add(albumCoverLabel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        mainPanel.add(leftPanel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.5;
        mainPanel.add(rightPanel, gbc);

        frame = new JFrame("Music Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
            loadImage(selectedSong.getImageFilePath());
        }
    }

    private void loadImage(String imagePath) {
        try {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            albumCoverLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            e.printStackTrace();
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

    private void chooseSong() {
        int selectedIndex = songList.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < songs.size()) {
            Song selectedSong = songs.get(selectedIndex);
            JOptionPane.showMessageDialog(frame, "You chose the song: " + selectedSong.getTitle(), "Song Selection", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a song from the list.", "Song Selection", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Sample lyrics for the songs (Replace with actual lyrics)
    private String getLyrics1() {
        return "Nangbihaji ma ne siganeun eunhaeng\n" +
                "Seodulleoseo jeongnihae gyaeneun real bad\n" +
                "Badajumyeon andwae\n" +
                "No, you better trust me\n" +
                "Dapdapaeseo geurae\n" +
                "Jeobeonedo bwatjiman neo eopseul ttae\n" +
                "Gyaen yeogijeogie nunbicheul ppurine\n" +
                "Aju nunbusige\n" +
                "Honestly uri saie\n" +
                "He's been totally lyin', yeah\n" +
                "\n" +
                "Nae saengil patie neoman mot on geunal\n" +
                "Hyejiniga eomcheong honnatdeon geunal\n" +
                "Jiwoniga yeochinirang heeojin geunal\n" +
                "Gyaeneun eonjena nega eopsi geunal\n" +
                "Neomu meosinneun oseul ipgo geunal\n" +
                "Heard him say\n" +
                "\n" +
                "We can go wherever you like\n" +
                "Baby, say the words and I'm down\n" +
                "All I need is you on my side\n" +
                "We can go whenever you like\n" +
                "Now, where are you? (Mm-mhm)\n" +
                "What's your ETA? What's your ETA? (Mm-mhm-mm)\n" +
                "What's your ETA? What's your ETA? (Mm-mhm)\n" +
                "What's your ETA? What's your ETA?\n" +
                "I'll be there right now, lose that boy on her arm\n" +
                "\n" +
                "Apa mami nega gyae mot ijeul ttae\n" +
                "Nae mal mideo you deserve better than that\n" +
                "Naega dowajulge\n" +
                "Gyaeneun geunyang playin'\n" +
                "Boys be always lyin', yeah\n" +
                "\n" +
                "Nae saengil patie neoman mot on geunal\n" +
                "Hyejiniga eomcheong honnatdeon geunal\n" +
                "Jiwoniga yeochinirang heeojin geunal\n" +
                "Gyaeneun eonjena nega eopsi geunal\n" +
                "Neomu meosinneun oseul ipgo geunal\n" +
                "Heard him say\n" +
                "\n" +
                "We can go wherever you like\n" +
                "Baby, say the words and I'm down\n" +
                "All I need is you on my side\n" +
                "We can go whenever you like\n" +
                "Now, where are you? (Mm-mhm)\n" +
                "What's your ETA? What's your ETA? (Mm-mhm-mm)\n" +
                "What's your ETA? What's your ETA? (Mm-mhm)\n" +
                "What's your ETA? What's your ETA?\n" +
                "I'll be there right now, lose the boy on your arm\n" +
                "\n" +
                "Need a boy on my arm (Need a boy on my arm)\n" +
                "Need a boy on my arm (Need a boy on my arm)\n" +
                "Want a boy on my arm (Want a boy on my arm)\n" +
                "Need you boy on my arm (Need you boy on my arm)\n" +
                "\n" +
                "[Korean:]\n" +
                "\n" +
                "낭비하지 마 네 시간은 은행\n" +
                "서둘러서 정리해 걔는 real bad\n" +
                "받아주면 안돼\n" +
                "No, you better trust me\n" +
                "답답해서 그래\n" +
                "저번에도 봤지만 너 없을 때\n" +
                "걘 여기저기에 눈빛을 뿌리네\n" +
                "아주 눈부시게\n" +
                "Honestly 우리 사이에\n" +
                "He's been totally lyin', yeah\n" +
                "\n" +
                "내 생일 파티에 너만 못 온 그날\n" +
                "혜진이가 엄청 혼났던 그날\n" +
                "지원이가 여친이랑 헤어진 그날\n" +
                "걔는 언제나 네가 없이 그날\n" +
                "너무 멋있는 옷을 입고 그날\n" +
                "Heard him say\n" +
                "\n" +
                "We can go wherever you like\n" +
                "Baby, say the words and I'm down\n" +
                "All I need is you on my side\n" +
                "We can go whenever you like\n" +
                "Now, where are you? (Mm-mhm)\n" +
                "What's your ETA? What's your ETA? (Mm-mhm-mm)\n" +
                "What's your ETA? What's your ETA? (Mm-mhm)\n" +
                "What's your ETA? What's your ETA?\n" +
                "I'll be there right now, lose that boy on her arm\n" +
                "\n" +
                "아파 맘이 네가 걔 못 잊을 때\n" +
                "내 말 믿어 you deserve better than that\n" +
                "내가 도와줄게\n" +
                "걔는 그냥 playin'\n" +
                "Boys be always lyin', yeah\n" +
                "\n" +
                "내 생일 파티에 너만 못 온 그날\n" +
                "혜진이가 엄청 혼났던 그날\n" +
                "지원이가 여친이랑 헤어진 그날\n" +
                "걔는 언제나 네가 없이 그날\n" +
                "너무 멋있는 옷을 입고 그날\n" +
                "Heard him say\n" +
                "\n" +
                "We can go wherever you like\n" +
                "Baby, say the words and I'm down\n" +
                "All I need is you on my side\n" +
                "We can go whenever you like\n" +
                "Now, where are you? (Mm-mhm)\n" +
                "What's your ETA? What's your ETA? (Mm-mhm-mm)\n" +
                "What's your ETA? What's your ETA? (Mm-mhm)\n" +
                "What's your ETA? What's your ETA?\n" +
                "I'll be there right now, lose the boy on your arm\n" +
                "\n" +
                "Need a boy on my arm (Need a boy on my arm)\n" +
                "Need a boy on my arm (Need a boy on my arm)\n" +
                "Want a boy on my arm (Want a boy on my arm)\n" +
                "Need you boy on my arm (Need you boy on my arm)\n" +
                "\n" +
                "[English translation:]\n" +
                "\n" +
                "Don't waste it, your time's a bank\n" +
                "Come on and end it, he's real bad\n" +
                "Don't indulge him\n" +
                "No, you better trust me\n" +
                "Why can't you see it?\n" +
                "I saw it before but when you weren't there\n" +
                "Sprinkling his gaze everywhere\n" +
                "So dazzling, honestly between us\n" +
                "He's been totally lying, yeah\n" +
                "\n" +
                "The day you couldn't come to my birthday party\n" +
                "The day Hyejin got in so much trouble\n" +
                "The day Jiwon broke up with his girlfriend\n" +
                "The day without you he was always\n" +
                "The day he showed up dressed to the nines\n" +
                "Heard him say\n" +
                "\n" +
                "We can go wherever you like\n" +
                "Baby, say the words and I'm down\n" +
                "All I need is you on my side\n" +
                "We can go whenever you like\n" +
                "Now, where are you? (Mm-mhm)\n" +
                "What's your ETA? What's your ETA? (Mm-mhm-mm)\n" +
                "What's your ETA? What's your ETA? (Mm-mhm)\n" +
                "What's your ETA? What's your ETA?\n" +
                "I'll be there right now, lose that boy on her arm\n" +
                "\n" +
                "My heart hurts when you can't let him go\n" +
                "Trust me you deserve better than that\n" +
                "I'll give you a hand\n" +
                "He's just playin'\n" +
                "Boys be always lying, yeah\n" +
                "\n" +
                "The day you couldn't come to my birthday party\n" +
                "The day Hyejin got in so much trouble\n" +
                "The day Jiwon broke up with his girlfriend\n" +
                "The day without you he was always\n" +
                "The day he showed up dressed to the nines\n" +
                "Heard him say\n" +
                "\n" +
                "We can go wherever you like\n" +
                "Baby, say the words and I'm down\n" +
                "All I need is you on my side\n" +
                "We can go whenever you like\n" +
                "Now, where are you? (Mm-mhm)\n" +
                "What's your ETA? What's your ETA? (Mm-mhm-mm)\n" +
                "What's your ETA? What's your ETA? (Mm-mhm)\n" +
                "What's your ETA? What's your ETA?\n" +
                "I'll be there right now, lose the boy on your arm\n" +
                "\n" +
                "Need a boy on my arm (Need a boy on my arm)\n" +
                "Need a boy on my arm (Need a boy on my arm)\n" +
                "Want a boy on my arm (Want a boy on my arm)\n" +
                "Need you boy on my arm (Need you boy on my arm)";
    }

    private String getLyrics2() {
        return "Lyrics for Song 2...";
    }

    private String getLyrics3() {
        return "Lyrics for Song 3...";
    }

    private String getLyrics4() {
        return "Lyrics for Song 4...";
    }

    private String getLyrics5() {
        return "Lyrics for Song 5...";
    }

    private void addSong(String title, String filePath, String lyrics, String imageFilePath) {
        Song newSong = new Song(title, filePath, lyrics, imageFilePath);
        songs.add(newSong);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
