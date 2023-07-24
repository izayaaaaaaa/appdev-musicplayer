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
    JButton showPlay, showPause, showFastForward, showBacktrack, showNextSong, showPreviousSong;
    JTable showPlaylist;
    JScrollPane sp;
    JProgressBar pb;

    public MP3PlayerView() {
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
        panel1.add(panelD, c);
        panelD.add(showPlay);


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
        showPlay = new JButton("Play");

    }

    public void showPauseButton() {
        // Display the pause button on the UI
        showPause = new JButton("Pause");

    }

    public void showFastForward(){
        //Display the fast forward button on the UI
        showFastForward = new JButton("FastForward");

    }

    public void showBacktrack(){
        //Display the backtrack button on the UI
        showBacktrack = new JButton("Backtrack");
    }

    public void showNextSong(){
        //Display the Next Song button on the UI
        showNextSong = new JButton("Next Song");
    }

    public void showPreviousSong(){
        //Display the Previous Song button on the UI
        showPreviousSong = new JButton("Previous Song");
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
