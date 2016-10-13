package TicTacToe.network;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class DataDecoder implements Decoder.Text<MultiplayerMessage> {

    /**
     * * TODO: Add Javadoc
     * @param s
     * @return
     * @throws DecodeException
     */
    @Override
    public MultiplayerMessage decode(String s) throws DecodeException {
        return MultiplayerMessage.parseMessage(s);
    }

    /**
     * * TODO: Add Javadoc
     * @param s
     * @return
     */
    @Override
    public boolean willDecode(String s) {
        return s.startsWith(MultiplayerMessage.UPDATE_BOARD_MESSAGE) || s.startsWith(MultiplayerMessage.MOVE_MESSAGE);
    }

    /**
     * * TODO: Add Javadoc
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
