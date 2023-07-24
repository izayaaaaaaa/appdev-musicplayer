public class MainApp {
    public static void main(String[] args) {
        MP3PlayerModel model = new MP3PlayerModel();
        MP3PlayerView view = new MP3PlayerView();
        // MP3PlayerController controller = new MP3PlayerController(model, view);

        // Set up event listeners for user interactions
        // view.setPlayButtonListener(controller::onPlayButtonClicked);
        // view.setPauseButtonListener(controller::onPauseButtonClicked);
        // view.setSongSelectListener(controller::onSongSelected);

        // Display the initial UI
        view.showPlayButton();
        view.showPlaylist(model.getPlaylist());
    }
}
