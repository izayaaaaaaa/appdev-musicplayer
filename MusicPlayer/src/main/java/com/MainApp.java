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