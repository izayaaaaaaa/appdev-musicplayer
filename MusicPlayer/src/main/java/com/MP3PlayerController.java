package com;

import java.awt.Image;

// connects the physical form to the action

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
// import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.Timer;

// import javax.swing.Action;

public class MP3PlayerController {
  private MP3PlayerModel model;
  private MP3PlayerView view;

  private Timer songTimer;
  private BufferedImage imgCover;

  public MP3PlayerController(MP3PlayerModel model, MP3PlayerView view) {
    this.model = model;
    this.view = view;

    // syncing the buttons created in the view to the listeners found in controller
    view.playBtn.addActionListener(new playListener());
    view.pauseBtn.addActionListener(new pauseListener());
    view.fastForwardBtn.addActionListener(new fastForwardListener());
    view.backtrackBtn.addActionListener(new backtrackListener());
    view.nextSongBtn.addActionListener(new nextSongListener());
    view.previousSongBtn.addActionListener(new previousSongListener());
    view.songList.addListSelectionListener(new songSelectionListener());

    view.playBtn.setVisible(true);
    view.pauseBtn.setVisible(false);
  }

  class songSelectionListener implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
      int id = view.songList.getSelectedIndex();
      System.out.println("Current ID: " + view.songList.getSelectedIndex());
      
      if(!e.getValueIsAdjusting() || model.songClip.isRunning() || view.songList.getSelectedIndex() >= 0 ){
        model.stop();
        view.playBtn.setVisible(true);
        view.pauseBtn.setVisible(false);
        ImageIcon icon = new ImageIcon(model.fetchAlbumCover(id));
        Image img = (icon).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        view.lyricsTextArea.setText(model.lyricsProcess(id));
        icon = new ImageIcon(img);
        view.artistImgLbl.setIcon(icon);
        view.playerImageLabel.setIcon(view.staticImageIcon);
        view.songNameLbl.setText(model.fetchSongName(id));
        view.artistNameLbl.setText(model.fetchArtistName(id));
      }

    

      model.indexChanged(view.songList.getSelectedIndex());
      model.fetchID(view.songList.getSelectedIndex());
    } 
  }
  
  class playListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == view.playBtn) {
         int id = view.songList.getSelectedIndex();

        view.playBtn.setVisible(false);
        view.pauseBtn.setVisible(true);

        ImageIcon icon = new ImageIcon(model.fetchAlbumCover(id));
        Image img = (icon).getImage().getScaledInstance(view.artistImgLbl.getWidth(), view.artistImgLbl.getHeight(), Image.SCALE_SMOOTH);

        System.out.println("Image Width: " + view.artistImgLbl.getHeight());

        view.lyricsTextArea.setText(model.lyricsProcess(id)); //Lyrics
        icon = new ImageIcon(img); //Album Cover
        view.artistImgLbl.setIcon(icon); //Set Album Cover
        view.playerImageLabel.setIcon(view.animatedGifIcon); //Start Animating Plaka
        view.songNameLbl.setText(model.fetchSongName(id)); //Fetch Song Name
        view.artistNameLbl.setText(model.fetchArtistName(id)); //Fetch Artist Name

        model.playMusic();

        // Add this section to start updating the progress bar
        Timer timer = new Timer();
        TimerTask progressUpdateTask = new TimerTask() {
          @Override
          public void run() {
            // Update the progress bar based on the current position and duration
            long duration = model.getSongDuration();
            long position = model.getSongPosition();
            if (duration > 0) {
              int progress = (int) (position * 100 / duration);
              view.pb.setValue(progress);
            }
          }
        };
        // Schedule the progress update task to run every 1 second
        timer.scheduleAtFixedRate(progressUpdateTask, 0, 1000);
      }
    }
    
  }

  class pauseListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == view.pauseBtn) {
        view.playerImageLabel.setIcon(view.staticImageIcon);
        view.playBtn.setVisible(true);
        view.pauseBtn.setVisible(false);
        model.pauseMusic();
      }
    }
  }

  class fastForwardListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == view.fastForwardBtn) {
        model.fastForwardMusic();
      }
    }
  }

  class backtrackListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == view.backtrackBtn) {
        model.backtrackMusic();
      }
    }
  }

  class nextSongListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == view.nextSongBtn) {
        int id = view.songList.getSelectedIndex();
        int nextIndex = view.songList.getSelectedIndex() + 1;
        int listSize = view.songList.getModel().getSize() - 1;

        if(nextIndex > listSize){
          view.songList.setSelectedIndex(0);
        }
        else{
          view.songList.setSelectedIndex(nextIndex);
        }

        ImageIcon icon = new ImageIcon(model.fetchAlbumCover(id));
        Image img = (icon).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        view.lyricsTextArea.setText(model.lyricsProcess(id));
        icon = new ImageIcon(img);
        view.artistImgLbl.setIcon(icon);
        view.playerImageLabel.setIcon(view.staticImageIcon);
        view.songNameLbl.setText(model.fetchSongName(id));
        view.artistNameLbl.setText(model.fetchArtistName(id));
      }
    }
  }

  class previousSongListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == view.previousSongBtn) {
        int id = view.songList.getSelectedIndex();
        int prevIndex = view.songList.getSelectedIndex() - 1;
        int listSize = view.songList.getModel().getSize() - 1;

          if(prevIndex < 0){
            view.songList.setSelectedIndex(listSize);
          }
          else{
            view.songList.setSelectedIndex(prevIndex);
          }

        ImageIcon icon = new ImageIcon(model.fetchAlbumCover(id));
        Image img = (icon).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        view.lyricsTextArea.setText(model.lyricsProcess(id));
        icon = new ImageIcon(img);
        view.artistImgLbl.setIcon(icon);
        view.playerImageLabel.setIcon(view.staticImageIcon);
        view.songNameLbl.setText(model.fetchSongName(id));
        view.artistNameLbl.setText(model.fetchArtistName(id));
      }
    }
  }

}