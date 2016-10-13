package TicTacToe.gui;

import TicTacToe.GameControler.GameController;
import TicTacToe.Main;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class GameScreen extends JFrame {

    JLabel currentTurn;
    private JPanel panel1;
    private JButton A1, B1, C1, A2, B2, C2, A3, B3, C3;
    private JButton[][] buttons;// y,x

    /**
     * Load new gamescreen and loads the buttons
     */
    public GameScreen() {
        setContentPane(panel1);
        setTitle("Tic Tac Toe");
        setResizable(false);
        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopGame();
            }
        });

        buttons = new JButton[][]{
                {A1, B1, C1},
                {A2, B2, C2},
                {A3, B3, C3}
        };

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                final int x = j;
                final int y = i;
                buttons[y][x].addActionListener(e -> doMove(x, y));
            }
        }

        setVisible(true);

    }

    /**
     * Move current action and visualize it to the board
     *
     * @param x Horizontal position
     * @param y Vertical position
     */
    public void doMove(int x, int y) {
        if (Main.game.move(x, y) && Main.game != null) {
            this.updateScreen(Main.game);
        }
    }

    /**
     * Stop current game
     *
     * @param game Current GameController
     */
    public abstract void gameOver(GameController game);

    /**
     * Update gameboard with current field
     *
     * @param game Current GameController
     */
    public void updateScreen(GameController game) {

        if (!game.isGameEnded())
            currentTurn.setText(game.getCurrentState().name());

        int y = 0;
        for (JButton[] row : buttons) {
            int x = 0;
            for (JButton button : row) {
                if (game.getField()[x][y] != GameController.Tile.EMPTY)
                    button.setText(game.getField()[x][y].name());
                x++;
            }
            y++;
        }
    }

    /**
     * Stop current game and remove it from memory
     * Reopens the main menu
     */
    public void stopGame() {
        dispose();
        Main.gameScreen = null;
        Main.game = null;
        Main.menu = new MainMenu();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setFont(new Font(panel2.getFont().getName(), panel2.getFont().getStyle(), 20));
        panel1.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        A1 = new JButton();
        A1.setFont(new Font(A1.getFont().getName(), A1.getFont().getStyle(), 20));
        A1.setText("");
        panel2.add(A1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 100), new Dimension(100, 100), null, 0, false));
        C1 = new JButton();
        C1.setFont(new Font(C1.getFont().getName(), C1.getFont().getStyle(), 20));
        C1.setText("");
        panel2.add(C1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 100), new Dimension(100, 100), null, 0, false));
        B1 = new JButton();
        B1.setFont(new Font(B1.getFont().getName(), B1.getFont().getStyle(), 20));
        B1.setText("");
        panel2.add(B1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 100), new Dimension(100, 100), null, 0, false));
        B2 = new JButton();
        B2.setFont(new Font(B2.getFont().getName(), B2.getFont().getStyle(), 20));
        B2.setText("");
        panel2.add(B2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 100), new Dimension(100, 100), null, 0, false));
        C2 = new JButton();
        C2.setFont(new Font(C2.getFont().getName(), C2.getFont().getStyle(), 20));
        C2.setText("");
        panel2.add(C2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 100), new Dimension(100, 100), null, 0, false));
        C3 = new JButton();
        C3.setFont(new Font(C3.getFont().getName(), C3.getFont().getStyle(), 20));
        C3.setText("");
        panel2.add(C3, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 100), new Dimension(100, 100), null, 0, false));
        B3 = new JButton();
        B3.setFont(new Font(B3.getFont().getName(), B3.getFont().getStyle(), 20));
        B3.setText("");
        panel2.add(B3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 100), new Dimension(100, 100), null, 0, false));
        A3 = new JButton();
        A3.setFont(new Font(A3.getFont().getName(), A3.getFont().getStyle(), 20));
        A3.setText("");
        panel2.add(A3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 100), new Dimension(100, 100), null, 0, false));
        A2 = new JButton();
        A2.setFont(new Font(A2.getFont().getName(), A2.getFont().getStyle(), 20));
        A2.setText("");
        panel2.add(A2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 100), new Dimension(100, 100), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Current turn: ");
        panel3.add(label1);
        currentTurn = new JLabel();
        currentTurn.setText("Nobody");
        panel3.add(currentTurn);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
