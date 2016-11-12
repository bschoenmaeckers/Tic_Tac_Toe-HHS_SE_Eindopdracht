package TicTacToe.GameControler;

import TicTacToe.Main;

public class GameController {

    /**
     * field[horizontal][vertical]
     */
    protected Tile[][] field;
    protected int moves = 0;
    protected State currentState;
    private int size = 3;

    public GameController(Tile startingTurn) {

        this.currentState = (startingTurn == Tile.O) ? State.CIRCLE : State.CROSS;

        this.field = new Tile[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                field[x][y] = Tile.EMPTY;
            }
        }
    }

    /**
     * @param positionX Horizontal position
     * @param positionY Vertical position
     * @return true when current tile is empty
     */
    public boolean isEmptyTile(int positionX, int positionY) {
        return field[positionX][positionY] == Tile.EMPTY;
    }

    /**
     * @return current state of game
     */
    public State getCurrentState() {
        return currentState;
    }

    /**
     * @param positionX horizontal position
     * @param positionY vertical position
     * @return boolean, return true whe move was successful.
     */
    public boolean move(int positionX, int positionY) {
        Tile tile = field[positionX][positionY];

        if (tile != Tile.EMPTY || isGameEnded()) {
            return false;
        } else {
            field[positionX][positionY] = (currentState == State.CIRCLE) ? Tile.O : Tile.X;
            moves++;
            printCurrentState();
            checkCurrentState(true);
            return true;
        }
    }

    /**
     * @return current gameField array
     */
    public Tile[][] getField() {
        return field;
    }

    /**
     * Check current playboard and define if there is a different state
     * Updates currentState variable
     */
    protected boolean checkCurrentState(boolean realMove) {
        boolean won = false;

        //Horizontal lines
        for (int y = 0; y < size; y++) {

            Tile startTile = field[0][y];
            for (int x = 0; x < size; x++) {
                if (startTile != field[x][y] || startTile == Tile.EMPTY)
                    break;
                else if (x == 2)
                    won = true;

            }

            if (won)
                break;

        }

        //Vertical lines
        for (int x = 0; x < size; x++) {
            Tile startTile = field[x][0];
            for (int y = 0; y < size; y++) {
                if (startTile != field[x][y] || startTile == Tile.EMPTY)
                    break;
                else if (y == 2)
                    won = true;
            }

            if (won)
                break;
        }

        // Diagonal lines
        Tile typeLeft = field[0][0];
        Tile typeRight = field[2][0];

        // Check winning condition diagonal \
        boolean leftWon = true;
        boolean rightWon = true;

        for (int x = 0; x < size; x++) {
            if (typeLeft == Tile.EMPTY || typeLeft != field[x][x])
                leftWon = false;

            if (typeRight == Tile.EMPTY || typeRight != field[(size - 1) - x][x])
                rightWon = false;
        }
        if (leftWon || rightWon)
            won = true;


        if (won && realMove) {
            System.out.println("Game Won!");
            if (currentState == State.CROSS) {
                currentState = State.END_CROSS;
                Main.gameScreen.gameOver(this);
            } else {
                currentState = State.END_CIRCLE;
                Main.gameScreen.gameOver(this);
            }
        } else if (moves >= size * size) {
            System.out.println("Draw!");
            currentState = State.END_DRAW;
            Main.gameScreen.gameOver(this);
        } else {
            currentState = getOppositePlayer(currentState);
        }
        return won;
    }

    /**
     * Returns of the game is finished
     *
     * @return true when game is flagged as ended
     */
    public boolean isGameEnded() {
        return currentState == State.END_CIRCLE || currentState == State.END_CROSS || currentState == State.END_DRAW;
    }

    /**
     * @param player player State to be flipped
     * @return Opposite player State
     */
    private State getOppositePlayer(State player) {
        return (player == State.CROSS) ? State.CIRCLE : State.CROSS;
    }

    /**
     * Display current population of field in terminal
     * Debugging purpose
     */
    public void printCurrentState() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {

                switch (field[x][y]) {
                    case EMPTY:
                        System.out.print("_");
                        break;
                    case O:
                        System.out.print("O");
                        break;
                    case X:
                        System.out.print("X");
                        break;
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Define state of tile, also used by current user selection
     */
    public enum Tile {
        EMPTY, O, X
    }

    /**
     * Define the state of the current game
     */
    public enum State {
        CIRCLE, CROSS, END_DRAW, END_CIRCLE, END_CROSS
    }

}
