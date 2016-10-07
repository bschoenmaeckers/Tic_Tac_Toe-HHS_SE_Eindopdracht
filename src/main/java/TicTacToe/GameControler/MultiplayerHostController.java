package TicTacToe.GameControler;

import TicTacToe.Main;
import TicTacToe.gui.MainMenu;
import TicTacToe.network.HostEndpoint;
import TicTacToe.network.MoveMessage;
import TicTacToe.network.UpdateBoardMessage;
import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;

public class MultiplayerHostController extends GameController {
    Server server = new Server(null,0,null,null, HostEndpoint.class);
    Session connection;

    public Server getServer() {
        return server;
    }

    public boolean playerConnected(Session newConnection){

        if (connection == null) {
            connection = newConnection;
            updateClient();
            Main.gameScreen.updateScreen(this);
            return true;
        } else
            return false;
    }

    public MultiplayerHostController(Tile startingTurn) throws DeploymentException {
        super(startingTurn);

        server.start();
    }

    public boolean move(MoveMessage message) {
        return currentState == State.CROSS && move(message.getX(), message.getY());
    }

    @Override
    public boolean move(int positionX, int positionY) {

        Tile tile = field[positionX][positionY];

        if (tile != Tile.EMPTY || isGameOver()) {
            return false;
        } else {
            field[positionX][positionY] = (currentState == State.CIRCLE) ? Tile.O : Tile.X;
            moves++;
            checkCurrentState();
            printCurrentState();

            if (Main.gameScreen == null)
                return false;

            Main.gameScreen.updateScreen(this);

            updateClient();

            return true;
        }
    }

    public void updateClient(){
        try {
            connection.getBasicRemote().sendObject(new UpdateBoardMessage(field,currentState));
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

    public void stopGame(){
        try {
            connection.close();
        } catch (IOException|NullPointerException ignored){

        }
        server.stop();
    }
}
