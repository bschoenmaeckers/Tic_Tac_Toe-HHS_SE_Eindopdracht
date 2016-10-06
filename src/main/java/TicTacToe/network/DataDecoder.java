package TicTacToe.network;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class DataDecoder implements Decoder.Text<MultiplayerMessage> {
    @Override
    public MultiplayerMessage decode(String s) throws DecodeException {
        return MultiplayerMessage.parseMessage(s);
    }

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
