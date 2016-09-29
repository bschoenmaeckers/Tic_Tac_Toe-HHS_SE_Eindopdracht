package gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Bas Schoenmaeckers on 29-9-2016.
 */
public class MainMenu extends JFrame {
    private JButton singlePlayerButton;
    private JPanel panel1;
    private JButton localMultiplayerButton;
    private JButton hostGameButton;
    private JButton joinGameButton;
    private JButton scoresButton;
    private JButton optionsButton;

    public MainMenu() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel1);
        setResizable(false);
        setVisible(true);
        pack();

        //center window
        setLocationRelativeTo(null);
    }
}
