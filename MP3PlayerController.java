// connects the physical form to the action

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// import javax.swing.Action;

public class MP3PlayerController {
  private MP3PlayerModel model;
  private MP3PlayerView view;

  Song currentSong = new Song("Anti Hero", "Taylor Swift - Anti-Hero (Official Music Video).wav");
  
  public MP3PlayerController(MP3PlayerModel model, MP3PlayerView view) {
    this.model = model;
    this.view = view;

    // syncing the buttons created in the view to the listeners found in controller
    view.playBtn.addActionListener(new mainListener());
    view.pauseBtn.addActionListener(new mainListener());
    view.fastForwardBtn.addActionListener(new mainListener());
    view.backtrackBtn.addActionListener(new mainListener());
    view.nextSongBtn.addActionListener(new mainListener());
    view.previousSongBtn.addActionListener(new mainListener());
  }
  
  class mainListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

      if (e.getSource() == view.playBtn) {
        System.out.println("Play button clicked");
        // output to console the current song title and path
        System.out.println("Current song: " + currentSong.getSong() + "; " + currentSong.getSongPath());
        model.playMusic(currentSong);
      }
      else if (e.getSource() == view.pauseBtn) {
        System.out.println("Pause button clicked");
        model.pauseMusic(currentSong);
      }
      else if (e.getSource() == view.fastForwardBtn) {
        System.out.println("Fast forward button clicked");
        //Code for functionality below
      }
      else if (e.getSource() == view.backtrackBtn) {
        System.out.println("Backtrack button clicked");
        //Code for functionality below
      }
      else if (e.getSource() == view.nextSongBtn) {
        System.out.println("Next song button clicked");
        //Code for functionality below
      }
      else if (e.getSource() == view.previousSongBtn) {
        System.out.println("Previous song button clicked");
        //Code for functionality below
      }
    }
  }
  

  public void updateView() {
    // update view
    
  }
}