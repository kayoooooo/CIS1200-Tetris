package org.cis1200.tetris;

import javax.swing.*;
import java.awt.*;

public class RunTetris implements Runnable {
    public void run() {
        final JFrame frame = new JFrame("Tetris");
        frame.setLocation(100, 100);

        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        status_panel.add(status);

        String[] list = { "New Game", "Load Game" };

        JOptionPane.showMessageDialog(
                null,
                "This is Tetris. The goal of the game is to not lose. \n" +
                        "Arrow keys left and right will move the piece. " +
                        "The up arrow will rotate the piece.\n"
                        +
                        "Space will drop the piece immediately, " +
                        "and the down arrow will allow the piece to fall.\n"
                        +
                        "When a row is full, it will be cleared. " +
                        "By clearing lines, you delay the inevitable."
        );

        int chosen = JOptionPane.showOptionDialog(
                null,
                "New Game or Load Game", "Tetris", 0, 0,
                null, list, list[0]
        );

        final Board board = new Board(status);
        frame.add(board, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());
        control_panel.add(reset);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        if (chosen != 0) {
            board.load();
        } else {
            board.reset();
        }
    }
}
