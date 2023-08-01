package com;

import java.awt.*;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
    logoPanel.setBackground(Color.GREEN);
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

    c.gridx = 0;
    c.gridy = 1;
    songListPanel = new JPanel();
    songListPanel.setBackground(Color.ORANGE);
    mainPanel.add(songListPanel, c);

    c.gridx = 1;
    c.gridy = 1;
    currentlyPlayingPanel = new JPanel();
    currentlyPlayingPanel.setBackground(Color.PINK);
    mainPanel.add(currentlyPlayingPanel, c);

    // createPreviousSongButton();
    // createBacktrackButton();
    // createPlayButton();
    // createPauseButton();
    // createFastForwardButton();
    // createNextSongButton();

    c.gridx = 2;
    c.gridy = 1;
    lyricsPanel = new JPanel(new BorderLayout());
    lyricsPanel.setBackground(Color.CYAN);

    mainPanel.add(lyricsPanel, c);
    createLyricsTextArea();



    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth = 3;
    bottomPanel = new JPanel();
    bottomPanel.setBackground(Color.MAGENTA);
    mainPanel.add(bottomPanel, c);

    this.setLayout(new GridLayout(3, 3));
    this.add(mainPanel);
    
    this.setTitle("MP3 Player");
    this.setSize(1440, 1024);
    this.setResizable(false);
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
    showPlaylist = new JTable(); 
    sp = new JScrollPane(showPlaylist);
  }
}
