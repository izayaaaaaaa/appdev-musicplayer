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
      resetAudioStream(s);
      s.getSongClip().setMicrosecondPosition(0L);

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