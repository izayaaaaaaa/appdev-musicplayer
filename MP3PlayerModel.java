import java.util.List;

public class MP3PlayerModel {
    private boolean isPlaying;
    private String currentSong;
    private List<String> playlist;
    
    public boolean isPlaying() {
        return isPlaying;
    }
    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
    public String getCurrentSong() {
        return currentSong;
    }
    public void setCurrentSong(String currentSong) {
        this.currentSong = currentSong;
    }
    public List<String> getPlaylist() {
        return playlist;
    }
    public void setPlaylist(List<String> playlist) {
        this.playlist = playlist;
    }

    // Constructor, getters, setters, and methods for managing the MP3 player data
    
}