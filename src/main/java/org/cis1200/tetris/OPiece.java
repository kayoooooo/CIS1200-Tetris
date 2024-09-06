package org.cis1200.tetris;

public class OPiece extends Piece {
    static int[][] shape = new int[][] { { 0, 1, 1, 0 }, { 0, 1, 1, 0 },
        { 0, 0, 0, 0 } };
    static int color = 1;

    public OPiece(int[][] shape, int color) {
        super(shape, color);
    }

    public OPiece() {
        this(shape, color);
    }

    @Override
    public void ccwRotate() { // Squares are represented in 3x4 in tetris, so the rotate fx wouldn't
                              // work
        return;
    }

    @Override
    public void cwRotate() { // Squares are represented in 3x4 in tetris, so the rotate fx wouldn't
                             // work
        return;
    }
}
