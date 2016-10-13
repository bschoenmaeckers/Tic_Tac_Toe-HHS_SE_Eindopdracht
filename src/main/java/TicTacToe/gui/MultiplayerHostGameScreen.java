package TicTacToe.gui;

import TicTacToe.GameControler.GameController;
import TicTacToe.GameControler.MultiplayerHostController;
import TicTacToe.Main;

import javax.swing.*;
import javax.websocket.DeploymentException;

public class MultiplayerHostGameScreen extends GameScreen {

    /**
     * TODO: Add Javadoc
     *
     * @throws DeploymentException
     */
    public MultiplayerHostGameScreen() throws DeploymentException {
        super();
        Main.game = new MultiplayerHostController(GameController.Tile.O);
        currentTurn.setText("Waiting for other player...");
    }

    /**
     * * TODO: Add Javadoc
     *
     * @param game Current GameController
     */
    @Override
    public void gameOver(GameController game) {
        updateScreen(game);
        ((MultiplayerHostController) Main.game).updateClient();

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

    /**
     * * TODO: Add Javadoc
     *
     * @param x Horizontal position
     * @param y Vertical position
     */
    @Override
    public void doMove(int x, int y) {
        if (Main.game.getCurrentState() == GameController.State.CIRCLE)
            super.doMove(x, y);
    }

    /**
     * * TODO: Add Javadoc
     *
     * @param game Current GameController
     */
    @Override
    public void updateScreen(GameController game) {
        super.updateScreen(game);

        if (game.getCurrentState() == GameController.State.CROSS)
            currentTurn.setText("Other player");
        else if (game.getCurrentState() == GameController.State.CIRCLE)
            currentTurn.setText("Your turn");
    }

    /**
     * * TODO: Add Javadoc
     */
    @Override
    public void stopGame() {
        ((MultiplayerHostController) Main.game).stopGame();
        super.stopGame();
    }
}
