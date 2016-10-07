package TicTacToe.gui;

import TicTacToe.Main;

import javax.swing.*;
import javax.websocket.DeploymentException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

        hostGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.gameScreen = new MultiplayerHostGameScreen();
                    MainMenu.this.dispose();
                } catch (DeploymentException e1) {
                    JOptionPane.showMessageDialog(MainMenu.this,"Error starting server!","Error",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        joinGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu.this.dispose();

                try {
                    Main.gameScreen = new MultiplayerClientGameScreen();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (DeploymentException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

}
