package TicTacToe.network;

import TicTacToe.GameControler.GameController;

import java.awt.*;

public class MoveMessage extends MultiplayerMessage {

    public GameController.Tile tile;
    public Point location;

    public MoveMessage() {
        super(MOVE_MESSAGE);
    }

    public MoveMessage( GameController.Tile tile, int x, int y) {
        super(MOVE_MESSAGE);
        this.tile = tile;
        this.location = new Point(x,y);
    }

    public int getX(){
        return location.x;
    }

    public int getY(){
        return location.y;
    }

    @Override
    public String asString() {
        return MOVE_MESSAGE + tile + "-" + location.x + "-" + location.y;
    }

    @Override
    public void fromString(String s) {
        String[] data = s.substring(MOVE_MESSAGE.length()).split("-");
        tile = GameController.Tile.valueOf(data[0]);
        location = new Point(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
    }
}
