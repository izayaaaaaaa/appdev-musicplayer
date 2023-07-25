// action words
// import java.util.List;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MP3PlayerModel {

  Boolean songSelected = false;
  Long currentFrame;
  Song currentSong;

  public void pauseMusic(Song s) {
      currentFrame = s.getSongClip().getMicrosecondPosition();
      s.getSongClip().stop();
  } 

  public void playMusic(Song s) {
    System.out.println("playMusic model method");

    try {
      //Ensure that no songs are currently selected before playing a song
      //To avoid playing bug: Two songs playing at the same time
      if(songSelected == false){  
        s.getSongClip().start();  //Start playing the song selected
        songSelected = true;  //Song has been selected
      }
      else if(songSelected){ 
        s.getSongClip().close();   
        resetAudioStream(s);
        s.getSongClip().setMicrosecondPosition(0L);
      }

    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
      System.out.println("playMusic() error");
      e1.printStackTrace();
    }
  }
  
  public void resetAudioStream(Song s) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    s.setSongStream();
    s.getSongClip().open(s.getSongStream());
  }
}