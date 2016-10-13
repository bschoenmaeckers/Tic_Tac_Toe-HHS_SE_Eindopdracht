package TicTacToe.network;

import TicTacToe.GameControler.MultiplayerHostController;
import TicTacToe.Main;

import javax.swing.*;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/game", decoders = {DataDecoder.class}, encoders = {DataEncoder.class})
public class HostEndpoint {

    /**
     * Handles incoming connections.
     * When there is already someone connected all other connections are denied.
     * @param session incoming connection
     * @throws IOException
     * @throws EncodeException
     */
    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        System.out.println("Player connecting!");

        if (((MultiplayerHostController) Main.game).playerConnected(session))
            session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "Player already connected!"));

        System.out.println("Player connected!");
    }

    /**
     * Handles incoming messages
     * @param message
     */
    @OnMessage
    public void handleMessage(MultiplayerMessage message) {
        if (message.getType().equals(MultiplayerMessage.MOVE_MESSAGE))
            ((MultiplayerHostController) Main.game).move(((MoveMessage) message));
    }

    /**
     * Stops the game and notifies player when the connection has been lost to the other player.
     * @param reason The reason why the connection has been closed.
     */
    @OnClose
    public void onClose(CloseReason reason) {
        if (reason.getCloseCode() != CloseReason.CloseCodes.NORMAL_CLOSURE && !Main.game.isGameEnded() && reason.getCloseCode() != CloseReason.CloseCodes.CANNOT_ACCEPT) {
            System.out.println("Player disconnected!");
            JOptionPane.showMessageDialog(Main.gameScreen, "The connection to the client has been lost. \n Reason: " + reason.getReasonPhrase(), "Connection lost!", JOptionPane.WARNING_MESSAGE);
            Main.gameScreen.stopGame();
        }
    }
}
