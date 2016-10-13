package TicTacToe.network;

import TicTacToe.GameControler.GameController;

import static TicTacToe.GameControler.GameController.Tile;

public class UpdateBoardMessage extends MultiplayerMessage {

    public Tile[][] board = new Tile[3][3];
    public GameController.State currentTurn;

    public UpdateBoardMessage() {
        super(UPDATE_BOARD_MESSAGE);
    }

    /**
     * Message to update clients.
     * @param board current board
     * @param currentTurn current turn
     */
    public UpdateBoardMessage(Tile[][] board, GameController.State currentTurn) {
        super(UPDATE_BOARD_MESSAGE);
        this.board = board;
        this.currentTurn = currentTurn;
    }

    /**
     * Display received message string
     * @return String, parsed string
     */
    @Override
    public String asString() {
        String data = UPDATE_BOARD_MESSAGE;

        for (Tile[] row : board) {
            for (Tile tile : row) {
                data += tile.name() + " ";
            }
        }

        data += currentTurn.name();

        return data.trim();
    }

    /**
     * Update board with current screen from message
     * @param s MessageString
     */
    @Override
    public void fromString(String s) {
        String[] data = s.substring(UPDATE_BOARD_MESSAGE.length()).split("\\s");
        int x = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Tile.valueOf(data[x++]);
            }
        }
        currentTurn = GameController.State.valueOf(data[x]);
    }
}
