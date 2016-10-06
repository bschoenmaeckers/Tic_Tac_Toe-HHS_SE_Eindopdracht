package TicTacToe.GameControler;


import TicTacToe.Main;
import TicTacToe.network.MoveMessage;
import TicTacToe.network.PlayerEndpoint;
import TicTacToe.network.UpdateBoardMessage;
import org.glassfish.tyrus.client.ClientManager;

import javax.swing.*;
import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MultiplayerClientController extends GameController {
    ClientManager client = ClientManager.createClient();
    Session connection;

    public MultiplayerClientController(Tile startingTurn) {
        super(startingTurn);

        do {
            try {
                connection = client.connectToServer(PlayerEndpoint.class, new URI("ws://" + JOptionPane.showInputDialog("Connect to server. \n\n Server ip:") + ":8025/game"));
            } catch (URISyntaxException | NullPointerException | DeploymentException e) {
                JOptionPane.showMessageDialog(Main.gameScreen, "This is not a valid address!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(Main.gameScreen, "Could not connect to server!");
            }
        } while (!connection.isOpen());
    }

    @Override
    public boolean move(int positionX, int positionY) {
        Tile tile = field[positionX][positionY];

        if (tile != Tile.EMPTY || isGameOver()) {
            return false;
        } else {
            try {
                connection.getBasicRemote().sendObject(new MoveMessage(Tile.X,positionX,positionY));
            } catch (IOException|EncodeException e) {
                //TODO stop game
            }
            return true;
        }
    }

    public void updateBoard(UpdateBoardMessage message) {
        this.field = message.board;
        this.currentState = message.currentTurn;
        Main.gameScreen.updateScreen(this);
    }

    @Override
    protected void checkCurrentState() {
        //Do Nothing
    }
}
