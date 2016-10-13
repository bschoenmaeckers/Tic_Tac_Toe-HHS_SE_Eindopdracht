package TicTacToe.gui;

import TicTacToe.Main;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.websocket.DeploymentException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenu extends JFrame {
    private JButton singlePlayerButton;
    private JPanel panel1;
    private JButton localMultiplayerButton;
    private JButton hostGameButton;
    private JButton joinGameButton;

    public MainMenu() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel1);
        setResizable(false);
        pack();

        //center window
        setLocationRelativeTo(null);
        setVisible(true);

        /**
         * Listener for SinglePlayerbutton
         * Start SinglePlayerScreen when button is pressed
         */
        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gameScreen = new SinglePlayerScreen();
                MainMenu.this.dispose();
            }
        });

        /**
         * Start LocalMultiPlayerScreen when button is pressed
         */
        localMultiplayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gameScreen = new LocalMultiplayerScreen();
                MainMenu.this.dispose();
            }
        });

        /**
         * Listener for Host Game button
         * Start HostGameScreen
         */
        hostGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.gameScreen = new MultiplayerHostGameScreen();
                    MainMenu.this.dispose();
                } catch (DeploymentException e1) {
                    JOptionPane.showMessageDialog(MainMenu.this, "Error starting server! Only one host per device allowed!", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        /**
         * Listener for Join Game button
         * Start JoinGameScreen
         */
        joinGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu.this.dispose();

                try {
                    Main.gameScreen = new MultiplayerClientGameScreen();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (DeploymentException e1) {
                    e1.printStackTrace();
                }
            }
        });
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
        panel1.setLayout(new GridLayoutManager(7, 1, new Insets(0, 10, 10, 10), -1, 10));
        singlePlayerButton = new JButton();
        singlePlayerButton.setText("Single Player");
        panel1.add(singlePlayerButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        localMultiplayerButton = new JButton();
        localMultiplayerButton.setText("Multi Player");
        panel1.add(localMultiplayerButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font(label1.getFont().getName(), label1.getFont().getStyle(), 40));
        label1.setText("Hoofdmenu");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 1), null, 0, false));
        hostGameButton = new JButton();
        hostGameButton.setText("Host Game");
        panel1.add(hostGameButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        joinGameButton = new JButton();
        joinGameButton.setText("Join Game");
        panel1.add(joinGameButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
