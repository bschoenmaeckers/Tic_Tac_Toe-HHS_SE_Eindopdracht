package TicTacToe.gui;

import TicTacToe.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JButton singlePlayerButton;
    private JPanel panel1;
    private JButton localMultiplayerButton;

    public MainMenu() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel1);
        setResizable(false);
        pack();

        //center window
        setLocationRelativeTo(null);
        setVisible(true);

        /*
          Start SinglePlayerScreen when button is pressed
         */
        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gameScreen = new SinglePlayerScreen();
                MainMenu.this.dispose();
            }
        });

        /*
          Start LocalMultiPlayerScreen when button is pressed
         */
        localMultiplayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gameScreen = new LocalMultiplayerScreen();
                MainMenu.this.dispose();
            }
        });
    }

}
