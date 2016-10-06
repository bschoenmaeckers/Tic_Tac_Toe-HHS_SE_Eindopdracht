package TicTacToe.gui;

import TicTacToe.GameControler.GameController;
import TicTacToe.GameControler.MultiplayerClientController;
import TicTacToe.Main;

import javax.websocket.DeploymentException;
import java.io.IOException;

public class MultiplayerClientGameScreen extends OnlineGameScreen{

    public MultiplayerClientGameScreen() throws IOException, DeploymentException {
        super();

        Main.game = new MultiplayerClientController(GameController.Tile.O);
    }

    @Override
    public void gameOver(GameController game) {

    }

    @Override
    public void updateScreen(GameController game) {
        super.updateScreen(game);

        if (game.getCurrentState() == GameController.State.CROSS)
            currentTurn.setText("Your turn");
        else if (game.getCurrentState() == GameController.State.CIRCLE)
            currentTurn.setText("Other player");
    }
}
