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

    @OnMessage
    public void handleMessage(MultiplayerMessage message) {
        switch (message.getType()) {
            case MultiplayerMessage.UPDATE_BOARD_MESSAGE:
                ((MultiplayerClientController) Main.game).updateBoard(((UpdateBoardMessage) message));
                break;
        }
    }

    @OnClose
    public void onClose(CloseReason reason) {
        if (reason.getCloseCode() != CloseReason.CloseCodes.NORMAL_CLOSURE && !Main.game.isGameEnded()) {
            JOptionPane.showMessageDialog(Main.gameScreen, "The connection to the server has been lost. \n Reason: " + reason.getReasonPhrase(), "Connection lost!", JOptionPane.WARNING_MESSAGE);
            Main.gameScreen.stopGame();
        }
    }
}
