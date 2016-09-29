package gui;

import javax.swing.*;

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
