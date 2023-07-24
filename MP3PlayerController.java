// connects the physical form to the action
public class MP3PlayerController {
    private MP3PlayerModel model;
    private MP3PlayerView view;

    public MP3PlayerController(MP3PlayerModel model, MP3PlayerView view) {
        this.model = model;
        this.view = view;
    }

    //Play Button
    public void onPlayButtonClicked() {
        model.setPlaying(true);
        view.showPlayPauseButton();
    }

    //Pause Button
    public void onPauseButtonClicked() {
        model.setPlaying(false);
        view.showPlayPauseButton();
    }

    public void onSongSelected(String songName) {
        model.setCurrentSong(songName);
        view.showCurrentSong(songName);
    }
}
