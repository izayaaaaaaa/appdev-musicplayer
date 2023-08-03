package com;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

// import javafx.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.Border;

import javafx.scene.text.Font;

public class MP3PlayerView extends JFrame{
  JPanel mainPanel, leftPanel, logoPanel, songListPanel, centerPanel, currentlyPlayingPanel, rightPanel, settingsPanel, lyricsPanel, bottomPanel;
  JPanel topSettingsPanel, bottomSettingsPanel;
  JPanel leftPaddingPanel, rightPaddingPanel, currentSongDisplayPanel, songDetailsPanel, playBackPanel;

  JLabel songNameLbl, artistNameLbl, artistImgLbl, songTitleLabel, songArtistLabel; 

  JTextArea lyricsTextArea;
  JScrollPane lyricsScrollPane, songListPane;

  JButton playBtn, pauseBtn, fastForwardBtn, backtrackBtn, nextSongBtn, previousSongBtn;

  JButton folderBtn, settingsBtn, minBtn, maxBtn, exitBtn;

  JTable showPlaylist;
  JList<String> songList;
  JScrollPane sp;
  JProgressBar pb;
  JLabel logoTitle;

  ImageIcon staticImageIcon, animatedGifIcon;
  JLabel playerImageLabel;

  Timer gifTimer;
  int currentFrameIndex = 0;

  ImageIcon logoIcon;

  // private long currentSongDuration;
  // private long currentSongPosition;
  // private boolean isSongPlaying;
  
  
  public MP3PlayerView() {
    // Load the custom font "Poppins" from the resources folder
    // try (InputStream fontStream = MP3PlayerView.class.getResourceAsStream("/MusicPlayer/src/main/resources/poppins-bold.ttf")) {
    //     Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
    //     Font customFontBold = customFont.deriveFont(Font.BOLD, 20);

    //     // Set the font of the "jukebox" JLabel to "Poppins"
    //     logoTitle.setFont(customFontBold);

    //     // ... (other code remains the same)
    // } catch (IOException | FontFormatException e) {
    //     e.printStackTrace();
    // }

    mainPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    createLeftPanel();
    createCenterPanel();
    createRightPanel();
    createBottomPanel();

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 0.2;
    gbc.weighty = 0.9;
    mainPanel.add(leftPanel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weightx = 0.4;
    mainPanel.add(centerPanel, gbc);
    
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.weightx=3;
    mainPanel.add(rightPanel, gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weighty = 0.1;
    gbc.gridwidth = 3;
    mainPanel.add(bottomPanel, gbc);

    // settingsPanel = new JPanel(new GridLayout(2, 1));

    // topSettingsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    // topSettingsPanel.setBackground(Color.decode("#f3f2de"));;

    // bottomSettingsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    // bottomSettingsPanel.setBackground(Color.decode("#f3f2de"));

    // settingsPanel.add(topSettingsPanel);
    // settingsPanel.add(bottomSettingsPanel);
    // settingsPanel.setBackground(Color.decode("#f3f2de"));;

    // // mainPanel.add(settingsPanel, c);

    // createFolderButton();
    // createSettingsButton();
    
    // this.setLayout(new GridLayout(1, 3)); // Consider updating the GridLayout to (6, 1) if you want bottomPanel to occupy the entire bottom row
    this.add(mainPanel);
    // set frame color with hex code
    this.setTitle("MP3 Player");
    this.pack();
    this.setSize(1440, 1024);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  public void createLeftPanel() {
    leftPanel = new JPanel(new GridBagLayout());
    leftPanel.setBackground(Color.decode("#f0e2be"));

    // Set the "Verdana" font with size 24
    // Font customFont24 = new Font("Verdana", Font.PLAIN, 24);

    logoTitle = new JLabel();    
    logoIcon = new ImageIcon(ClassLoader.getSystemResource("jukebox.png"));
    logoTitle.setIcon(logoIcon);
    // setbackground to white
    logoTitle.setBackground(Color.WHITE);

    createSongList();
  
    // Add padding to the label using Insets
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(0, 0, 50, 0);
    gbc.anchor = GridBagConstraints.NORTH;
    leftPanel.add(logoTitle, gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 1;
    // gbc.insets = new Insets(10, 0, 10, 0);
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    songListPanel.setBackground(Color.BLACK);
    
    // set size of songlistpanel to leftpanel
    // songListPanel.setPreferredSize(new Dimension(300, 300));
    leftPanel.add(songListPanel, gbc);
  }

  public void createRightPanel() {
    rightPanel = new JPanel(new GridLayout(3,3));
    rightPanel.setBackground(Color.decode("#f2f0db"));

    // Create an empty panel for top padding
    JPanel topPaddingPanel = new JPanel();
    // topPaddingPanel.setPreferredSize(new Dimension(1, 20));
    topPaddingPanel.setOpaque(false);

    // create an empty panel for rightside padding
    JPanel rightPaddingPanel = new JPanel();
    // rightPaddingPanel.setPreferredSize(new Dimension(100, 1));
    rightPaddingPanel.setOpaque(false);

    lyricsPanel = new JPanel(new BorderLayout());
    lyricsPanel.setBackground(Color.decode("#f3f2de"));;

    lyricsTextArea = new JTextArea();
    lyricsScrollPane = new JScrollPane(lyricsTextArea);
    lyricsPanel.add(lyricsScrollPane, BorderLayout.CENTER);

    // set the lyrics background color to be the same as the right panel and text should be black
    lyricsTextArea.setBackground(Color.decode("#f2f0db"));
    lyricsTextArea.setForeground(Color.BLACK);

    rightPanel.setBackground(Color.decode("#f2f0db"));
    rightPanel.add(topPaddingPanel);
    rightPanel.add(lyricsPanel);
    //rightPanel.add(rightPaddingPanel);
  
    // rightPanel.add(topPaddingPanel, BorderLayout.NORTH);
    // rightPanel.add(rightPaddingPanel, BorderLayout.EAST);
    // rightPanel.add(lyricsPanel, BorderLayout.CENTER);
  }

  public void createCenterPanel() {
    centerPanel = new JPanel();
    currentlyPlayingPanel = new JPanel(new BorderLayout());
    currentlyPlayingPanel.setBackground(Color.decode("#f3f2de"));

    staticImageIcon = new ImageIcon("MusicPlayer/src/main/resources/static.jpg");
    animatedGifIcon = new ImageIcon("MusicPlayer/src/main/resources/plaka_photoshop_point1.gif");
    staticImageIcon.setImage(staticImageIcon.getImage().getScaledInstance(620, 450, Image.SCALE_DEFAULT));
    animatedGifIcon.setImage(animatedGifIcon.getImage().getScaledInstance(620, 450, Image.SCALE_DEFAULT));
    playerImageLabel = new JLabel(staticImageIcon);

    // Start the timer to control the GIF playback speed
    int delay = 0; // Adjust the delay to control the playback speed (larger value = slower playback)
    gifTimer = new Timer(delay, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        currentFrameIndex = (currentFrameIndex + 1) % animatedGifIcon.getIconWidth();
        Image frameImage = getCurrentFrame(animatedGifIcon.getImage(), currentFrameIndex);
        playerImageLabel.setIcon(new ImageIcon(frameImage));
      }
    });
    // gifTimer.start(); // Start the timer

    // create an empty north panel
    JPanel emptyNorthPanel = new JPanel();
    emptyNorthPanel.setBackground(Color.decode("#f3f2de"));
    emptyNorthPanel.setPreferredSize(new Dimension(200, 250));

    // create a south panel with 2 rows of jlabel
    JPanel southPanel = new JPanel(new GridLayout(2, 1));
    southPanel.setBackground(Color.decode("#f3f2de"));
    southPanel.setPreferredSize(new Dimension(300, 100));

    // create a song title label based on currently playing song
    songTitleLabel = new JLabel("Song Title");
    southPanel.add(songTitleLabel);

    // create a song artist label
    songArtistLabel = new JLabel("Song Artist");
    southPanel.add(songArtistLabel);

    // add the panels into the currently playing panel
    currentlyPlayingPanel.add(emptyNorthPanel, BorderLayout.NORTH);
    currentlyPlayingPanel.add(southPanel, BorderLayout.SOUTH);

    currentlyPlayingPanel.add(playerImageLabel, BorderLayout.CENTER);
    centerPanel.add(currentlyPlayingPanel);
    centerPanel.setBackground(Color.decode("#f2f0db"));
  }

  public void createBottomPanel() {
    bottomPanel = new JPanel();

    // create left and right panels
    leftPaddingPanel = new JPanel();
    leftPaddingPanel.setPreferredSize(new Dimension(20, 1));
    leftPaddingPanel.setOpaque(false);

    rightPaddingPanel = new JPanel();
    rightPaddingPanel.setPreferredSize(new Dimension(20, 1));
    rightPaddingPanel.setOpaque(false);

    //Song details panel
    songDetailsPanel = new JPanel(new GridLayout(2, 0));
    songNameLbl = new JLabel("Song Name");
    artistNameLbl = new JLabel("Artist Name");

    //Add contnent for song details
    songDetailsPanel.add(songNameLbl);
    songDetailsPanel.add(artistNameLbl);
    
    //Create current currentSongDisplay Panel
    currentSongDisplayPanel = new JPanel(new FlowLayout(0,50,25));
    currentSongDisplayPanel.setBackground(Color.decode("#f0e2be"));;

    //Creat artist image
    artistImgLbl = new JLabel();

    //Set the progress bar
    createProgressBar();
    pb.setPreferredSize(new Dimension(600, 10));
    
    //Create playback panel
    playBackPanel =  new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
    playBackPanel.setBackground(Color.decode("#f0e2be"));;

    //Create buttons for playback Panel
    previousSongBtn = new JButton();
    previousSongBtn.setBackground(Color.decode("#f0e2be"));
    previousSongBtn.setBorder(BorderFactory.createEmptyBorder());
    previousSongBtn.setIcon(new ImageIcon("MusicPlayer/src/main/resources/icons/previousIcon.png"));
   
    backtrackBtn = new JButton();
    backtrackBtn.setBackground(Color.decode("#f0e2be"));
    backtrackBtn.setBorder(BorderFactory.createEmptyBorder());
    backtrackBtn.setIcon(new ImageIcon("MusicPlayer/src/main/resources/icons/backtrackIcon.png"));

    playBtn = new JButton();
    playBtn.setBackground(Color.decode("#f0e2be"));
    playBtn.setBorder(BorderFactory.createEmptyBorder());
    playBtn.setIcon(new ImageIcon("MusicPlayer/src/main/resources/icons/playIcon.png"));

    pauseBtn = new JButton();
    pauseBtn.setBackground(Color.decode("#f0e2be"));
    pauseBtn.setBorder(BorderFactory.createEmptyBorder());
    pauseBtn.setIcon(new ImageIcon("MusicPlayer/src/main/resources/icons/pauseIcon.png"));

    fastForwardBtn = new JButton();
    fastForwardBtn.setBackground(Color.decode("#f0e2be"));
    fastForwardBtn.setBorder(BorderFactory.createEmptyBorder());
    fastForwardBtn.setIcon(new ImageIcon("MusicPlayer/src/main/resources/icons/fastforwardIcon.png"));

    nextSongBtn = new JButton();
    nextSongBtn.setBackground(Color.decode("#f0e2be"));
    nextSongBtn.setBorder(BorderFactory.createEmptyBorder());
    nextSongBtn.setIcon(new ImageIcon("MusicPlayer/src/main/resources/icons/nextIcon.png"));

    //Add all buttons into the playback panel
    playBackPanel.add(previousSongBtn);
    playBackPanel.add(backtrackBtn);
    playBackPanel.add(playBtn);
    playBackPanel.add(pauseBtn);
    playBackPanel.add(fastForwardBtn);
    playBackPanel.add(nextSongBtn);

    //Add the panels into the song display panel
    currentSongDisplayPanel.add(artistImgLbl);
    currentSongDisplayPanel.add(songDetailsPanel);
    currentSongDisplayPanel.add(pb);
    currentSongDisplayPanel.add(playBackPanel);

    bottomPanel.add(leftPaddingPanel, BorderLayout.WEST);
    bottomPanel.add(currentSongDisplayPanel, BorderLayout.CENTER);
    bottomPanel.add(rightPaddingPanel, BorderLayout.EAST);
    bottomPanel.setBackground(Color.decode("#f0e2be"));
  }

  public void createSongList() {
    songListPanel = new JPanel(new BorderLayout());
    songListPanel.setBackground(Color.decode("#f0e2be"));
    songList = new JList<>();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicplayer");
    EntityManager em = emf.createEntityManager();
    List<Song> tracks = em.createQuery("SELECT s FROM Song s", Song.class).getResultList();
    em.close();
    emf.close();

    String[] songTitles = new String[tracks.size()];
    for (int i = 0; i < tracks.size(); i++) {
      Song song = tracks.get(i);
      songTitles[i] = song.getSongTitle();
    }

    // Set the data to the JList
    songList.setListData(songTitles);
    songList.setSelectedIndex(0);

    songListPane = new JScrollPane(songList);
    // songListPane.setPreferredSize(new Dimension(300, 500));

    songListPanel.add(songListPane, BorderLayout.CENTER);
  }
  
  // Method to extract the current frame from the GIF image
  private Image getCurrentFrame(Image image, int frameIndex) {

    MediaTracker mediaTracker = new MediaTracker(this);
    mediaTracker.addImage(image, 0);
    try {
      mediaTracker.waitForAll();
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }

    int width = image.getWidth(null);
    int height = image.getHeight(null);
    int[] pixels = new int[width * height];

    PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, pixels, 0, width);
    try {
        pixelGrabber.grabPixels();
    } catch (InterruptedException ex) {
        ex.printStackTrace();
    }

    int frameWidth = 300; // Adjust this value based on the desired frame width
    int[] currentFramePixels = new int[frameWidth * height];

    for (int y = 0; y < height; y++) {
        System.arraycopy(pixels, y * width + frameIndex, currentFramePixels, y * frameWidth, frameWidth);
    }

    Image currentFrameImage = createImage(new MemoryImageSource(frameWidth, height, currentFramePixels, 0, frameWidth));
    return currentFrameImage;
  }

  // public void createFolderButton(){
  //   folderBtn = new JButton("Folder");
  //   bottomSettingsPanel.add(folderBtn);
  // }

  // public void createSettingsButton(){
  //   settingsBtn = new JButton("Settings");
  //   bottomSettingsPanel.add(settingsBtn);
  // }

  public void createProgressBar(){
    //CurrentSong Min and Max Duration
    //JProgressbar(min, max)
    pb = new JProgressBar(); //Pass as parameter for current song time duration
    // pb = new JProgressBar(0, 100);
    pb.setValue(0);
    pb.setStringPainted(true);
  }
}
