// connects the physical form to the action

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MP3PlayerController implements ActionListener {
  private MP3PlayerModel model;
  private MP3PlayerView view;
  
  public MP3PlayerController(MP3PlayerModel model, MP3PlayerView view) {
    this.model = model;
    this.view = view;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == view.playBtn) {

      System.out.println("Play button clicked");
      
      model.playMusic();

      // Logic for handling the Play button click
      // if (model.isPlaying()) {
        //     // If currently playing, pause the playback
        //     model.setPlaying(false);
        //     view.playBtn.setText("Play"); // Change button text to "Play"
        // } else {
          //     // If currently paused or stopped, start playback
          //     model.setPlaying(true);
          //     view.playBtn.setText("Pause"); // Change button text to "Pause"
    }
    else if (e.getSource() == view.pauseBtn) {
            System.out.println("Pause button clicked");
            //Code for functionality below

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