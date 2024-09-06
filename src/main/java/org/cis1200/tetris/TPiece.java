package org.cis1200.tetris;

public class TPiece extends Piece {

    static int[][] shape = new int[][] { { 0, 1, 0 }, { 1, 1, 1 }, { 0, 0, 0 } };
    static int color = 6;

    public TPiece(int[][] shape, int color) {
        super(shape, color);
    }

    public TPiece() {
        this(shape, color);
    }

}
