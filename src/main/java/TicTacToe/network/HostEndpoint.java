package TicTacToe.network;

import TicTacToe.GameControler.MultiplayerHostController;
import TicTacToe.Main;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/game", decoders = {DataDecoder.class}, encoders = {DataEncoder.class})
public class HostEndpoint {

    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        System.out.println("Player connecting!");

        if(!((MultiplayerHostController) Main.game).playerConnected(session))
            session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "Player already connected!"));
    }

    @OnClose
    public void onClose() {
        System.out.println("Player disconnected!");
        //Stop game
    }

    @OnMessage
    public void handleMessage(MultiplayerMessage message, Session session) {
        if (message.getType().equals(MultiplayerMessage.MOVE_MESSAGE))
            ((MultiplayerHostController) Main.game).move(((MoveMessage) message));
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

}
