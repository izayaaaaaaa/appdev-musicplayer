package com;

// import javafx.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class MP3PlayerView extends JFrame{
  JPanel mainPanel, leftPanel, logoPanel, songListPanel, centerPanel, currentlyPlayingPanel, rightPanel, settingsPanel, lyricsPanel, bottomPanel;
  JPanel topSettingsPanel, bottomSettingsPanel;
  JPanel leftPaddingPanel, rightPaddingPanel, currentSongDisplayPanel, songDetailsPanel, playBackPanel;

  JLabel songNameLbl, artistNameLbl, artistImgLbl, songTitleLabel, songArtistLabel; 

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

  Timer gifTimer;
  int currentFrameIndex = 0;

  ImageIcon logoIcon;
  
  
  public MP3PlayerView() {
    mainPanel = new JPanel(new BorderLayout());

    createLeftPanel();
    createCenterPanel();
    createRightPanel();
    createBottomPanel();

    mainPanel.add(leftPanel, BorderLayout.WEST);
    mainPanel.add(centerPanel, BorderLayout.CENTER);
    mainPanel.add(rightPanel, BorderLayout.EAST);
    mainPanel.add(bottomPanel, BorderLayout.SOUTH);

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

    logoTitle = new JLabel();    
    logoIcon = new ImageIcon(ClassLoader.getSystemResource("jukebox (1).png"));
    logoTitle.setIcon(logoIcon);
    logoTitle.setBackground(Color.WHITE);

    createSongList();
  
    // Add padding to the label using Insets
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(0, 0, 100, 0);
    gbc.anchor = GridBagConstraints.NORTH;
    leftPanel.add(logoTitle, gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.NORTH;

    leftPanel.add(songList, gbc);
  }

  public void createRightPanel() {
    rightPanel = new JPanel(new BorderLayout());
    rightPanel.setBackground(Color.decode("#f2f0db"));

    // Create an empty panel for top padding
    JPanel topPaddingPanel = new JPanel();
    topPaddingPanel.setOpaque(false);
    topPaddingPanel.setPreferredSize(new Dimension(500, 220));

    JPanel bottomPaddingPanel = new JPanel();
    bottomPaddingPanel.setOpaque(false);
    bottomPaddingPanel.setPreferredSize(new Dimension(520, 220));

    // create an empty panel for rightside padding
    JPanel rightPaddingPanel = new JPanel();
    rightPaddingPanel.setOpaque(false);
    rightPaddingPanel.setPreferredSize(new Dimension(20, 0));

    JPanel leftPaddingPanel = new JPanel();
    leftPaddingPanel.setOpaque(false);
    leftPaddingPanel.setPreferredSize(new Dimension(10, 0));

    lyricsPanel = new JPanel(new BorderLayout());
    lyricsPanel.setBackground(Color.decode("#f3f2de"));

    lyricsTextArea = new JTextArea("Please Select a Song");
    lyricsScrollPane = new JScrollPane(lyricsTextArea);
    lyricsScrollPane.setOpaque(false);
    lyricsScrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#f2f0db")));
    lyricsPanel.add(lyricsScrollPane, BorderLayout.CENTER);

    // set the lyrics background color to be the same as the right panel and text should be black
    lyricsTextArea.setBackground(Color.decode("#f2f0db"));
    lyricsTextArea.setForeground(Color.BLACK);
    lyricsTextArea.setBorder(BorderFactory.createLineBorder(Color.decode("#f2f0db")));
    lyricsTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
    lyricsTextArea.setEditable(false);
    lyricsTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
    

    rightPanel.setBackground(Color.decode("#f2f0db"));
    rightPanel.add(leftPaddingPanel, BorderLayout.WEST);
    rightPanel.add(bottomPaddingPanel, BorderLayout.SOUTH);
    rightPanel.add(topPaddingPanel, BorderLayout.NORTH);
    rightPanel.add(rightPaddingPanel, BorderLayout.EAST);
    rightPanel.add(lyricsPanel, BorderLayout.CENTER);
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

    int delay = 0; // Adjust the delay to control the playback speed (larger value = slower playback)
    gifTimer = new Timer(delay, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        currentFrameIndex = (currentFrameIndex + 1) % animatedGifIcon.getIconWidth();
        Image frameImage = getCurrentFrame(animatedGifIcon.getImage(), currentFrameIndex);
        playerImageLabel.setIcon(new ImageIcon(frameImage));
      }
    });

    // create an empty north panel
    JPanel emptyNorthPanel = new JPanel();
    emptyNorthPanel.setBackground(Color.decode("#f3f2de"));
    emptyNorthPanel.setPreferredSize(new Dimension(200, 250));

    // add the panels into the currently playing panel
    currentlyPlayingPanel.add(emptyNorthPanel, BorderLayout.NORTH);

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
    songNameLbl.setOpaque(false);
    songNameLbl.setFont(new Font("Poppins", Font.BOLD, 20));
    artistNameLbl = new JLabel("Artist Name");
    artistNameLbl.setOpaque(false);
    artistNameLbl.setFont(new Font("Poppins", Font.PLAIN, 12));

    //Add contnent for song details
    songDetailsPanel.setOpaque(false);
    songDetailsPanel.add(songNameLbl);
    songDetailsPanel.add(artistNameLbl);
    
    //Create current currentSongDisplay Panel
    currentSongDisplayPanel = new JPanel(new FlowLayout(0,50,25));
    currentSongDisplayPanel.setBackground(Color.decode("#f0e2be"));;

    //Creat artist image
    artistImgLbl = new JLabel();

    //Set the progress bar
    createProgressBar();
    pb.setPreferredSize(new Dimension(600, 15));
    pb.setStringPainted(false);
    pb.setUI(new CustomProgressBarUI());

    //Create playback panel
    playBackPanel =  new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
    playBackPanel.setBackground(Color.decode("#f0e2be"));

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

  class CustomProgressBarUI extends BasicProgressBarUI {
    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;

        // Get the current value and bounds of the progress bar
        int value = pb.getValue();
        int width = pb.getWidth();
        int height = pb.getHeight();

        // Calculate the width of the filled portion
        int fillWidth = (int) (width * value / 100.0);

        // Define the colors for the progress bar
        Color background = Color.decode("#D9D9D9");
        Color foreground = Color.decode("#3d405b");

        // Draw the background
        g2d.setColor(background);
        g2d.fillRect(0, 0, width, height);

        // Draw the filled portion
        g2d.setColor(foreground);
        g2d.fillRect(0, 0, fillWidth, height);
    }
  }

  public void createSongList() {
    songListPanel = new JPanel();
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

    songList.setCellRenderer(new CustomListCellRenderer());
    // Set the data to the JList
    songList.setListData(songTitles);
    songList.setSelectedIndex(0);
    songList.setBackground(Color.decode("#f0e2be"));
    songList.setPreferredSize(new Dimension(100, 500));
    songList.setFixedCellHeight(100);
    songList.setFixedCellWidth(20);
  }

  class CustomListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // Set the foreground color based on the selection
        if (isSelected) {
            component.setFont(new Font("Poppins", Font.BOLD, 15));
            component.setBackground(list.getBackground());
            if (cellHasFocus) {
              list.setSelectionBackground(list.getBackground());
              list.setSelectionForeground(list.getForeground());
              ((JComponent) component).setBorder(null);
          }

        } else {
            component.setFont(new Font("Poppins", Font.PLAIN, 12));
        }

        return component;
    }
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

  public void createProgressBar(){
    pb = new JProgressBar(); //Pass as parameter for current song time duration
    pb.setValue(0);
    pb.setStringPainted(true);
  }
}
