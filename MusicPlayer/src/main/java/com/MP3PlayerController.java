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
      int selection = view.songList.getSelectedIndex();
      int id = 0;

      if(selection <= -1){
        id = -1;
      }
      else{
        id = selection;
      }
      
      System.out.println("Current ID: " + id);

      model.fetchSong(id);
    } 
  }
  
  class playListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == view.playBtn) {
        int selection = view.songList.getSelectedIndex();
        int id = 0;
  
        if(selection <= -1){
          id = -1;
        }
        else{
          id = selection;
        }

        // try{
        //    imgCover = ImageIO.read(new File(model.fetchAlbumCover(id)));
        // }

        //ImageIcon img = new ImageIcon(model.fetchAlbumCover(id));
        //view.artistImgLbl.setIcon(img);

        System.out.println("FetchAlbum:" + model.fetchAlbumCover(id));
        ImageIcon icon = new ImageIcon(model.fetchAlbumCover(id));
        Image img = (icon).getImage().getScaledInstance(view.artistImgLbl.getWidth()+100, 
                                                        view.artistImgLbl.getHeight()+100, Image.SCALE_SMOOTH);

        icon = new ImageIcon(img);
        view.artistImgLbl.setIcon(icon);

        //view.artistImgLbl.setIcon(new ImageIcon(model.fetchAlbumCover(id)).getImage().getScaledInstance(imgCover.getWidth(), imgCover.getHeight(), Image.SCALE_SMOOTH));
        //System.out.println("Image is" + img);
        
        view.playerImageLabel.setIcon(view.animatedGifIcon);
        view.playBtn.setVisible(false);
        view.pauseBtn.setVisible(true);
        view.lyricsTextArea.setText(model.lyricsProcess(id));

        view.songNameLbl.setText(model.fetchSongName(id));
        view.artistNameLbl.setText(model.fetchArtistName(id));

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
        model.nextSongMusic();

      }
    }
  }

  class previousSongListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == view.previousSongBtn) {

        model.previousSongMusic();
      
      }
    }
  }

}