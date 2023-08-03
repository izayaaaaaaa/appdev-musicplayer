package com;

public class MainApp {
  public static void main(String[] args) {

    PersistSongs storeData = new PersistSongs();
    MP3PlayerModel model = new MP3PlayerModel();
    MP3PlayerView view = new MP3PlayerView();
    MP3PlayerController controller = new MP3PlayerController(model, view);

    // update view
    //controller.updateView();
  }
}

// include the text area lyrics improve the ui

// fix the leftover bg crap of the music player

// fix the spacing and sizes of the song list

// settings for darkmode eme eme

// weird bottom bar bug

// song has to play automatically when next is clicked

