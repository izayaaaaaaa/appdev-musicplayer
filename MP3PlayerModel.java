// action words
// import java.util.List;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MP3PlayerModel {






  public void playMusic(Song s) {
    System.out.println("playMusic model method");

    try {
      s.getSongClip().close();
      resetAudioStream(currentSong);
      s.getSongClip().setMicrosecondPosition(currentFrame);

    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
      System.out.println("playMusic() error");
      e1.printStackTrace();
    }
  }



  // private boolean isPlaying;
  // private String currentSong;
  // private List<String> playlist;
  
  // public boolean isPlaying() {
  //   return isPlaying;
  // }
  // public void setPlaying(boolean isPlaying) {
  //   this.isPlaying = isPlaying;
  // }
  // public String getCurrentSong() {
  //   return currentSong;
  // }
  // public void setCurrentSong(String currentSong) {
  //   this.currentSong = currentSong;
  // }
  // public List<String> getPlaylist() {
  //   return playlist;
  // }
  // public void setPlaylist(List<String> playlist) {
  //   this.playlist = playlist;
  // }
}