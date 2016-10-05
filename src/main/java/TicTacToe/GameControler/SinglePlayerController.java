package TicTacToe.GameControler;

import TicTacToe.Main;

import javax.swing.*;
import java.util.Random;

public class SinglePlayerController extends GameController {


    public SinglePlayerController(Tile startingTurn) {
        super(startingTurn);
    }

    @Override
    public boolean move(int positionX, int positionY) {
        boolean result = super.move(positionX, positionY);


        if (result && !isGameEnded())
            SwingUtilities.invokeLater(this::moveByAI);

        return result;
    }
    
    private void moveByAI() {

        try {
            Thread.sleep(1000);
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
        Main.gameScreen.updateScreen(this);
    }
}
