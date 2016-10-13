package TicTacToe.network;

import TicTacToe.GameControler.MultiplayerClientController;
import TicTacToe.Main;

import javax.swing.*;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;

@ClientEndpoint(encoders = {DataEncoder.class}, decoders = {DataDecoder.class})
public class PlayerEndpoint {

    /**
     * Handles incoming messages
     * @param message
     */
    @OnMessage
    public void handleMessage(MultiplayerMessage message) {
        if (message.getType().equals(MultiplayerMessage.UPDATE_BOARD_MESSAGE))
                ((MultiplayerClientController) Main.game).updateBoard(((UpdateBoardMessage) message));
    }

    /**
     * Notifies player when the connection has been lost to the other player.
     * @param reason The reason why the connection has been closed.
     */
    @OnClose
    public void onClose(CloseReason reason) {
        if (reason.getCloseCode() != CloseReason.CloseCodes.NORMAL_CLOSURE && !Main.game.isGameEnded()) {
            JOptionPane.showMessageDialog(Main.gameScreen, "The connection to the server has been lost. \n Reason: " + reason.getReasonPhrase(), "Connection lost!", JOptionPane.WARNING_MESSAGE);
            Main.gameScreen.stopGame();
        }
    }
}
