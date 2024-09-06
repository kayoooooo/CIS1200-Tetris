package org.cis1200.tetris;

public class ZPiece extends Piece {

    static int[][] shape = new int[][] { { 1, 1, 0 }, { 0, 1, 1 }, { 0, 0, 0 } };
    static int color = 7;

    public ZPiece(int[][] shape, int color) {
        super(shape, color);
    }

    public ZPiece() {
        this(shape, color);
    }

}
