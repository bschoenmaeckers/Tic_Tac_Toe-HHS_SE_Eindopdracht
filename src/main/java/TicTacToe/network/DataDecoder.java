package TicTacToe.network;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class DataDecoder implements Decoder.Text<MultiplayerMessage> {

    /**
     * @param s Message to decode
     * @return Decoded message
     * @throws DecodeException
     */
    @Override
    public MultiplayerMessage decode(String s) throws DecodeException {
        return MultiplayerMessage.parseMessage(s);
    }

    /**
     * @param s message
     * @return true when this is a valid message
     */
    @Override
    public boolean willDecode(String s) {
        return s.startsWith(MultiplayerMessage.UPDATE_BOARD_MESSAGE) || s.startsWith(MultiplayerMessage.MOVE_MESSAGE);
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
}
