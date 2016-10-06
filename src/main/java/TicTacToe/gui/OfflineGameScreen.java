package TicTacToe.gui;

import TicTacToe.Main;

public abstract class OfflineGameScreen extends GameScreen {

    /**
     * Do the move and update the screen
     * @param x Horizontal position
     * @param y Vertical position
     */
    @Override
    public void doMove(int x, int y) {
        if (Main.game.move(x, y)) {
            this.updateScreen(Main.game);
        }
    }
}
