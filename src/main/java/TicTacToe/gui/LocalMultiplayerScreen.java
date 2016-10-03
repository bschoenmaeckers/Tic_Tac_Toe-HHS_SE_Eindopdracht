package TicTacToe.gui;

import TicTacToe.GameControler.GameController;
import TicTacToe.Main;

public class LocalMultiplayerScreen extends OfflineGameScreen {
    public LocalMultiplayerScreen() {
        super();

        Main.game = new GameController(GameController.Tile.O);
    }
}
