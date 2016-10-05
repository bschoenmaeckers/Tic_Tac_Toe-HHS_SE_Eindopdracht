package TicTacToe.network;

import javax.websocket.*;
import java.util.logging.Logger;

@ClientEndpoint(encoders = {DataEncoder.class},decoders = {DataDecoder.class})
public class PlayerEndpoint{
    Logger logger = Logger.getLogger("Client");

    @OnMessage
    public void handleMessage(MultiplayerMessage message, Session session){
        logger.info("message: " + message.asString());
    }

//    @OnClose
//    public void onClose(){
//
//    }
//
//    @OnError
//    public void onError(){
//
//    }
}
