import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Song {
  private String songTitle;
  private String songPath;
  private AudioInputStream songStream;
  private Clip songClip;

  public Song(String songTitle, String songPath) {
    this.songTitle = songTitle;
    this.songPath = songPath;
    
    try {
      setSongStream();
      setSongClip();
      getSongClip().open(getSongStream());
    } catch (Exception e) {
      System.out.println("Song constructor error");
      e.printStackTrace();
    }
  }

  public String getSong() {
    return songTitle;
  }
  public void setSong(String song) {
    this.songTitle = song;
  }

  public String getSongPath() {
    return songPath;
  }
  public void setSongPath(String songPath) {
    this.songPath = songPath;
  }

  public AudioInputStream getSongStream() {
    return songStream;
  }
  public void setSongStream() throws UnsupportedAudioFileException, IOException {
    songStream = AudioSystem.getAudioInputStream(new File(songPath).getAbsoluteFile());
  }
  
  public Clip getSongClip() {
    return songClip;
  }
  public void setSongClip() throws LineUnavailableException {
    songClip = AudioSystem.getClip();
  }
}
