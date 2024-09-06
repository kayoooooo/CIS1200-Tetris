package org.cis1200.tetris;

public class JPiece extends Piece {
    static int[][] shape = new int[][] { { 1, 0, 0 }, { 1, 1, 1 }, { 0, 0, 0 } };
    static int color = 3;

    public JPiece(int[][] shape, int color) {
        super(shape, color);
    }

    public JPiece() {
        this(shape, color);
    }

}
