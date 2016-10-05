package TicTacToe.network;

import TicTacToe.GameControler.GameController;

import java.io.IOException;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/host",decoders = {DataDecoder.class}, encoders = {DataEncoder.class})
public class HostEndpoint {

    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        System.out.println("Player connecting...");
        GameController.Tile[][] board = {{GameController.Tile.EMPTY,GameController.Tile.EMPTY,GameController.Tile.EMPTY},
                {GameController.Tile.EMPTY,GameController.Tile.O,GameController.Tile.O},
                {GameController.Tile.EMPTY,GameController.Tile.EMPTY,GameController.Tile.X}};
        MultiplayerMessage message = new UpdateBoardMessage(board);

        session.getBasicRemote().sendObject(message);
    }

    @OnClose
    public void onClose(){
        System.out.println("Player disconnected!");
        //Stop game
    }

    @OnMessage
    public void handleMessage(MultiplayerMessage message , Session session) throws IOException {
        if (message.getType().equals(MultiplayerMessage.MOVE_MESSAGE))
            System.out.println("got move.");
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

}
