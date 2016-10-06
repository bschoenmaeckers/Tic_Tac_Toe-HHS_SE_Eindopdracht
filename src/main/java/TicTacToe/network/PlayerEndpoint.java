package TicTacToe.network;

import TicTacToe.GameControler.GameController;
import TicTacToe.GameControler.MultiplayerClientController;
import TicTacToe.Main;

import javax.websocket.*;
import java.io.IOException;

@ClientEndpoint(encoders = {DataEncoder.class}, decoders = {DataDecoder.class})
public class PlayerEndpoint {

    @OnMessage
    public void handleMessage(MultiplayerMessage message, Session session) {
        switch (message.getType()){
            case MultiplayerMessage.UPDATE_BOARD_MESSAGE:
                ((MultiplayerClientController) Main.game).updateBoard(((UpdateBoardMessage) message));
                break;
        }
    }

    @OnClose
    public void onClose(CloseReason reason){
        System.out.println(reason.getReasonPhrase());
    }
//
//    @OnError
//    public void onError(){
//
//    }
}
