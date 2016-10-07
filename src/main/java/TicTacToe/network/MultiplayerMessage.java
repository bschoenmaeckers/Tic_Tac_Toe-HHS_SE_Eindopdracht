package TicTacToe.network;

public abstract class MultiplayerMessage {

    public static final String MOVE_MESSAGE = "mve";
    public static final String UPDATE_BOARD_MESSAGE = "upd";

    String type;

    public MultiplayerMessage(String type) {
        this.type = type;
    }

    public static MultiplayerMessage parseMessage(String s) {
        System.out.println("Parse: " + s);
        MultiplayerMessage message;

        if (s.startsWith(MOVE_MESSAGE)) {
            message = new MoveMessage();
        } else if (s.startsWith(UPDATE_BOARD_MESSAGE)) {
            message = new UpdateBoardMessage();
        } else {
            throw new RuntimeException("Unknown message: " + s);
        }
        message.fromString(s);
        return message;
    }

    public abstract String asString();

    public abstract void fromString(String s);

    public String getType() {
        return type;
    }
}
