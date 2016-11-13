package TicTacToe.GameControler;

import TicTacToe.Main;

import javax.swing.*;
import java.util.Arrays;
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

    private boolean fakeMove(int positionX, int positionY, Tile tile) {
        Tile tilePos = field[positionX][positionY];
        State lastState = currentState;
        Tile[][] lastField = deepCopy(field);

        if (tilePos != Tile.EMPTY || isGameEnded()) {
            return false;
        } else {
            field[positionX][positionY] = tile;
            boolean result = checkCurrentState(false);
            currentState = lastState;
            field = lastField;
            return result;
        }
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

        int x = 0;
        int y = 0;
        boolean foundMove = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (fakeMove(i, j, Tile.X)) {
                    System.out.println("Found winning move!");
                    foundMove = true;
                    x = i;
                    y = j;
                    break;
                }
            }
            if (foundMove)
                break;
        }

        if (this.isEmptyTile(1, 1)) {
            System.out.println("Taking middle tile!");
            foundMove = true;
            x = y = 1;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (fakeMove(i, j, Tile.O)) {
                    System.out.println("Losing! Taking action!");
                    foundMove = true;
                    x = i;
                    y = j;
                    break;
                }
            }
            if (foundMove)
                break;
        }

        if (!foundMove) {
            do {
                x = rand.nextInt(3);
                y = rand.nextInt(3);
            } while (!this.isEmptyTile(x, y));
        }

        super.move(x, y);

        //Check if game screen is still open.
        if (Main.gameScreen != null)
            Main.gameScreen.updateScreen(this);
    }

    private Tile[][] deepCopy(Tile[][] original) {
        if (original == null) {
            return null;
        }

        final Tile[][] result = new Tile[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }
}
