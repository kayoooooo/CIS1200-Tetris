package org.cis1200.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel {
    private Tetris ttrs;

    private JLabel status;

    private final int width = 30;

    private final int height = 30;

    private boolean playing = false;

    public Board(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        Timer timer = new Timer(1000, e -> tick());
        timer.start(); // MAKE SURE TO START THE TIMER!

        ttrs = new Tetris(); // initializes model for the game
        status = statusInit; // initializes the status JLabel

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    ttrs.moveLeft();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    ttrs.moveRight();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    ttrs.moveDown();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    ttrs.rotate();
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    ttrs.hardDrop();
                }
                repaint();
            }
        });
    }

    public void reset() {
        ttrs.reset();
        playing = true;
        status.setText("Running...");
        requestFocusInWindow();
    }

    public void load() {
        ttrs.load();
        playing = true;
        repaint();
        requestFocusInWindow();
    }

    private void tick() {
        playing = !(ttrs.checkLost());
        if (playing) {
            int line = ttrs.getPos()[0];
            ttrs.moveDown();
            if (line == ttrs.getPos()[0]) { // it was not a valid move
                ttrs.addToBoard();
                ttrs.nextPiece();
                ttrs.clearLines();
                ttrs.refreshQueue();
                ttrs.save();
                playing = !(ttrs.checkLost());
            }
            repaint();
        } else {
            status.setText("You lost...");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ttrs.draw(g, width, height, playing);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width * 10, height * 20);
    }
}
