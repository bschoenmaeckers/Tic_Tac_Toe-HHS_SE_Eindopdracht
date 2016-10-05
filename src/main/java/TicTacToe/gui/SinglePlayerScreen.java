package TicTacToe.gui;

import TicTacToe.GameControler.GameController;
import TicTacToe.GameControler.SinglePlayerController;
import TicTacToe.Main;

import javax.swing.*;

public class SinglePlayerScreen extends OfflineGameScreen {

    public SinglePlayerScreen() {
        super();

        Main.game = new SinglePlayerController(GameController.Tile.O);
        currentTurn.setText("Your turn");
    }

    @Override
    public void updateScreen(GameController game) {
        super.updateScreen(game);
        if (game.getCurrentState() == GameController.State.CROSS)
            currentTurn.setText("Computer");
        else if (game.getCurrentState() == GameController.State.CIRCLE)
            currentTurn.setText("Your turn");
    }

    @Override
    public void doMove(int x, int y) {
        if (Main.game.getCurrentState() == GameController.State.CIRCLE)
            super.doMove(x, y);
    }

    @Override
    public void gameOver(GameController game) {
        updateScreen(game);

        switch (game.getCurrentState()) {
            case END_DRAW:
                JOptionPane.showMessageDialog(this, "Draw!");
                break;
            case END_CIRCLE:
                JOptionPane.showMessageDialog(this, "You won!");
                break;
            case END_CROSS:
                JOptionPane.showMessageDialog(this, "You lost!");
                break;
        }
        stopGame();
    }
}
