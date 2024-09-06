package org.cis1200.tetris;

public class IPiece extends Piece {

    static int[][] shape = new int[][] { { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 },
        { 0, 0, 0, 0 } };

    static int color = 2;

    public IPiece(int[][] shape, int color) {
        super(shape, color);
    }

    public IPiece() {
        this(shape, color);
    }

}
