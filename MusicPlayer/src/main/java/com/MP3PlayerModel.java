package com;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class MP3PlayerModel {
  Boolean songSelected = false,
          songPaused = false,
          songChanged = false;

  Long currentFrame;
  String sname;

  public AudioInputStream songStream ;
  public Clip songClip;
  public FileReader reader;
  public BufferedReader br;
  public StringBuffer sb;

  public int s_id;
  
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public void indexChanged(int id){
    if(s_id != id){
      songChanged = true;
      System.out.println("Song changed");
    }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public void fetchID(int id){    
    //Set to first song of no specific song has been selected
    s_id = id;
  }

  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public Song getSong(){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicplayer");
    EntityManager em = emf.createEntityManager();

    if(songChanged){
      fetchID(s_id);
    }

    Song s = em.find(Song.class, s_id);

  
    if (songClip == null || songStream == null || !songClip.isRunning() && s_id != -1) {
        try {
            stop();
            songStream = AudioSystem.getAudioInputStream(new File(s.getSongPath()).getAbsoluteFile());
            songClip = AudioSystem.getClip();
            songClip.open(songStream);
            songClip.setFramePosition(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    return s;
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public String fetchAlbumCover(int id){
    Song s = getSong();

    return s.getAlbumCover();
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public String lyricsProcess(int id){
    Song s = getSong();

    try {
      String storedLyrics = "";

      reader = new FileReader(new File(s.getLyricsPath()).getAbsolutePath());
      br = new BufferedReader(reader);
      sb = new StringBuffer(storedLyrics);
    
      System.out.println("br read = " + br.read());

      while((storedLyrics = br.readLine())!=null){
        sb.append(storedLyrics+"\n");
      }

      br.close();   
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    } 

    return sb.toString();

  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public void playMusic() {
    System.out.println("Music played: s_id " + s_id);

    Song s = getSong();

    if (!songSelected && !songPaused && s != null) {
        songClip.start();
        songPaused = false;
        songSelected = true;
    } else if (songPaused && s != null) {
        resumePlay();
        songPaused = false;
    }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public void pauseMusic(){
    Song s = getSong();

    if (s != null && songClip.isRunning()) {
        currentFrame = songClip.getMicrosecondPosition();
        System.out.println("Current Frame is " + currentFrame);
        songClip.stop();
        songPaused = true;
    }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public void resumePlay() {
    Song s = getSong();
    if (s != null) {
        try {
            songStream = AudioSystem.getAudioInputStream(new File(s.getSongPath()).getAbsoluteFile());
            songClip = AudioSystem.getClip();
            songClip.open(songStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(songChanged){
          currentFrame = 0L;
          songChanged = false;
        }
        songClip.setMicrosecondPosition(currentFrame); // Set the clip's position to the previously paused position
        songClip.start();
        
    }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public void fastForwardMusic() {
    Song s = getSong();
    if (s != null){

      long currentPosition = songClip.getMicrosecondPosition();
      long newPosition = currentPosition + 1000000;
      
      if(newPosition > songClip.getMicrosecondLength()){
        newPosition = songClip.getMicrosecondLength();
      }

      songClip.setMicrosecondPosition(newPosition);

    }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public void backtrackMusic() {
    Song s = getSong();
    if (s != null){

      long currentPosition = songClip.getMicrosecondPosition();
      long newPosition = currentPosition - 1000000;
      
      if(newPosition < 0){
        newPosition = 0;
      }
      songClip.setMicrosecondPosition(newPosition);

    }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public void stop() {
    if (songClip != null && songClip.isRunning()) {
      songClip.setMicrosecondPosition(0);
      songClip.stop();
      songClip.close();
      try {
        songStream.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      songStream = null;
      songClip = null;
      songSelected = false;
      songPaused = false;
    }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public long getSongDuration() {
    if (songClip != null) {
      return songClip.getMicrosecondLength() / 1_000_000; // Convert to seconds
    }
    return 0;
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Method to get the current position of the currently playing song
  public long getSongPosition() {
    if (songClip != null) {
      return songClip.getMicrosecondPosition() / 1_000_000; // Convert to seconds
    }
    return 0;
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public String fetchSongName(int id) {
    Song s = getSong();

    return s.getSongTitle();
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public String fetchArtistName(int id) {
    Song s = getSong();

    return s.getArtistName();
  }

}