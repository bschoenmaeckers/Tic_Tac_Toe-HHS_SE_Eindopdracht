package TicTacToe.gui;

import TicTacToe.GameControler.GameController;
import TicTacToe.Main;

import javax.swing.*;

public class LocalMultiplayerScreen extends OfflineGameScreen {
    public LocalMultiplayerScreen() {
        super();

        Main.game = new GameController(GameController.Tile.O);
    }

    @Override
    public void gameOver(GameController game) {
        updateScreen(game);

        switch (game.getCurrentState()) {
            case END_DRAW:
                JOptionPane.showMessageDialog(this, "Draw!");
                break;
            case END_CIRCLE:
                JOptionPane.showMessageDialog(this, "Circle won!");
                break;
            case END_CROSS:
                JOptionPane.showMessageDialog(this, "Cross won!");
                break;
        }
        stopGame();
    }
}
