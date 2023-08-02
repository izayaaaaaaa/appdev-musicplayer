package com;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;

@Entity
@Table(name = "MusicPlayer")
public class Song {

  @Id
  @Column(name = "MID", unique = true)
  private int id;

  @Column(name = "SongTitle", nullable = false)
  private String songTitle;

  @Column(name = "SongPath", nullable = false)
  private String songPath;

  @Column(name = "LyricsPath", nullable = false)
  private String lyricsPath;

  @Column (name = "ArtistName", nullable = false)
  private String artistName;

  @Column (name = "AlbumCover", nullable = false)
  private String albumCover;

  // private AudioInputStream songStream;
  // private Clip songClip;
  
  // public Song() {
  //   super();

    // this.songTitle = songTitle;
    // this.songPath = songPath;
    
    // try {
    //   setSongStream();
    //   setSongClip();
    //   getSongClip().open(getSongStream());
    // } catch (Exception e) {
    //   System.out.println("Song constructor error");
    //   e.printStackTrace();
  //   // }
  // }

  public Song(int id, String songTitle, String songPath, String lyricsPath, String artistName, String albumCover) {
    this.id = id;
    this.songTitle = songTitle;
    this.songPath = songPath;
    this.lyricsPath = lyricsPath;
    this.artistName = artistName;
    this.albumCover = albumCover;
  }

  public Song () {
    super();
  }

  public String getSongTitle() {
    return songTitle;
  }
  public void setSongTitle(String songTitle) {
    this.songTitle = songTitle;
  }

  public String getSongPath() {
    return songPath;
  }
  public void setSongPath(String songPath) {
    this.songPath = songPath;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLyricsPath() {
    return lyricsPath;
  }

  public void setLyricsPath(String lyricsPath) {
    this.lyricsPath = lyricsPath;
  }

  public String getArtistName() {
    return artistName;
  }

  public void setArtistName(String artistName) {
    this.artistName = artistName;
  }

  public String getAlbumCover() {
    return albumCover;
  }

  public void setAlbumCover(String albumCover) {
    this.albumCover = albumCover;
  }

  


  // public long getSongDuration() {
  //   try {
  //     AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(new File(songPath));
  //     return fileFormat.getFrameLength() * 1000L / fileFormat.getFormat().getFrameRate();
  //   } catch (UnsupportedAudioFileException | IOException e) {
  //       e.printStackTrace();
  //   }
  //   return 0L; // Return 0 if there is an error getting the duration
  // }

  // public AudioInputStream getSongStream() {
  //   return songStream;
  // }
  // public void setSongStream() throws UnsupportedAudioFileException, IOException {
  //   songStream = AudioSystem.getAudioInputStream(new File(songPath).getAbsoluteFile());
  // }
  
  // public Clip getSongClip() {
  //   return songClip;
  // }
  // public void setSongClip() throws LineUnavailableException {
  //   songClip = AudioSystem.getClip();
  // }
}
