package TicTacToe.network;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class DataEncoder implements Encoder.Text<MultiplayerMessage> {

    /**
     * @param message object
     * @return message as a string
     * @throws EncodeException
     */
    @Override
    public String encode(MultiplayerMessage message) throws EncodeException {
        return message.asString();
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
