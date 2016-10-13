package TicTacToe.network;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class DataEncoder implements Encoder.Text<MultiplayerMessage> {

    /**
     * * TODO: Add Javadoc
     *
     * @param message
     * @return
     * @throws EncodeException
     */
    @Override
    public String encode(MultiplayerMessage message) throws EncodeException {
        return message.asString();
    }

    /**
     * * TODO: Add Javadoc
     *
     * @param config
     */
    @Override
    public void init(EndpointConfig config) {

    }

    /**
     * * TODO: Add Javadoc
     */
    @Override
    public void destroy() {

    }
}
