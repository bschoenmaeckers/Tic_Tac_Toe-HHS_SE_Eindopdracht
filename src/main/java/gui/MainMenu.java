package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SinglePlayerScreen();
            }
        });
    }

}
