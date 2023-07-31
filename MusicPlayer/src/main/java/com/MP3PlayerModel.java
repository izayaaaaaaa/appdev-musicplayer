package com;

// action words
// import java.util.List;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

      s = em.find(Song.class, 1);

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

        s = new Song();

        if (!songSelected && s != null) {  
      
          s.setSongTitle("Anti Hero");
          s.setSongPath("Taylor Swift - Anti-Hero (Official Music Video).wav");
          
          System.out.println("Song Path: " + s.getSongPath());
          AudioProcess();

          songClip.start();
          songSelected = true;  // Song has been selected
          songPaused = false;   // Song is not paused

        } else if (songSelected && songPaused && s != null){
          resumePlay();
        }

        em.persist(s);
        em.getTransaction().commit();

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

      s = em.find(Song.class, 1);

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

      //em.persist(s);
      em.getTransaction().commit();
      
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

      s = em.find(Song.class, 1);

      if (s != null){
  
        AudioProcess();

      }

      //em.persist(s);
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

}