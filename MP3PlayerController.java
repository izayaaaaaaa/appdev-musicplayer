// connects the physical form to the action

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MP3PlayerController implements ActionListener {
  private MP3PlayerModel model;
  private MP3PlayerView view;

  Song currentSong = new Song("Anti Hero", "Taylor Swift - Anti-Hero (Official Music Video).wav");
  
  public MP3PlayerController(MP3PlayerModel model, MP3PlayerView view) {
    this.model = model;
    this.view = view;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == view.playBtn) {

      System.out.println("Play button clicked");
      // output to console the current song title and path
      System.out.println("Current song: " + currentSong.getSong() + "; " + currentSong.getSongPath());
      
      
      
      model.playMusic(currentSong);
    }
  }
}