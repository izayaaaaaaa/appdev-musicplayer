import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MP3PlayerView extends JFrame{
    
    JPanel panel1, panel2, panelA, panelB, panelC, panelD;
    JButton showPlayBtn, showPauseBtn, showFastForwardBtn, showBacktrackBtn, showNextSongBtn, showPreviousSongBtn;
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

        showPreviousSongButton();
            panelD.add(showPreviousSongBtn);
        showBacktrackButton();
            panelD.add(showBacktrackBtn);
        showPlayButton();
            panelD.add(showPlayBtn);
        showPlayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // print something to the console
                System.out.println("Play button clicked");
            }
        });
        showFastForwardButton();
            panelD.add(showFastForwardBtn);
        showNextSongButton();
            panelD.add(showNextSongBtn);

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
        showPlayBtn = new JButton("Play");
    }

    public void showPauseButton() {
        showPauseBtn = new JButton("Pause");
    }

    public void showFastForwardButton(){
        //Display the fast forward button on the UI
        showFastForwardBtn = new JButton("FastForward");

    }

    public void showBacktrackButton(){
        //Display the backtrack button on the UI
        showBacktrackBtn = new JButton("Backtrack");
    }

    public void showNextSongButton(){
        //Display the Next Song button on the UI
        showNextSongBtn = new JButton("Next Song");
    }

    public void showPreviousSongButton(){
        //Display the Previous Song button on the UI
        showPreviousSongBtn = new JButton("Previous Song");
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
