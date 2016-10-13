package TicTacToe.GameControler;

import TicTacToe.Main;

import javax.swing.*;
import java.util.Random;

public class SinglePlayerController extends GameController {


    public SinglePlayerController(Tile startingTurn) {
        super(startingTurn);
    }

    /**
     * Do move by user and start moveByAI
     *
     * @param positionX horizontal position
     * @param positionY vertical position
     * @return boolean, return true whe move was successful.
     */
    @Override
    public boolean move(int positionX, int positionY) {
        boolean result = super.move(positionX, positionY);

        if (result && !isGameEnded())
            //Update Screen and do AI move.
            SwingUtilities.invokeLater(() -> SwingUtilities.invokeLater(this::moveByAI));

        return result;
    }

    /**
     * Wait 700ms and do a random move
     */
    private void moveByAI() {
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Random rand = new Random();

        int x, y;

        do {
            x = rand.nextInt(3);
            y = rand.nextInt(3);
        } while (!this.isEmptyTile(x, y));

        super.move(x, y);

        //Check if game screen is still open.
        if (Main.gameScreen != null)
            Main.gameScreen.updateScreen(this);
    }
}
