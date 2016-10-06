package TicTacToe.GameControler;

import TicTacToe.Main;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class SinglePlayerController extends GameController {


    public SinglePlayerController(Tile startingTurn) {
        super(startingTurn);
    }

    @Override
    public boolean move(int positionX, int positionY) {
        boolean result = super.move(positionX, positionY);

        Main.gameScreen.updateScreen(this);

        if (result && !isGameOver())
            moveByAI();

        return result;
    }


    private void moveByAI() {

        System.out.println("AI");
        Random rand = new Random();

        int x;
        int y;

        do {
            x = rand.nextInt(3);
            y = rand.nextInt(3);
        } while (!this.isEmptyTile(x, y));

        super.move(x, y);
    }
}
