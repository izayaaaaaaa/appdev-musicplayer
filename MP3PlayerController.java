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
    
    Song currentSong = new Song("Anti Hero", "Taylor Swift - Anti-Hero (Official Music Video).wav");

    if (e.getSource() == view.playBtn) {

      System.out.println("Play button clicked");
      // output to console the current song title and path
      System.out.println("Current song: " + currentSong.getSong() + "; " + currentSong.getSongPath());
      
      
      
      model.playMusic(currentSong);
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