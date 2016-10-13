package TicTacToe.network;

import TicTacToe.GameControler.GameController;

import java.awt.*;

public class MoveMessage extends MultiplayerMessage {

    private GameController.Tile tile;
    private Point location;

    /**
     * constructs empty move message.
     */
    public MoveMessage() {
        super(MOVE_MESSAGE);
    }

    /**
     * Constructs new move message with given data.
     * @param tile New value
     * @param x Horizontal board position
     * @param y Vertical board position
     */
    public MoveMessage(GameController.Tile tile, int x, int y) {
        super(MOVE_MESSAGE);
        this.tile = tile;
        this.location = new Point(x, y);
    }

    /**
     * @return Horizontal position
     */
    public int getX() {
        return location.x;
    }

    /**
     * @return Vertical position
     */
    public int getY() {
        return location.y;
    }

    /**
     * @return Message as string
     */
    @Override
    public String asString() {
        return MOVE_MESSAGE + tile + "-" + location.x + "-" + location.y;
    }

    /**
     * Set message data from string
     * @param s String
     */
    @Override
    public void fromString(String s) {
        String[] data = s.substring(MOVE_MESSAGE.length()).split("-");
        tile = GameController.Tile.valueOf(data[0]);
        location = new Point(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
    }
}
