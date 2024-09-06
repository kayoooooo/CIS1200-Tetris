package org.cis1200.tetris;

public class SPiece extends Piece {

    static int[][] shape = new int[][] { { 0, 1, 1 }, { 1, 1, 0 }, { 0, 0, 0 } };
    static int color = 5;

    public SPiece(int[][] shape, int color) {
        super(shape, color);
    }

    public SPiece() {
        this(shape, color);
    }

}
