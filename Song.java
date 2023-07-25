import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

public class Song {
  private

  private String song;
  private String songPath;
  private AudioInputStream songStream;
  private Clip songClip;

  public Song(String song, String songPath) {
    this.song = song;
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

  // implement the getter and setter of song, songPath, songStream, and songClip
  public String getSong() {
    return song;
  }
  public void setSong(String song) {
    this.song = song;
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
  public void setSongStream(AudioInputStream songStream) {
    this.songStream = songStream;
  }

  public Clip getSongClip() {
    return songClip;
  }
  public void setSongClip(Clip songClip) {
    this.songClip = songClip;
  }
}
