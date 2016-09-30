package TicTacToe.gui;

import TicTacToe.Main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class GameScreen extends JFrame {

    private JPanel panel1;
    private JButton A1;
    private JButton C1;
    private JButton B1;
    private JButton B2;
    private JButton C2;
    private JButton A2;
    private JButton C3;
    private JButton B3;
    private JButton A3;

    public GameScreen() {
        setContentPane(panel1);
        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopGame();
                dispose();
                Main.gameScreen = null;
                Main.menu = new MainMenu();
            }
        });

        setVisible(true);
    }

    public abstract void stopGame();
}
