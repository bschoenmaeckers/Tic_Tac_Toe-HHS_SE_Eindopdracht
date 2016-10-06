package TicTacToe.gui;

import TicTacToe.GameControler.GameController;
import TicTacToe.GameControler.MultiplayerHostController;
import TicTacToe.Main;

import javax.websocket.DeploymentException;

public class MultiplayerHostGameScreen extends OnlineGameScreen {

    public MultiplayerHostGameScreen() throws DeploymentException {
        super();
        Main.game = new MultiplayerHostController(GameController.Tile.O);
        currentTurn.setText("Waiting for other player...");
    }

    @Override
    public void gameOver(GameController game) {

    }

    @Override
    public void doMove(int x, int y) {
        if(Main.game.getCurrentState() == GameController.State.CIRCLE)
            super.doMove(x, y);
    }

    @Override
    public void updateScreen(GameController game) {
        super.updateScreen(game);

        if (game.getCurrentState() == GameController.State.CROSS)
            currentTurn.setText("Other player");
        else if (game.getCurrentState() == GameController.State.CIRCLE)
            currentTurn.setText("Your turn");
    }

    @Override
    public void stopGame() {
        ((MultiplayerHostController) Main.game).getServer().stop();
        super.stopGame();
    }
}
