package org.cis1200.tetris;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Tetris {
    private int[][] board;

    private int[] position;

    private static Color[] colors = new Color[] { Color.BLACK, Color.YELLOW, Color.CYAN, Color.BLUE,
        Color.ORANGE, Color.GREEN, Color.MAGENTA, Color.RED };

    private static LinkedList<Piece> pieces = new LinkedList<Piece>(
            Arrays.asList(
                    new Piece[] { new OPiece(), new LPiece(), new JPiece(),
                        new IPiece(), new TPiece(), new ZPiece(), new SPiece() }
            )
    );

    private LinkedList<Piece> queue;

    public Tetris() {
        reset();
    }

    public void reset() {
        board = new int[20][10];
        queue = new LinkedList<Piece>();
        LinkedList<Piece> holder = (LinkedList<Piece>) pieces.clone();
        Collections.shuffle(holder);
        queue.addAll(holder);
        position = new int[] { 0, 4 };
    }

    public int[] getPos() {
        return Arrays.copyOf(position, position.length);
    }

    public Piece currentPiece() {
        return queue.getFirst();
    }

    public void nextPiece() {
        queue.remove();
        this.setPos(new int[] { 0, 4 });
    }

    public void refreshQueue() {
        if (queue.size() < 2) {
            LinkedList<Piece> holder = (LinkedList<Piece>) pieces.clone();
            Collections.shuffle(holder);
            queue.addAll(holder);
        }
    }

    public boolean checkLost() {
        if (board[0][4] != 0 || board[0][5] != 0 || board[0][3] != 0 || board[0][6] != 0) {
            return true;
        }
        return false;
    }

    public void clearLines() {
        int i = 0;
        while (i < 20) {
            boolean full = true;
            for (int index : board[i]) {
                if (index == 0) {
                    full = false;
                    break;
                }
            }
            if (full) {
                for (int j = i; j > 0; j--) {
                    board[j] = board[j - 1];
                }
                board[0] = new int[10];
            }
            i++;
        }
    }

    public boolean validPosition(Piece p, int[] pos) {
        int[][] shape = p.getShape();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != 0) {
                    if (!(pos[0] + i - 1 < 0)) {
                        if ((pos[0] + i - 1 >= 20) || (pos[1] + j - 1 >= 10)
                                || (pos[1] + j - 1 < 0)) {
                            return false;
                        }
                        if (board[pos[0] + i - 1][pos[1] + j - 1] != 0) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void addToBoard() { // don't need to test adding while invalid as this will only
                                      // be called on valid
        int[][] shape = currentPiece().getShape();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != 0 && !(position[0] + i - 1 < 0)) {
                    board[position[0] + i - 1][position[1] + j - 1] = currentPiece().getColor();
                }
            }
        }
    }

    // functions for actions that can happen which affect where the piece is
    public void moveLeft() {
        int[] pos = this.getPos();
        System.out.println(pos[1]);
        System.out.println(pos[0]);
        pos[1]--;
        if (validPosition(this.getQueue().getFirst(), pos)) {
            position[1] -= 1;
        }
    }

    public void moveRight() {
        int[] pos = this.getPos();
        pos[1]++;
        if (validPosition(this.getQueue().getFirst(), pos)) {
            position[1] += 1;
        }
    }

    public void moveDown() {
        int[] pos = this.getPos();
        pos[0]++;
        if (validPosition(this.getQueue().getFirst(), pos)) {
            position[0] += 1;
        }
    }

    public void rotate() {
        currentPiece().ccwRotate();
        if (!validPosition(currentPiece(), position)) {
            currentPiece().cwRotate();
        }
    }

    public void hardDrop() {
        while (true) {
            int[] pos = this.getPos();
            moveDown();
            if (pos[0] == this.getPos()[0]) {
                break;
            }
        }
    }

    // testing purposes of setting board state
    public void setLine(int i, int[] line) {
        board[i] = line;
    }

    public int[] getLine(int i) {
        return board[i].clone();
    }

    public void setPos(int[] p) {
        position = p;
    }

    public void addFirst(Piece p) {
        this.queue.addFirst(p);
    }

    public LinkedList<Piece> getQueue() {
        return (LinkedList<Piece>) queue.clone();
    }

    public void save() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("state.txt", false));
            for (int[] line : board) {
                String toParse = Arrays.toString(line);
                toParse = toParse.substring(1, toParse.length() - 1);
                bw.write(toParse + "\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("state.txt"));
            for (int i = 0; i < 20; i++) {
                String[] nodes = br.readLine().split(", ");
                int[] holder = new int[10];
                for (int j = 0; j < 10; j++) {
                    holder[j] = Integer.valueOf(nodes[j]);
                }
                this.setLine(i, holder);
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics g, int w, int h, boolean p) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                g.setColor(colors[board[i][j]]);
                g.fillRect(j * w, i * h, w, h);
                g.setColor(Color.WHITE);
                g.drawRect(j * w, i * h, w, h);
            }
        }
        if (p) {
            int[][] shape = this.getQueue().getFirst().getShape();
            for (int i = 0; i < shape.length; i++) {
                for (int j = 0; j < shape[0].length; j++) {
                    if (((position[0] + i - 1 < 20) && (position[1] + j - 1 < 10)
                            && (position[1] + j - 1 > -1)
                            && (position[0] + i - 1 > -1))) {
                        if (shape[i][j] != 0) {
                            g.setColor(colors[this.getQueue().getFirst().getColor()]);
                            g.fillRect(
                                    (position[1] + j - 1) * w,
                                    (position[0] + i - 1) * h, w, h
                            );
                        }
                    }
                }
            }
        }
    }
}
