package TicTacToe.gui;

import TicTacToe.GameControler.GameController;
import TicTacToe.GameControler.SinglePlayerController;
import TicTacToe.Main;

public class SinglePlayerScreen extends OfflineGameScreen {



    public SinglePlayerScreen() {
        super();

        Main.game = new SinglePlayerController(GameController.Tile.O);
    }

    @Override
    public void doMove(int x, int y) {
        System.out.println("Click! x = " + x + " y = " + y);

        if (Main.game.move(x, y)) {
            this.updateScreen(Main.game);
        }
    }
}
