package com;

// action words
// import java.util.List;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;


public class MP3PlayerModel {
  Boolean songSelected = false,
          songPaused = false;
  Long currentFrame;
  Song currentSong;
  String sname;

  public Song s;
  public AudioInputStream songStream ;
  public Clip songClip;

  int s_id = 1;
  
  
  /*
   * PAUSE MUSIC
   */

  public void AudioProcess(){

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

      s = em.find(Song.class, s_id);

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

        s = em.find(Song.class, s_id);

        if (!songSelected) {  

          System.out.println("Song Path: " + s.getSongPath());
          AudioProcess();

          songClip.start();
          songSelected = true;  // Song has been selected
          songPaused = false;   // Song is not paused

        } else if (songSelected && songPaused && s != null){
          resumePlay();
        }


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

      s = em.find(Song.class, s_id);

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
  
        AudioProcess();

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

        s = em.find(Song.class, s_id);

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

        s = em.find(Song.class, s_id);

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
      s_id = s_id + 1;

      if(s_id>=recordCount.intValue()+1){
        s_id = 1;
      }

      s = em.find(Song.class, s_id);
      AudioProcess();
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
      s_id = s_id - 1;

      if(s_id<=0){
        s_id = recordCount.intValue();
      }

      s = em.find(Song.class, s_id);
      AudioProcess();
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