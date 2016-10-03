package TicTacToe.GameControler;

public class GameController {

    // TODO: Maybe this is not needed or overused
    public enum Tile {
        EMPTY, CIRCLE, CROSS
    }

    public enum State {
        CIRCLE, CROSS, DRAW, NONE, ILLIGAL;
    }

    /**
     * field[horizontal][vertical]
     */
    private Tile[][] field;
    private int size = 3;
    private int moves = 0;
    private Tile currentTurn;
    private State gameState = State.NONE;


    public GameController(Tile startingTurn) {

        this.currentTurn = startingTurn;
        this.field = new Tile[size][size];
        for (int h = 0; h < size; h++) {
            for (int v = 0; v < size; v++) {
                field[h][v] = Tile.EMPTY;
            }
        }

    }

    /**
     *
     * @param positionH
     * @param positionV
     * @return
     * @throws Exception
     * @todo Better usage of error handling
     */
    public boolean isEmptyTile(int positionH, int positionV) throws Exception {
        if (!positionInGame(positionH, positionV)) {
            throw new Exception("Position exceeds gamesize");
        }

        System.out.print(field[positionH][positionV] + " - ");
        return field[positionH][positionV] == Tile.EMPTY;

    }

    public State getGameState() {
        return gameState;
    }

    /**
     *
     * @param positionH
     * @param positionV
     * @return boolean IsInPostion
     */
    public boolean positionInGame(int positionH, int positionV) {
        return !(positionH < 0 || positionH > this.size ||
                positionV < 0 || positionV > this.size);
    }

    /**
     * @param positionH horizontal position
     * @param positionV vertical postion
     * @param state circle or cross
     * @return boolean, return true whe move was successful.
     * @todo Better usage of error handling
     */
    public boolean move(int positionH, int positionV, Tile state) {
        System.out.print(positionH + ", " + positionV + " - ");
        Tile tile = field[positionH][positionV];
        boolean result;

        if (tile != Tile.EMPTY || tile == state || state != currentTurn) {
            return false;
        }
        else {
            field[positionH][positionV] = state;
            checkWin();
            return true;
        }
    }

    public Tile[][] getField() {
        return field;
    }

    public void checkWin (){
        boolean won = false;

        //Horizontal lines
        for (int y = 0; y < size; y++) {

            Tile startTile = field[0][y];
            for (int x = 0; x < size; x++) {
                if(startTile != field[x][y] || startTile == Tile.EMPTY) {
                    break;
                }
                else if (x == 2) {
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
                if(startTile != field[x][y] || startTile == Tile.EMPTY) {
                    break;
                }
                else if (y == 2) {
                    won = true;
                }
            }

            if(won) {
                break;
            }
        }

        //Diagonal lines
        if (field[1][1] != Tile.EMPTY && ((field[1][1] == field[0][0] && field[1][1] == field[2][2]) ||
                (field[1][1] == field[2][0] && field[1][1] == field[0][2]))) {
            won = true;
        }

        if (won){
            System.out.println("Game Won!");
            if (currentTurn == Tile.CROSS) {
                gameState = State.CROSS;
            }
            else {
                gameState = State.CIRCLE;
            }
        } else if(moves >= size*size){
            System.out.println("Draw!");
            gameState = State.DRAW;

        }
        else {
            currentTurn = getOppositePlayer(currentTurn);
        }
    }

    private Tile getOppositePlayer(Tile player){
        if (player == Tile.CROSS) {
            return Tile.CIRCLE;
        }
        else if (player == Tile.CIRCLE) {
            return Tile.CROSS;
        }
        else {
            return Tile.EMPTY;
        }
    }

    /**
     * Debugging purpose
     */
    public void printCurrentState() {
        for (int h = 0; h < size; h++) {
            for (int v = 0; v < size; v++) {

                switch (field[h][v]) {
                    case EMPTY:
                        System.out.print("_");
                        break;
                    case CIRCLE:
                        System.out.print("O");
                        break;
                    case CROSS:
                        System.out.print("X");
                        break;
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}
