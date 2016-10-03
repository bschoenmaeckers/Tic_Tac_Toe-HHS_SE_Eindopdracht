package TicTacToe.gui;

import TicTacToe.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JButton[][] Buttons = //x,y
            {
                    {A1,B1,C1},
                    {A2,B2,C2},
                    {A3,B3,C3}
            };

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

        int x,y = 0;
        for (JButton[] row : Buttons) {
            for (JButton button : row){
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doMove(new Integer(x++),new Integer(y++));
                    }
                });
            }
        }

//        A1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                doMove(0,0);
//            }
//        });
//        A2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                doMove(0,1);
//            }
//        });
//        A3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                doMove(0,2);
//            }
//        });
//
//        B1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                doMove(1,0);
//            }
//        });
//        B2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                doMove(1,1);
//            }
//        });
//        B3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                doMove(1,2);
//            }
//        });
//
//        C1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                doMove(2,0);
//            }
//        });
//        C2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                doMove(2,1);
//            }
//        });
//        C3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                doMove(2,2);
//            }
//        });

                setVisible(true);

        }

    public abstract void doMove(int x, int y);

    public void updateScreen(){
        for (int i = 0; i < Main.game.getField().length; i++) {

        }
    }

    public abstract void stopGame();
}
