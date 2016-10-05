package TicTacToe.GameControler;

import TicTacToe.network.HostEndpoint;
import org.glassfish.tyrus.server.Server;

public class MultiplayerHostController extends GameController{
    Server server;

    public MultiplayerHostController(Tile startingTurn) {
        super(startingTurn);
        }

    public Server getServer(){
        return server;
    }
}
