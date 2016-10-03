package TicTacToe.GameControler;

public class GameController {

    /**
     * field[horizontal][vertical]
     */
    private Tile[][] field;
    private int size = 3;
    private int moves = 0;
    private State currentState;

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
     * @param positionX
     * @param positionY
     * @return
     * @throws Exception
     * @todo Better usage of error handling
     */
    public boolean isEmptyTile(int positionX, int positionY) throws Exception {
        if (!positionInGame(positionX, positionY)) {
            throw new Exception("Position exceeds gamesize");
        }

        System.out.print(field[positionX][positionY] + " - ");
        return field[positionX][positionY] == Tile.EMPTY;

    }

    public State getCurrentState() {
        return currentState;
    }

    /**
     * @param positionX
     * @param positionY
     * @return boolean IsInPostion
     */
    public boolean positionInGame(int positionX, int positionY) {
        return !(positionX < 0 || positionY > this.size ||
                positionX < 0 || positionY > this.size);
    }

    /**
     * @param positionX horizontal position
     * @param positionY vertical position
     * @return boolean, return true whe move was successful.
     * @todo Better usage of error handling
     */
    public boolean move(int positionX, int positionY) {
        Tile tile = field[positionX][positionY];

        if (tile != Tile.EMPTY) {
            return false;
        } else {
            field[positionX][positionY] = (currentState == State.CIRCLE) ? Tile.O : Tile.X;
            checkCurrentState();
            printCurrentState();
            return true;
        }
    }

    public Tile[][] getField() {
        return field;
    }

    private void checkCurrentState() {
        boolean won = false;

        //Horizontal lines
        for (int y = 0; y < size; y++) {

            Tile startTile = field[0][y];
            for (int x = 0; x < size; x++) {
                if (startTile != field[x][y] || startTile == Tile.EMPTY) {
                    break;
                } else if (x == 2) {
                    won = true;
                }
            }

            if (won) {
                break;
            }
        }

        //Vertical lines
        for (int x = 0; x < size; x++) {
            Tile startTile = field[x][0];
            for (int y = 0; y < size; y++) {
                if (startTile != field[x][y] || startTile == Tile.EMPTY) {
                    break;
                } else if (y == 2) {
                    won = true;
                }
            }

            if (won) {
                break;
            }
        }

        //Diagonal lines
        if (field[1][1] != Tile.EMPTY && ((field[1][1] == field[0][0] && field[1][1] == field[2][2]) ||
                (field[1][1] == field[2][0] && field[1][1] == field[0][2]))) {
            won = true;
        }

        if (won) {
            System.out.println("Game Won!");
            if (currentState == State.CROSS) {
                currentState = State.END_CROSS;
            } else {
                currentState = State.END_CROSS;
            }
        } else if (moves >= size * size) {
            System.out.println("Draw!");
            currentState = State.END_DRAW;

        } else {
            currentState = getOppositePlayer(currentState);
        }
    }

    private State getOppositePlayer(State player) {
        if (player == State.CROSS) {
            return State.CIRCLE;
        } else {
            return State.CROSS;
        }
    }

    /**
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
    }

    // TODO: Maybe this is not needed or overused
    public enum Tile {
        EMPTY, O, X
    }

    public enum State {
        CIRCLE, CROSS, END_DRAW, END_CIRCLE, END_CROSS
    }

}
