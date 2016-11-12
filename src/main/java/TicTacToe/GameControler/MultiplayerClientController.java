package TicTacToe.GameControler;


import TicTacToe.Main;
import TicTacToe.gui.MultiplayerClientGameScreen;
import TicTacToe.network.MoveMessage;
import TicTacToe.network.PlayerEndpoint;
import TicTacToe.network.UpdateBoardMessage;
import org.glassfish.tyrus.client.ClientManager;

import javax.swing.*;
import javax.websocket.CloseReason;
import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MultiplayerClientController extends GameController {
    private Session connection;
    private boolean leavingGame = false;

    /**
     * Connect GUI to server
     * @param gameScreen Current Gamescreen
     * @param startingTurn The first player to start
     */
    public MultiplayerClientController(MultiplayerClientGameScreen gameScreen, Tile startingTurn) {
        super(startingTurn);

        do {
            try {
                ClientManager client = ClientManager.createClient();
                String address = JOptionPane.showInputDialog(Main.gameScreen, "Connect to server. \n\n Server ip:");

                if (address == null) {
                    //cancel button hit. Returning to main menu
                    gameScreen.stopGame();
                    return;
                }

                connection = client.connectToServer(PlayerEndpoint.class, new URI("ws://" + address + ":8025/game"));
            } catch (URISyntaxException e) {
                JOptionPane.showMessageDialog(Main.gameScreen, "This is not a valid address!");
                connection = null;
            } catch (IOException | DeploymentException e) {
                JOptionPane.showMessageDialog(Main.gameScreen, "Could not connect to server!");
                connection = null;
            }
        } while (connection == null || !connection.isOpen());
    }

    /**
     * Make move when possible
     * @param positionX horizontal position
     * @param positionY vertical position
     * @return true when move is succes
     */
    @Override
    public boolean move(int positionX, int positionY) {
        Tile tile = field[positionX][positionY];

        if (tile != Tile.EMPTY || isGameEnded()) {
            return false;
        } else {
            try {
                connection.getBasicRemote().sendObject(new MoveMessage(Tile.X, positionX, positionY));
            } catch (IOException | EncodeException e) {
                System.out.println("Error while handling player move:");
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    /**
     * Update message on gameboard
     * Whose turn, and if someone has won
     * @param message Message to be displayed
     */
    public void updateBoard(UpdateBoardMessage message) {
        this.field = message.board;
        this.currentState = message.currentTurn;
        Main.gameScreen.updateScreen(this);
        checkCurrentState(true);
    }

    /**
     * Check if game is ended
     */
    @Override
    protected boolean checkCurrentState(boolean realMove) {
        if (currentState == State.END_CROSS || currentState == State.END_CIRCLE || currentState == State.END_DRAW)
            Main.gameScreen.gameOver(this);
        return realMove;
    }

    /**
     * Returns true when game is over or disconnected
     * @return endState
     */
    @Override
    public boolean isGameEnded() {
        return super.isGameEnded() || leavingGame;
    }

    /**
     * Close connection from server side
     */
    public void closeConnection() {
        if (connection == null)
            return;

        try {
            if (connection.isOpen()) {
                if (isGameEnded())
                    connection.close();
                else {
                    leavingGame = true;
                    connection.close(new CloseReason(CloseReason.CloseCodes.GOING_AWAY, "Client left the game!"));
                }
            }
        } catch (IOException ignore) {
        }
    }
}
