public class MainApp {
  public static void main(String[] args) {
    MP3PlayerModel model = new MP3PlayerModel();

    MP3PlayerView view = new MP3PlayerView(model);
    // show gui through a view class method (not the constructor)

    Song s = new Song("song", "songPath");
  }
}