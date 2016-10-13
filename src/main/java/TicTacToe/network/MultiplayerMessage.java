package TicTacToe.network;

public abstract class MultiplayerMessage {

    public static final String MOVE_MESSAGE = "mve";
    public static final String UPDATE_BOARD_MESSAGE = "upd";

    String type;

    /**
     * construct message with given type
     * @param type Message type
     */
    public MultiplayerMessage(String type) {
        this.type = type;
    }

    /**
     * parse string to a message object
     * @param s message string
     * @return parsed message
     * @throws RuntimeException when message could not be parsed.
     */
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

    /**
     * @return Message as string
     */
    public abstract String asString();

    /**
     * Set message data from string
     * @param s String
     */
    public abstract void fromString(String s);

    /**
     *
     * @return Message type
     */
    public String getType() {
        return type;
    }
}
