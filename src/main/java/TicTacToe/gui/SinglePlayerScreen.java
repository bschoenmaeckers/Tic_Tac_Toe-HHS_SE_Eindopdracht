package TicTacToe.gui;

public class SinglePlayerScreen extends OfflineGameScreen {

    public SinglePlayerScreen() {
        super();
        //TODO
    }

    @Override
    public void doMove(int x, int y) {
        System.out.println("Click! x = " + x + " y = " + y);
    }
}
