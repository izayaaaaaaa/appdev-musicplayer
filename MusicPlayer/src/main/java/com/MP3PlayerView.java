package com;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.JTextArea;

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
  JTable songList;
  JScrollPane sp;
  JProgressBar pb;
  JLabel logoTitle;
  
  
  public MP3PlayerView() {
    mainPanel = new JPanel(new GridBagLayout());
    // mainPanel.setBackground(Color.BLUE);
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
    topSettingsPanel.setBackground(Color.RED);

    bottomSettingsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomSettingsPanel.setBackground(Color.RED);

    settingsPanel.add(topSettingsPanel);
    settingsPanel.add(bottomSettingsPanel);
    settingsPanel.setBackground(Color.YELLOW);

    mainPanel.add(settingsPanel, c);

    createFolderButton();
    createSettingsButton();
    createMinButton();
    createMaxButton();
    createExitButton();

    // create a list of songs
    String[] dummySongs = {
      "Song 1",
      "Song 2",
      "Song 3",
      "Song 4",
      "Song 5",
      "Song 6",
      "Song 7",
      "Song 8",
      "Song 9",
      "Song 10",
    };

    c.gridx = 0;
    c.gridy = 1;
    c.gridheight = 3;
    songListPanel = new JPanel(new GridLayout(10, 1));
    // songListPanel.setBackground(Color.ORANGE);
    for (int i = 1; i <= 10; i++) {
      songListPanel.add(new JLabel(dummySongs[i-1]));
    }
    mainPanel.add(songListPanel, c);

    c.gridx = 1;
    c.gridy = 1;
    currentlyPlayingPanel = new JPanel();
    currentlyPlayingPanel.setBackground(Color.PINK);
    mainPanel.add(currentlyPlayingPanel, c);

    c.gridx = 2;
    c.gridy = 1;
    lyricsPanel = new JPanel(new BorderLayout());
    lyricsPanel.setBackground(Color.CYAN);

    mainPanel.add(lyricsPanel, c);
    createLyricsTextArea();

    c.gridx = 2;
    c.gridy = 1;
    lyricsPanel = new JPanel();
    lyricsPanel.setBackground(Color.CYAN);
    mainPanel.add(lyricsPanel, c);


    /////////////////////////////////////////////////////////////////////////
    //Bottom Panel Area
    c.gridx = 0;
    c.gridy = 4; // Update the gridy value to 4
    c.gridwidth = 3;
    bottomPanel = new JPanel();
    bottomPanel.setLayout(new BorderLayout()); 
    bottomPanel.setBackground(Color.MAGENTA);

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
    currentSongDisplayPanel.setBackground(Color.ORANGE);

    //Creat artist image
    createArtistImgLbl();

    //Set the progress bar
    createProgressBar();
    pb.setPreferredSize(new Dimension(600, 10));
    
    //Create playback panel
    createPlayBackPanel();
    playBackPanel.setBackground(Color.CYAN);

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
    
    this.setTitle("MP3 Player");
    // this.pack();
    this.setSize(1440, 1024);
    this.setResizable(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  public void createSongNameLbl(){
    songNameLbl = new JLabel("Song Title");
  }

  public void createArtistNameLbl(){
    artistNameLbl = new JLabel("Artist Name");
  }

  public void createArtistImgLbl(){
    artistImgLbl = new JLabel("Image Here");
    artistImgLbl.setSize(50, 50);
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
    
    lyricsTextArea = new JTextArea("Lyrics Here");
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
