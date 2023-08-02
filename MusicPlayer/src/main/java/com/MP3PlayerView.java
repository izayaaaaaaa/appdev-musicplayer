package com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
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

public class MP3PlayerView extends JFrame{
  JPanel mainPanel, logoPanel, settingsPanel, songListPanel, currentlyPlayingPanel, lyricsPanel, bottomPanel;
  JPanel topSettingsPanel, bottomSettingsPanel;
  JPanel leftPaddingPanel, rightPaddingPanel, currentSongDisplayPanel, songDetailsPanel, playBackPanel;

  JLabel songNameLbl, artistNameLbl, artistImgLbl;

  JTextArea lyricsTextArea;
  JScrollPane lyricsScrollPane;

  JButton playBtn, pauseBtn, fastForwardBtn, backtrackBtn, nextSongBtn, previousSongBtn;

  JButton folderBtn, settingsBtn, minBtn, maxBtn, exitBtn;

  JTable showPlaylist;
  JList<String> songList;
  JScrollPane sp;
  JProgressBar pb;
  JLabel logoTitle;

  ImageIcon staticImageIcon, animatedGifIcon;
  JLabel playerImageLabel;

  //declare the giftimer
  Timer gifTimer;
  int currentFrameIndex = 0;

  private long currentSongDuration;
  private long currentSongPosition;
  private boolean isSongPlaying;
  
  
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
    mainPanel.setBackground(Color.decode("#f3f2de"));;
    GridBagConstraints c = new GridBagConstraints();
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;

    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 2;
    c.gridheight = 1;
    logoPanel = new JPanel();
    // logoPanel.setBackground(Color.GREEN);
    logoTitle = new JLabel("jukebox");
    logoPanel.add(logoTitle);
    mainPanel.add(logoPanel, c);

    c.gridx = 2;
    c.gridy = 0;
    c.gridwidth = 1;
    c.gridheight = 1;
    settingsPanel = new JPanel(new GridLayout(2, 1));

    topSettingsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    topSettingsPanel.setBackground(Color.decode("#f3f2de"));;

    bottomSettingsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomSettingsPanel.setBackground(Color.decode("#f3f2de"));

    settingsPanel.add(topSettingsPanel);
    settingsPanel.add(bottomSettingsPanel);
    settingsPanel.setBackground(Color.decode("#f3f2de"));;

    mainPanel.add(settingsPanel, c);

    createFolderButton();
    createSettingsButton();
    createMinButton();
    createMaxButton();
    createExitButton();

    // create a list of songs
    c.gridx = 0;
    c.gridy = 1;
    c.gridheight = 3;
    createSongList();
    songListPanel = new JPanel(new GridLayout(1, 1));
    songListPanel.add(songList);
    songListPanel.setBackground(Color.decode("#f3f2de"));
    mainPanel.add(songListPanel, c);

    c.gridx = 1;
    c.gridy = 1;
    currentlyPlayingPanel = new JPanel(new BorderLayout());
    currentlyPlayingPanel.setBackground(Color.decode("#f3f2de"));

    staticImageIcon = new ImageIcon("MusicPlayer/src/main/resources/static.jpg");
    animatedGifIcon = new ImageIcon("MusicPlayer/src/main/resources/plaka_photoshop_point1.gif");
    staticImageIcon.setImage(staticImageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
    animatedGifIcon.setImage(animatedGifIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
    playerImageLabel = new JLabel(staticImageIcon);

    currentlyPlayingPanel.add(playerImageLabel, BorderLayout.CENTER);

    mainPanel.add(currentlyPlayingPanel, c);

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
    gifTimer.start(); // Start the timer

    c.gridx = 2;
    c.gridy = 1;
    lyricsPanel = new JPanel(new BorderLayout());
    lyricsPanel.setBackground(Color.decode("#f3f2de"));;

    mainPanel.add(lyricsPanel, c);
    createLyricsTextArea();

    //Bottom Panel Area
    c.gridx = 0;
    c.gridy = 4; // Update the gridy value to 4
    c.gridwidth = 3;
    bottomPanel = new JPanel();
    bottomPanel.setLayout(new BorderLayout()); 
    bottomPanel.setBackground(Color.decode("#f3f2de"));;

    //left padding panel
    createLeftPaddingPanel();
    
    //right padding panel
    createRightPaddingPanel();
    
    //Song details panel
    createSongDetailsPanel();
    createSongNameLbl();
    createArtistNameLbl();

    //Add contnent for song details
    songDetailsPanel.add(songNameLbl);
    songDetailsPanel.add(artistNameLbl);
    
    //Create current currentSongDisplay Panel
    createCurrentSongDisplayPanel();
    currentSongDisplayPanel.setBackground(Color.decode("#f3f2de"));;

    //Creat artist image
    createArtistImgLbl();

    //Set the progress bar
    createProgressBar();
    pb.setPreferredSize(new Dimension(600, 10));
    
    //Create playback panel
    createPlayBackPanel();
    playBackPanel.setBackground(Color.decode("#f3f2de"));;

    //Create buttons for playback Panel
    createPreviousSongButton();
    createBacktrackButton();
    createPlayButton();
    createPauseButton();
    createFastForwardButton();
    createNextSongButton();

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

    c.anchor = GridBagConstraints.PAGE_END;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 4; // Update the gridy value to 4
    c.gridwidth = 3;
    mainPanel.add(bottomPanel, c);

    this.setLayout(new GridLayout(1, 3)); // Consider updating the GridLayout to (6, 1) if you want bottomPanel to occupy the entire bottom row
    this.add(mainPanel);
    
    // set frame color with hex code
    this.setTitle("MP3 Player");
    this.pack();
    this.setSize(1440, 1024);
    this.setResizable(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
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

  public void createSongList(){
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
  }
    
  public void createSongNameLbl(){
    songNameLbl = new JLabel("Song Title");
  }

  public void createArtistNameLbl(){
    artistNameLbl = new JLabel("Artist Name");
  }

  public void createArtistImgLbl(){
    artistImgLbl = new JLabel();
    // Border round = BorderFactory.createLineBorder(Color.BLACK, 10, true);
    // artistImgLbl.setBorder(round);
   //artistImgLbl.
   
    //artistImgLbl.setSize(50, 50);
  }

  public void createLeftPaddingPanel(){
    leftPaddingPanel = new JPanel();
    leftPaddingPanel.setPreferredSize(new Dimension(20, 1));
    leftPaddingPanel.setOpaque(false);
  }

  public void createRightPaddingPanel(){
    rightPaddingPanel = new JPanel();
    rightPaddingPanel.setPreferredSize(new Dimension(20, 1));
    rightPaddingPanel.setOpaque(false);
  }

  public void createCurrentSongDisplayPanel(){
    currentSongDisplayPanel = new JPanel(new FlowLayout(0,50,25));
  }

  public void createSongDetailsPanel(){
    songDetailsPanel = new JPanel(new GridLayout(2, 0));
  }

  public void createPlayBackPanel(){
    playBackPanel =  new JPanel(new FlowLayout());
  }
  
  public void createLyricsTextArea(){
    
    lyricsTextArea = new JTextArea();
    lyricsScrollPane = new JScrollPane(lyricsTextArea);
    lyricsPanel.add(lyricsScrollPane, BorderLayout.CENTER);
  }

  public void createFolderButton(){
    folderBtn = new JButton("Folder");
    bottomSettingsPanel.add(folderBtn);
  }

  public void createSettingsButton(){
    settingsBtn = new JButton("Settings");
    bottomSettingsPanel.add(settingsBtn);
  }

  public void createMinButton(){
    minBtn = new JButton("Min");
    topSettingsPanel.add(minBtn);
  }

  public void createMaxButton(){
    maxBtn = new JButton("Max");
    topSettingsPanel.add(maxBtn);
  }

  public void createExitButton(){
    exitBtn = new JButton("Exit");
    topSettingsPanel.add(exitBtn);
  }
  
  public void createPlayButton() {
    // Display the current song on the UI
    playBtn = new JButton("Play");
    //playBackPanel.add
    
  }
  
  public void createPauseButton() {
    pauseBtn = new JButton("Pause");
    // panelD.add(pauseBtn);
  }
  
  public void createFastForwardButton(){
    fastForwardBtn = new JButton("FastForward");
    // panelD.add(fastForwardBtn);
  }
  
  public void createBacktrackButton(){
    backtrackBtn = new JButton("Backtrack");
    // panelD.add(backtrackBtn);
  }
  
  public void createNextSongButton(){
    nextSongBtn = new JButton("Next");
    // panelD.add(nextSongBtn);
  }
  
  public void createPreviousSongButton(){
    previousSongBtn = new JButton("Previous");
    // panelD.add(previousSongBtn);
  }

  public void createCurrentSong(String songName){
    JLabel currentSong = new JLabel(songName);
    // panelD.add(currentSong);
  }
  
  public void createProgressBar(){
    //CurrentSong Min and Max Duration
    //JProgressbar(min, max)
    pb = new JProgressBar(); //Pass as parameter for current song time duration
    // pb = new JProgressBar(0, 100);
    pb.setValue(0);
    pb.setStringPainted(true);
  }

  //public void 

  
  public void createPlaylist(List<String> playlist) {
    String[] columnNames = {"Song Title"};
    Object[][] data = new Object[playlist.size()][1];

    for (int i = 0; i < playlist.size(); i++) {
      data[i][0] = playlist.get(i);
    }

    showPlaylist = new JTable(data, columnNames);
    sp = new JScrollPane(showPlaylist);
    songListPanel.add(sp, BorderLayout.CENTER);
  }
}
