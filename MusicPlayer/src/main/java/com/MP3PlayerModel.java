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

  private AudioInputStream songStream;
  private Clip songClip;
  // private Song s;


  /*
   * PAUSE MUSIC
   */

  // public void pauseMusic(Song s) {
  //   if (s != null && s.getSongClip().isRunning()) {
  //     currentFrame = s.getSongClip().getMicrosecondPosition();
  //     s.getSongClip().stop();
  //     songPaused = true;
  //   }
  // } 

  /*
   * PLAY MUSIC
   */

  public void playMusic(Song s) {
    System.out.println("playMusic model method");

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicplayer");
    EntityManager em = emf.createEntityManager();

    if (!songSelected && s != null) {  

      try {

        em.getTransaction().begin();

        s = new Song();

        s.setSongTitle("heey");
        s.setSongPath("heey");
        
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
    
      // s.getSongClip().start();  // Start playing the song selected
      // songSelected = true;  // Song has been selected
      // songPaused = false;   // Song is not paused
    } else if (songSelected && songPaused && s != null) { 
      //resumePlay(s);
    }
  }

  /*
   * RESUME MUSIC
   */

  // public void resumePlay(Song s) {
  //   if (s != null) {
  //     try {
  //       s.getSongClip().close();
  //       resetAudioStream(s);
  //       s.getSongClip().setMicrosecondPosition(currentFrame);
  //       s.getSongClip().start(); // Start playing the song after resuming
  //       songPaused = false;   // Song is not paused anymore

  //     } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
  //       System.out.println("resumePlay() error");
  //       e1.printStackTrace();
  //     }
  //   }
  // }

  /*
   * RESET AUDIO STREAM MUSIC
   */
  
  // public void resetAudioStream(Song s) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
  //   if (s != null) {
  //     s.setSongStream();
  //     s.getSongClip().open(s.getSongStream());
  //   }
  // }

  // ... (other class members)
}