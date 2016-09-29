import gui.MainMenu;

public class Main {

    static GameController game = new GameController();

    public static void main(String[] args) {
        //Open main menu
        MainMenu menu = new MainMenu();

        move(0, 0, GameController.Tile.CIRCLE);
        move(0, 0, GameController.Tile.CROSS);
        move(0, 1, GameController.Tile.CROSS); // Illigal
        move(1, 1, GameController.Tile.CIRCLE);
        move(1, 0, GameController.Tile.CROSS);
        move(2, 2, GameController.Tile.CIRCLE);
    }

    public static void move(int positionH, int positionV, GameController.Tile state) {
        switch (game.move(positionH, positionV, state)) {
            case CIRCLE:
                System.out.println("Circle wins!");
                break;
            case CROSS:
                System.out.println("Cross wins!");
                break;
            case DRAW:
                System.out.println("Draw!");
                break;
            case NONE:
                System.out.println("OK");
                break;
            case ILLIGAL:
                System.out.println("Illegal move!");
                break;
        }
        game.printCurrentState();
        System.out.println();
        System.out.println();
    }
}
