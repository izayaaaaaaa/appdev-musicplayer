// connects the physical form to the action
package com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MP3PlayerController {
  private MP3PlayerModel model;
  private MP3PlayerView view;

  private List<Song> playlist;
  private int currentSongIndex;
  Song currentSong;

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

    view.playBtn.setVisible(true);
    view.pauseBtn.setVisible(false);

    playlist = new ArrayList<>();
    playlist.add(new Song("Anti Hero", "Taylor Swift - Anti-Hero (Official Music Video).wav"));
    playlist.add(new Song("Curious", "Curious - Hayley Kiyoko.wav" ));
    playlist.add(new Song("Sex (with my ex)", "Sex (with my ex) - Fletcher.wav"));
  
    currentSongIndex = 0;
    currentSong = playlist.get(currentSongIndex);
  }

  class playListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == view.playBtn) {
        System.out.println("Play button clicked");
        view.playBtn.setVisible(false);
        view.pauseBtn.setVisible(true);
        model.playMusic(currentSong);
      }
    }
  }

  class pauseListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == view.pauseBtn) {
        System.out.println("Pause button clicked");
        view.playBtn.setVisible(true);
        view.pauseBtn.setVisible(false);
        model.pauseMusic(currentSong);
      }
    }
  }

  class fastForwardListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == view.fastForwardBtn) {
        System.out.println("Fast forward button clicked");
        model.fastForwardMusic(currentSong);
      }
    }
  }

  class backtrackListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == view.backtrackBtn) {
        System.out.println("Backtrack button clicked");
        model.backtrackMusic(currentSong);
      }
    }
  }

  class nextSongListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == view.nextSongBtn) {
        System.out.println("Next song button clicked");
        currentSongIndex = (currentSongIndex + 1) % playlist.size(); // Loop back to the first song if at the end
        currentSong = playlist.get(currentSongIndex);
        // output the current song index
        System.out.println(currentSongIndex);
        model.playMusic(currentSong);
      }
    }
  }

  class previousSongListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == view.previousSongBtn) {
        System.out.println("Previous song button clicked");
        currentSongIndex = (currentSongIndex - 1) % playlist.size(); // Loop back to the last song if at the beginning
        currentSong = playlist.get(currentSongIndex);
      }
    }
  }
}