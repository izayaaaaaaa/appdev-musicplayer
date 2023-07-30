// action words
// import java.util.List;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MP3PlayerModel {
  Boolean songSelected = false,
          songPaused = false;
  Long currentFrame;
  Song currentSong;
  private Song s;

  public void pauseMusic(Song s) {
    if (s != null && s.getSongClip().isRunning()) {
      currentFrame = s.getSongClip().getMicrosecondPosition();
      s.getSongClip().stop();
      songPaused = true;
    }
  } 

  public void playMusic(Song s) {
    System.out.println("playMusic model method");
    if (!songSelected && s != null) {  
      s.getSongClip().start();  // Start playing the song selected
      songSelected = true;  // Song has been selected
      songPaused = false;   // Song is not paused
    } else if (songSelected && songPaused && s != null) { 
      resumePlay(s);
    }
  }

  public void resumePlay(Song s) {
    if (s != null) {
      try {
        s.getSongClip().close();
        resetAudioStream(s);
        s.getSongClip().setMicrosecondPosition(currentFrame);
        s.getSongClip().start(); // Start playing the song after resuming
        songPaused = false;   // Song is not paused anymore

      } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
        System.out.println("resumePlay() error");
        e1.printStackTrace();
      }
    }
  }
  
  public void resetAudioStream(Song s) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    if (s != null) {
      s.setSongStream();
      s.getSongClip().open(s.getSongStream());
    }
  }

  // ... (other class members)
}