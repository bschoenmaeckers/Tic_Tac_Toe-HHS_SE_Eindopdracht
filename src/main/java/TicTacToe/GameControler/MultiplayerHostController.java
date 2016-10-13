package TicTacToe.GameControler;

import TicTacToe.Main;
import TicTacToe.network.HostEndpoint;
import TicTacToe.network.MoveMessage;
import TicTacToe.network.UpdateBoardMessage;
import org.glassfish.tyrus.server.Server;

import javax.websocket.CloseReason;
import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;

public class MultiplayerHostController extends GameController {
    private Server server = new Server(null, 0, null, null, HostEndpoint.class);
    private Session connection;
    private boolean leavingGame = false;

    public MultiplayerHostController(Tile startingTurn) throws DeploymentException {
        super(startingTurn);

        server.start();
    }

    public boolean playerConnected(Session newConnection) {

        if (connection == null) {
            connection = newConnection;
            updateClient();
            Main.gameScreen.updateScreen(this);
            return true;
        } else
            return false;
    }

    public boolean move(MoveMessage message) {
        return currentState == State.CROSS && move(message.getX(), message.getY());
    }

    @Override
    public boolean move(int positionX, int positionY) {

        Tile tile = field[positionX][positionY];

        if (tile != Tile.EMPTY || isGameEnded() || connection == null) {
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

    @Override
    public boolean isGameEnded() {
        return super.isGameEnded() || leavingGame;
    }

    public void updateClient() {
        try {
            connection.getBasicRemote().sendObject(new UpdateBoardMessage(field, currentState));
        } catch (IOException | EncodeException e) {
            System.out.println("Could not send an update to the client because an error accord:");
            e.printStackTrace();
        }
    }

    public void stopGame() {
        try {
            if (connection.isOpen()) {
                if (isGameEnded())
                    connection.close();
                else {
                    leavingGame = true;
                    connection.close(new CloseReason(CloseReason.CloseCodes.GOING_AWAY, "Host left the game!"));
                }
            }
        } catch (IOException | NullPointerException ignored) {

        }
        server.stop();
    }
}
