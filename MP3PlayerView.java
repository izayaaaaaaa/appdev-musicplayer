// physical form
import java.awt.*;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MP3PlayerView extends JFrame{
    JPanel panel1, panel2, panelA, panelB, panelC, panelD;

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

    }

    public void showPauseButton() {
        // Display the pause button on the UI

    }

    public void showPlaylist(List<String> playlist) {
        // Display the playlist on the UI

    }
}
