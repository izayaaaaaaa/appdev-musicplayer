package com;

// action words
// import java.util.List;

// import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
// import javax.sound.sampled.LineUnavailableException;
// import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.BufferedReader;
import java.io.File;
// import java.io.IOException;
// import javax.sound.sampled.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
// import javax.sound.sampled.UnsupportedAudioFileException;


public class MP3PlayerModel {
  Boolean songSelected = false,
          songPaused = false;
  Long currentFrame;
  String sname;
  //String lyrics;

  //public String lyrics;
  public Song s;
  public AudioInputStream songStream ;
  public Clip songClip;
  public FileReader reader;
  public BufferedReader br;
  public StringBuffer sb;

  public int s_id = 1;
  
  
  /*
   * PAUSE MUSIC
   */

  public String lyricsProcess(int id){
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicplayer");
    EntityManager em = emf.createEntityManager();

    int j;

    if(id <= -1){
      s_id = 1;
    }
    else{
      s_id = id;
    }

    s = em.find(Song.class, s_id);

     try {
      
      reader = new FileReader(new File(s.getLyricsPath()).getAbsolutePath());
      br = new BufferedReader(reader);
      sb = new StringBuffer(br.readLine());
      String storedLyrics = null;

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

  public void fetchSong(int id){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicplayer");
    EntityManager em = emf.createEntityManager();
    

    if(id <= -1){
      s_id = 1;
    }
    else{
      s_id = id;
    }

    s = em.find(Song.class, s_id);

    try{

      songStream = AudioSystem.getAudioInputStream(new File(s.getSongPath()).getAbsoluteFile());
      songClip = AudioSystem.getClip();
      songClip.open(songStream);

    } catch (Exception ex) {

      ex.printStackTrace();
    }
    
  }

  public void pauseMusic(){

      EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicplayer");
      EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      System.out.println("Song ID:" + s.getId());

      if (s != null && songClip.isRunning()) {

        currentFrame = songClip.getMicrosecondPosition();
        System.out.println("Current Frame is " + currentFrame);

        songClip.stop();
        songPaused = true;
      }

      em.getTransaction().commit();

    } catch (Exception e) {
      // TODO: handle exception

      if(em.getTransaction() != null){
        em.getTransaction().rollback();
      }

      e.printStackTrace();
    } finally {
      em.close();
      emf.close();
    }
  }

  /*
   * PLAY MUSIC
   */

  public void playMusic() {
    System.out.println("playMusic model method");

      EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicplayer");
      EntityManager em = emf.createEntityManager();

      try {

        em.getTransaction().begin();
        fetchSong(s_id);
        //lyricsProcess(s_id);
        

        if (!songSelected) {  

          System.out.println("Song Path: " + s.getSongPath());
          fetchSong(s_id);   
          // reader = new FileReader(new File(s.getLyricsPath()).getAbsolutePath());
          // br = new BufferedReader(reader);
          // System.out.println("Lyrics: "  + br.readLine());
          // br.close();


          songClip.start();
          songSelected = true;  // Song has been selected
          songPaused = false;   // Song is not paused

        } else if (songSelected && songPaused && s != null){
          resumePlay();
        }

        //lyricsProcess(s_id);

        // while(lyricsProcess(s_id) == null){
        //   break;
        // }

      } catch (Exception ex) {

        if(em.getTransaction() != null){

          em.getTransaction().rollback();

        }
          ex.printStackTrace();

      } finally {
        em.close();
        emf.close();
      }

  }

  /*
   * RESUME MUSIC
   */

  public void resumePlay() {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicplayer");
    EntityManager em = emf.createEntityManager();

    try {

      em.getTransaction().begin();


      if(s != null){

        try {
          songClip.close();
          resetAudioStream();
          songClip.setMicrosecondPosition(currentFrame);
          songClip.start();
          songPaused = false;
          
        } catch (Exception e1) {

          System.out.println("resumePlay() error");

          e1.printStackTrace();

        }
      }

      
    } catch (Exception e) {

        if(em.getTransaction() != null){

          em.getTransaction().rollback();

        }

        e.printStackTrace();

    } finally {
      em.close();
      emf.close();
    }

  }

  /*
   * RESET AUDIO STREAM MUSIC
   */
  
  public void resetAudioStream(){

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicplayer");
    EntityManager em = emf.createEntityManager();

    try {
      
      em.getTransaction().begin();

      s = em.find(Song.class, s_id);

      if (s != null){
  
        fetchSong(s_id);

      }


    } catch (Exception e) {
      // TODO: handle exception
      if(em.getTransaction() != null){
          em.getTransaction().rollback();
      }

      e.printStackTrace();

    } finally {
      em.close();
      emf.close();
    }

  }

  public void fastForwardMusic() {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicplayer");
    EntityManager em = emf.createEntityManager();

    try {

        em.getTransaction().begin();

      if (s != null){

        long currentPosition = songClip.getMicrosecondPosition();
        long newPosition = currentPosition + 1000000;
        
        if(newPosition > songClip.getMicrosecondLength()){
          newPosition = songClip.getMicrosecondLength();
        }

        songClip.setMicrosecondPosition(newPosition);

      }


    } catch (Exception e) {
      // TODO: handle exception

      if(em.getTransaction() != null){
          em.getTransaction().rollback();
      }

      e.printStackTrace();

    } finally {
      em.close();
      emf.close();
    }
  }

  public void backtrackMusic() {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicplayer");
    EntityManager em = emf.createEntityManager();

    try {

      em.getTransaction().begin();

      if (s != null){

        long currentPosition = songClip.getMicrosecondPosition();
        long newPosition = currentPosition - 1000000;
        
        if(newPosition < 0){
          newPosition = 0;
        }
        songClip.setMicrosecondPosition(newPosition);

      }
      
    } catch (Exception e) {
      // TODO: handle exception

      if(em.getTransaction() != null){
          em.getTransaction().rollback();
      }

      e.printStackTrace();
    } finally {
      em.close();
      emf.close();
    }

    if (s != null) {
      // long currentPosition = s.getSongClip().getMicrosecondPosition();
      // long newPosition = currentPosition - 1000000; // Adjust the value as needed for the backtrack speed
      // if (newPosition < 0) {
      //   newPosition = 0; // Make sure we don't go before the start of the song
      // }
      // s.getSongClip().setMicrosecondPosition(newPosition);
    }
  }

  public void nextSongMusic(){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicplayer");
    EntityManager em = emf.createEntityManager();
    Query query = em.createQuery("SELECT COUNT(s) FROM Song s");
    Long recordCount = (Long) query.getSingleResult();

    try {

      songClip.stop();
      em.getTransaction().begin();
      s_id++;

      //Reaches MAX Highest ID
      if(s_id >= 4 || s_id == 3){
        s_id=0;
        System.out.println("Test 1: "+ s_id);
      } 

      fetchSong(s_id);
      songClip.start();


    } catch (Exception e) {
      // TODO: handle exception
      if(em.getTransaction() != null){
        em.getTransaction().rollback();
      }

      e.printStackTrace();
    } finally {
      em.close();
      emf.close();
    }
  }

  public void previousSongMusic(){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicplayer");
    EntityManager em = emf.createEntityManager();
    Query query = em.createQuery("SELECT COUNT(s) FROM Song s");
    Long recordCount = (Long) query.getSingleResult();

    try {

      songClip.stop();
      em.getTransaction().begin();
      s_id--;

      //Reaches MAX Lowest ID
      if(s_id >= 4 || s_id == -1){
        s_id=2;
        System.out.println("Test 1: "+ s_id);
      } 

      fetchSong(s_id);
      songClip.start();

      
    } catch (Exception e) {
      // TODO: handle exception
      if(em.getTransaction() != null){
        em.getTransaction().rollback();
      }

      e.printStackTrace();
    } finally {
      em.close();
      emf.close();
    }
  }

  public void stop() {
    if (s != null) {
      //s.getSongClip().stop();
    }
  }


}