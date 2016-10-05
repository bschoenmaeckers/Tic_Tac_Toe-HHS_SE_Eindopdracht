package TicTacToe.network;

import TicTacToe.network.chat.ChatEndpoint;
import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.server.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class TestServer {
    public TestServer() {
        Server server = new Server("localhost", 8080, "", null, HostEndpoint.class);
        ClientManager client = ClientManager.createClient();

        try {
            server.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please press a key to stop the server.");
            reader.readLine();
            System.out.println("Connecting to server");
            client.connectToServer(PlayerEndpoint.class,new URI("ws://localhost:8080/host"));
            reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
}
