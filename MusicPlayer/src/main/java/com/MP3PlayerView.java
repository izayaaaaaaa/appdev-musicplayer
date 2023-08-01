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
    logoPanel = new JPanel();
    // logoPanel.setBackground(Color.GREEN);
    logoTitle = new JLabel("jukebox");
    logoPanel.add(logoTitle);
    mainPanel.add(logoPanel, c);

    c.gridx = 2;
    c.gridy = 0;
    c.gridwidth = 1;
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

    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth = 3;
    bottomPanel = new JPanel();
    bottomPanel.setLayout(new BorderLayout()); 
    bottomPanel.setBackground(Color.MAGENTA);

    //left padding panel
    JPanel leftPaddingPanel = new JPanel();
    leftPaddingPanel.setPreferredSize(new Dimension(20, 1));
    leftPaddingPanel.setOpaque(false);

    //right padding panel
    JPanel rightPaddingPanel = new JPanel();
    rightPaddingPanel.setPreferredSize(new Dimension(20, 1));
    rightPaddingPanel.setOpaque(false);

    //Song details panel
    JPanel songDetails = new JPanel(new GridLayout(2, 0));
    JLabel songName = new JLabel("Song Title");
    JLabel artistName = new JLabel("Artist Name");

    //Add contnent for song details
    songDetails.add(songName);
    songDetails.add(artistName);
    
    //Create current currentSongDisplay Panel
    JPanel currentSongDisplay = new JPanel(new FlowLayout(0,50,25));
    currentSongDisplay.setBackground(Color.ORANGE);

    //Creat artist image
    JLabel artistImg = new JLabel("Image Here");
    artistImg.setSize(50, 50);

    //Set the progress bar
    createProgressBar();
    pb.setPreferredSize(new Dimension(600, 10));
    
    //Create playback panel
    JPanel playBackPanel = new JPanel(new FlowLayout());
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
    currentSongDisplay.add(artistImg);
    currentSongDisplay.add(songDetails);
    currentSongDisplay.add(pb);
    currentSongDisplay.add(playBackPanel);

    bottomPanel.add(leftPaddingPanel, BorderLayout.WEST);
    bottomPanel.add(currentSongDisplay, BorderLayout.CENTER);
    bottomPanel.add(rightPaddingPanel, BorderLayout.EAST);
    c.anchor = GridBagConstraints.PAGE_END;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth = 3;
    mainPanel.add(bottomPanel, c);


    mainPanel.add(bottomPanel, c);

    this.setLayout(new GridLayout(5, 3));
    this.add(mainPanel);
    
    this.setTitle("MP3 Player");
    // this.pack();
    this.setSize(1440, 1024);
    this.setResizable(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
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
    // panelD.add(playBtn);
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
