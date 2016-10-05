package TicTacToe.network;

import TicTacToe.GameControler.GameController;

import java.awt.*;

public class MoveMessage extends MultiplayerMessage {

    public GameController.Tile tile;
    public Point location;

    public MoveMessage() {
        super(MOVE_MESSAGE);
    }

    @Override
    public String asString() {
        return MOVE_MESSAGE + tile + "-" + location.getX() + "-" + location.getY();
    }

    @Override
    public void fromString(String s) {
        String[] data = s.substring(MOVE_MESSAGE.length()).split("-");
        tile = GameController.Tile.valueOf(data[0]);
        location = new Point(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
    }
}
