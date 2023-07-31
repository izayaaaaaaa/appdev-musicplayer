package com;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

  public Song(int id, String songTitle, String songPath) {
    this.id = id;
    this.songTitle = songTitle;
    this.songPath = songPath;
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
