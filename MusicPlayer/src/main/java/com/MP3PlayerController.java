package com;

// connects the physical form to the action

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

// import javax.swing.Action;

public class MP3PlayerController {
  private MP3PlayerModel model;
  private MP3PlayerView view;

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
        view.playBtn.setVisible(false);
        view.pauseBtn.setVisible(true);
        model.playMusic();
      }
    }
  }

  class pauseListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == view.pauseBtn) {
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
        // model.stop(currentSong);
        // model.songSelected = false;

        // currentSongIndex++;
        // if (currentSongIndex >= playlist.size()) {
        //   currentSongIndex = 0;
        // }
        // currentSong = playlist.get(currentSongIndex);
        // model.playMusic(currentSong);
      }
    }
  }

  class previousSongListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == view.previousSongBtn) {

        model.previousSongMusic();
        //model.stop(currentSong);
       //model.songSelected = false;

        //currentSongIndex--;
        //if (currentSongIndex < 0) {
        //  currentSongIndex = playlist.size() - 1;
        //}
        //currentSong = playlist.get(currentSongIndex);
        //model.playMusic(currentSong);
      }
    }
  }

}