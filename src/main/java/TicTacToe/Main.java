package TicTacToe;

import TicTacToe.GameControler.GameController;
import TicTacToe.gui.GameScreen;
import TicTacToe.gui.MainMenu;
import TicTacToe.network.TestServer;

public class Main {

    public static MainMenu menu;
    public static GameScreen gameScreen;
    public static GameController game;

    public static void main(String[] args) {
        //Open main menu
        MainMenu menu = new MainMenu();
        new TestServer();
    }
}
