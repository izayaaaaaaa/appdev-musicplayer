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

public class MP3PlayerView extends JFrame{
  JPanel panel1, panel2, songListPanel, panelA, panelB, panelC, panelD;
  JButton playBtn, pauseBtn, fastForwardBtn, backtrackBtn, nextSongBtn, previousSongBtn;
  JTable songList;
  JTable showPlaylist;
  JScrollPane sp;
  JProgressBar pb;
  
  
  public MP3PlayerView() {
    panel1 = new JPanel(new GridBagLayout());
    panel1.setBackground(Color.BLUE);
    GridBagConstraints c = new GridBagConstraints();
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;

    c.gridx = 0;
    c.gridy = 0;
    panelA = new JPanel();
    panelA.setBackground(Color.GREEN);
    panel1.add(panelA, c);

    c.gridx = 0;
    c.gridy = 1;
    panelB = new JPanel();
    panelB.setBackground(Color.YELLOW);
    panel1.add(panelB, c);

    c.gridx = 0;
    c.gridy = 2;
    panelC = new JPanel();
    panelC.setBackground(Color.ORANGE);
    panel1.add(panelC, c);

    c.gridx = 0;
    c.gridy = 3;
    panelD = new JPanel();
    panelD.setBackground(Color.PINK);

    createPreviousSongButton();
    createBacktrackButton();
    createPlayButton();
    createPauseButton();
    createFastForwardButton();
    createNextSongButton();
    
    panel1.add(panelD, c);

    panel2 = new JPanel(new BorderLayout());
    panel2.setBackground(Color.RED);

    // put the song list here
    songListPanel = new JPanel();
    songList = new JTable();
    

    this.setLayout(new GridLayout(1, 2));
    this.add(panel1);
    this.add(panel2);

    this.setTitle("MP3 Player");
    this.setSize(500, 500);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
  
  public void createPlayButton() {
    playBtn = new JButton("Play");
    panelD.add(playBtn);
  }
  
  public void createPauseButton() {
    pauseBtn = new JButton("Pause");
    panelD.add(pauseBtn);
  }
  
  public void createFastForwardButton(){
    fastForwardBtn = new JButton("FastForward");
    panelD.add(fastForwardBtn);
  }
  
  public void createBacktrackButton(){
    backtrackBtn = new JButton("Backtrack");
    panelD.add(backtrackBtn);
  }
  
  public void createNextSongButton(){
    nextSongBtn = new JButton("Next");
    panelD.add(nextSongBtn);
  }
  
  public void createPreviousSongButton(){
    previousSongBtn = new JButton("Previous");
    panelD.add(previousSongBtn);
  }
  
  public void createCurrentSong(String songName) {
    JLabel currentSong = new JLabel(songName);
    panelA.add(currentSong);
  }

  public void createProgressBar(){ 
    //CurrentSong Min and Max Duration
    //JProgressbar(min, max)
    pb = new JProgressBar(); //Pass as parameter for current song time duration
  }
  
  public void createPlaylist(List<String> playlist) {
    showPlaylist = new JTable(); 
    sp = new JScrollPane(showPlaylist);
  }
}
