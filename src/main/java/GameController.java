import gui.GameScreen;

/**
 * Created by DucoSebel on 29-09-16.
 */
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


    public GameController() {

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
     * Source mostly thanked to Harewareguy
     * http://stackoverflow.com/questions/1056316/algorithm-for-determining-tic-tac-toe-game-over
     * @param positionH horizontal position
     * @param positionV vertical postion
     * @param state circle or cross
     * @return GameState
     * @throws Exception when there is an illegal move
     * @todo Better usage of error handling
     */
    public State move(int positionH, int positionV, Tile state) {
        System.out.print(positionH + ", " + positionV + " - ");

        if (!positionInGame(positionH, positionV)) {
            System.out.print("Not In Game - ");
            return State.ILLIGAL;
        }

        try {
            if (!isEmptyTile(positionH, positionV)) {
                System.out.print("Already something here - ");
                return State.ILLIGAL;
            }
        } catch (Exception e) {
            System.out.print("Not In Game - ");
            return State.ILLIGAL;
        }

        field[positionH][positionV] = state;

        if (state == Tile.EMPTY) {
            return State.NONE;
        }

        moves += 1;

        State winner = State.CROSS;
        if (state == Tile.CIRCLE) {
            winner = State.CIRCLE;
        }

        // Check winning action
        // Horizontal
        for(int i = 0; i < this.size; i++){
            if(field[positionH][i] != state)
                break;
            if(i == this.size - 1){
                return winner;
            }
        }

        // Vertical
        for(int i = 0; i < this.size; i++){
            if(field[i][positionV] != state)
                break;
            if(i == this.size - 1){
                return winner;
            }
        }

        // Diagonal
        if(positionH == positionV){
            for(int i = 0; i < this.size; i++){
                if(field[i][i] != state)
                    break;
                if(i == this.size-1){
                    return winner;
                }
            }
        }

        // Anti diagonal
        for(int i = 0; i < this.size; i++){
            if(field[i][(this.size - 1) - i] != state)
                break;
            if(i == this.size-1){
                return winner;
            }
        }

        //check draw
        if(moves == (this.size ^ 2 - 1)){
            return State.DRAW;
        }


        return State.NONE;
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
