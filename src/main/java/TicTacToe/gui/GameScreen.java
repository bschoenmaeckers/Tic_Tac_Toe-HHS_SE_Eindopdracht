package TicTacToe.gui;

import TicTacToe.GameControler.GameController;
import TicTacToe.Main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class GameScreen extends JFrame {

    JLabel currentTurn;
    private JPanel panel1;
    private JButton A1, B1, C1, A2, B2, C2, A3, B3, C3;
    private JButton[][] buttons;// y,x

    /**
     * Load new gamescreen and loads the buttons
     */
    public GameScreen() {
        setContentPane(panel1);
        setTitle("Tic Tac Toe");
        setResizable(false);
        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopGame();
            }
        });

        buttons = new JButton[][]{
                {A1, B1, C1},
                {A2, B2, C2},
                {A3, B3, C3}
        };

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                final int x = j;
                final int y = i;
                buttons[y][x].addActionListener(e -> doMove(x, y));
            }
        }

        setVisible(true);

    }

    /**
     * Move current action and visualize it to the board
     *
     * @param x Horizontal position
     * @param y Vertical position
     */
    public void doMove(int x, int y) {
        if (Main.game.move(x, y)) {
            this.updateScreen(Main.game);
        }
    }

    /**
     * Stop current game
     *
     * @param game Current GameController
     */
    public abstract void gameOver(GameController game);

    /**
     * Update gameboard with current field
     *
     * @param game Current GameController
     */
    public void updateScreen(GameController game) {
        if (!game.isGameEnded())
            currentTurn.setText(game.getCurrentState().name());

        int y = 0;
        for (JButton[] row : buttons) {
            int x = 0;
            for (JButton button : row) {
                if (game.getField()[x][y] != GameController.Tile.EMPTY)
                    button.setText(game.getField()[x][y].name());
                x++;
            }
            y++;
        }
    }

    /**
     * Stop current game and remove it from memory
     * Reopens the main menu
     */
    public void stopGame() {
        dispose();
        Main.gameScreen = null;
        Main.game = null;
        Main.menu = new MainMenu();
    }

}
