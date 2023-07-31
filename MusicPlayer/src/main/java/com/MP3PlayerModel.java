// action words
package com;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MP3PlayerModel {
  Boolean songSelected = false, songPaused = false;
  Long currentFrame;
  Song currentSong;
  private Song s;

  public void pauseMusic(Song s) {
    System.out.println("pauseMusic model method");
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
    System.out.println("resumePlay model method");
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
    System.out.println("resetAudioStream model method");
    if (s != null) {
      s.setSongStream();
      s.getSongClip().open(s.getSongStream());
    }
  }

  public void fastForwardMusic(Song s) {
    System.out.println("fastForwardMusic model method");
    if (s != null) {
      long currentPosition = s.getSongClip().getMicrosecondPosition();
      long newPosition = currentPosition + 1000000; // Adjust the value as needed for the fast forward speed
      if (newPosition > s.getSongClip().getMicrosecondLength()) {
        newPosition = s.getSongClip().getMicrosecondLength(); // Make sure we don't go beyond the song length
      }
      s.getSongClip().setMicrosecondPosition(newPosition);
    }
  }

  public void backtrackMusic(Song s) {
    System.out.println("backtrackMusic model method");
    if (s != null) {
      long currentPosition = s.getSongClip().getMicrosecondPosition();
      long newPosition = currentPosition - 1000000; // Adjust the value as needed for the backtrack speed
      if (newPosition < 0) {
        newPosition = 0; // Make sure we don't go before the start of the song
      }
      s.getSongClip().setMicrosecondPosition(newPosition);
    }
  }
}