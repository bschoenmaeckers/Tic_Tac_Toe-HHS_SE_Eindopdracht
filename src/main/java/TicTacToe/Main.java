package TicTacToe;

import TicTacToe.GameControler.GameController;
import TicTacToe.gui.GameScreen;
import TicTacToe.gui.MainMenu;

public class Main {

    public static MainMenu menu;
    public static GameScreen gameScreen;
    public static GameController game;

    /**
     * Start application an display MainMenu where the user
     * can choose between Single or Multi player
     *
     * @param args
     */
    public static void main(String[] args) {
        //Open main menu
        menu = new MainMenu();
    }
}
