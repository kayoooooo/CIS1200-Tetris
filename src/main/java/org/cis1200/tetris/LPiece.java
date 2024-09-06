package org.cis1200.tetris;

public class LPiece extends Piece {
    static int[][] shape = new int[][] { { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 } };
    static int color = 4;

    public LPiece(int[][] shape, int color) {
        super(shape, color);
    }

    public LPiece() {
        this(shape, color);
    }

}
