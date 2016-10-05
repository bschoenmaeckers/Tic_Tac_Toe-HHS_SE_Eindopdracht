package TicTacToe.gui;

import TicTacToe.Main;

public abstract class OfflineGameScreen extends GameScreen {

    @Override
    public void doMove(int x, int y) {
        if (Main.game.move(x, y)) {
            this.updateScreen(Main.game);
        }
    }
}
