import java.awt.*;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MP3PlayerView extends JFrame{
    JPanel panel1, panel2, panelA, panelB, panelC, panelD;
    JButton playBtn, pauseBtn, fastForwardBtn, backtrackBtn, nextSongBtn, previousSongBtn;
    JTable showPlaylist;
    JScrollPane sp;
    JProgressBar pb;

    public MP3PlayerView(MP3PlayerModel model) {
        panel1 = new JPanel(new GridBagLayout());
        panel1.setBackground(Color.BLUE);
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        

        c.gridx = 0;
        c.gridy = 0;
        panelA = new JPanel();
        panelA.setBackground(Color.GREEN);
        panel1.add(panelA, c);

        c.gridx = 0;
        c.gridy = 1;
        panelB = new JPanel();
        panelB.setBackground(Color.YELLOW);
        panel1.add(panelB, c);

        c.gridx = 0;
        c.gridy = 2;
        panelC = new JPanel();
        panelC.setBackground(Color.ORANGE);
        panel1.add(panelC, c);

        c.gridx = 0;
        c.gridy = 3;
        panelD = new JPanel();
        panelD.setBackground(Color.PINK);

        showPreviousSongButton();
            panelD.add(previousSongBtn);
        showBacktrackButton();
            panelD.add(backtrackBtn);
        showPlayButton();
            panelD.add(playBtn);
        playBtn.addActionListener(new MP3PlayerController(model, this));
        showFastForwardButton();
            panelD.add(fastForwardBtn);
        showNextSongButton();
            panelD.add(nextSongBtn);

        panel1.add(panelD, c);

        panel2 = new JPanel(new BorderLayout());
        panel2.setBackground(Color.RED);

        this.setLayout(new GridLayout(1, 2));
        this.add(panel1);
        this.add(panel2);

        this.setTitle("MP3 Player");
        this.setSize(500, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void showCurrentSong(String songName) {
        // Display the current song on the UI
    }

    public void showPlayButton() {
        // Display the play button on the UI
        playBtn = new JButton("Play");
    }

    public void showPauseButton() {
        pauseBtn = new JButton("Pause");
    }

    public void showFastForwardButton(){
        //Display the fast forward button on the UI
        fastForwardBtn = new JButton("FastForward");

    }

    public void showBacktrackButton(){
        //Display the backtrack button on the UI
        backtrackBtn = new JButton("Backtrack");
    }

    public void showNextSongButton(){
        //Display the Next Song button on the UI
        nextSongBtn = new JButton("Next Song");
    }

    public void showPreviousSongButton(){
        //Display the Previous Song button on the UI
        previousSongBtn = new JButton("Previous Song");
    }

    public void showProgressBar(){
        //Display the Progress Bar on the UI

        //CurrentSong Min and Max Duration
        //JProgressbar(min, max)
        pb = new JProgressBar(); //Pass as parameter for current song time duration
    }


    public void showPlaylist(List<String> playlist) {
        // Display the playlist on the UI
        showPlaylist = new JTable(); 
        sp = new JScrollPane(showPlaylist);
    }

    // Other methods to handle user interactions and display updates




}
