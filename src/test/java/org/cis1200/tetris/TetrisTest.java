package org.cis1200.tetris;

//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import static org.junit.Assert.*;

public class TetrisTest {

    // TESTS FOR THE GAME STATE
    @Test
    public void clearLine() {
        Tetris ttrs = new Tetris();
        assertEquals(ttrs.getLine(19)[0], 0);
        int[] fullLine = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        int[] newLine = new int[] { 1, 2, 0, 0, 0, 0, 0, 2, 0, 1 };
        ttrs.setLine(19, fullLine);
        ttrs.setLine(18, newLine);
        assertEquals(1, ttrs.getLine(19)[0]);
        assertEquals(2, ttrs.getLine(18)[1]);
        ttrs.clearLines();
        assertEquals(2, ttrs.getLine(19)[1]);
    }

    @Test
    public void clearMultipleNonAdjacentLines() {
        Tetris ttrs = new Tetris();
        assertEquals(ttrs.getLine(19)[0], 0);
        int[] fullLine = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        int[] newLine = new int[] { 1, 2, 0, 0, 0, 0, 0, 2, 0, 1 };
        ttrs.setLine(19, fullLine);
        ttrs.setLine(18, newLine);
        ttrs.setLine(17, fullLine);
        ttrs.setLine(16, fullLine);
        ttrs.setLine(15, newLine);
        assertEquals(1, ttrs.getLine(19)[0]);
        assertEquals(2, ttrs.getLine(18)[1]);
        ttrs.clearLines();
        assertEquals(2, ttrs.getLine(19)[1]);
        assertEquals(2, ttrs.getLine(18)[1]);
    }

    @Test
    public void popPiece() {
        Tetris ttrs = new Tetris();
        ttrs.nextPiece();
        assertEquals(6, ttrs.getQueue().size());
    }

    // TESTS FOR PIECE ROTATION
    @Test
    public void oPiece() { // slightly trivial
        OPiece test = new OPiece();
        int[][] state = test.getShape();
        test.ccwRotate();
        assertEquals(state, test.getShape());
    }

    @Test
    public void sPiece() { // less trivial, should work for all 3x3, as they all inherit rotate.
        SPiece test = new SPiece();
        int[][] image = new int[][] { { 1, 0, 0 }, { 1, 1, 0 }, { 0, 1, 0 } };
        test.ccwRotate();
        assertEquals(image, test.getShape());
    }

    @Test
    public void iPiece() { // less trivial, plus a different rotation.
        IPiece test = new IPiece();
        int[][] image = new int[][] { { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 },
            { 0, 1, 0, 0 } };
        test.ccwRotate();
        assertEquals(image, test.getShape());
    }

    // TESTS FOR VALID POSITIONS
    @Test
    public void rotatingInvalid() { // testing piece against the edge
        Tetris ttrs = new Tetris();
        TPiece test = new TPiece();
        ttrs.addFirst(test);
        ttrs.rotate();
        ttrs.setPos(new int[] { 3, 9 });
        assertTrue(ttrs.validPosition(ttrs.currentPiece(), ttrs.getPos()));
        ttrs.rotate();
        assertTrue(ttrs.validPosition(ttrs.currentPiece(), ttrs.getPos())); // the rotation did not
                                                                            // happen
    }

    @Test
    public void movingInvalidNoChange() { // testing piece against the edge
        Tetris ttrs = new Tetris();
        TPiece test = new TPiece();
        ttrs.addFirst(test);
        ttrs.rotate();
        ttrs.setPos(new int[] { 3, 9 });
        assertTrue(ttrs.validPosition(ttrs.currentPiece(), ttrs.getPos()));
        ttrs.moveRight();
        assertTrue(ttrs.validPosition(ttrs.currentPiece(), ttrs.getPos())); // piece was not put
                                                                            // into invalid
        // position
    }

    @Test
    public void invalidMoveLeft() { // testing piece against the edge
        Tetris ttrs = new Tetris();
        TPiece test = new TPiece();
        ttrs.addFirst(test);
        ttrs.rotate();
        ttrs.rotate();
        ttrs.rotate();
        ttrs.setPos(new int[] { 3, 0 });
        assertTrue(ttrs.validPosition(ttrs.currentPiece(), ttrs.getPos()));
        ttrs.moveLeft();
        assertTrue(ttrs.validPosition(ttrs.currentPiece(), ttrs.getPos()));
        assertEquals(0, ttrs.getPos()[1]);
    }

    @Test
    public void hardDrop() {
        Tetris ttrs = new Tetris();
        TPiece test = new TPiece();
        ttrs.addFirst(test);
        ttrs.hardDrop();
        assertEquals(19, ttrs.getPos()[0]);
    }

    @Test
    public void downInvalid() {
        Tetris ttrs = new Tetris();
        TPiece test = new TPiece();
        ttrs.addFirst(test);
        ttrs.hardDrop();
        assertEquals(19, ttrs.getPos()[0]);
        int holder = ttrs.getPos()[0];
        ttrs.moveDown();
        assertEquals(holder, ttrs.getPos()[0]);
    }

    @Test
    public void placePiece() {
        Tetris ttrs = new Tetris();
        TPiece test = new TPiece();
        ttrs.getQueue().addFirst(test);
        ttrs.hardDrop();
        ttrs.addToBoard();
        assertNotEquals(0, ttrs.getLine(19)[4]);
    }

    @Test
    public void saveLoad() {
        Tetris ttrs = new Tetris();
        TPiece test = new TPiece();
        ttrs.getQueue().addFirst(test);
        ttrs.hardDrop();
        ttrs.addToBoard();
        assertNotEquals(0, ttrs.getLine(19)[4]);
        ttrs.save();
        Tetris t2 = new Tetris();
        t2.load();
        assertNotEquals(0, ttrs.getLine(19)[4]);
    }

    @Test
    public void gameOver() {
        Tetris ttrs = new Tetris();
        TPiece test = new TPiece();
        ttrs.getQueue().addFirst(test);
        ttrs.addToBoard();
        assertNotEquals(0, ttrs.getLine(0)[4]);
        assertTrue(ttrs.checkLost());
    }

    @Test
    public void addCanClear() { // simulates a "Tetris" (simultaneous 4 line clear)
        Tetris ttrs = new Tetris();
        IPiece test = new IPiece();
        ttrs.getQueue().addFirst(test);
        ttrs.rotate();
        ttrs.setPos(new int[]{0,9});
        ttrs.hardDrop();
        int[] line = new int[]{1,1,1,1,1,1,1,1,1,0};
        ttrs.setLine(19, line);
        ttrs.setLine(18, line);
        ttrs.setLine(17, line);
        ttrs.setLine(16, line);
        ttrs.addToBoard();
        ttrs.clearLines();
        assertEquals(0, ttrs.getLine(19)[0]);
        assertEquals(0, ttrs.getLine(18)[0]);
        assertEquals(0, ttrs.getLine(17)[0]);
        assertEquals(0, ttrs.getLine(16)[0]);
    }

    @Test
    public void invalidMoveInBoard() { // testing collisions
        Tetris ttrs = new Tetris();
        TPiece test = new TPiece();
        ttrs.addFirst(test);
        ttrs.rotate();
        ttrs.rotate();
        ttrs.setPos(new int[] { 18, 4 });
        int[] line = new int[]{0,0,0,0,0,1,1,1,1,1};
        ttrs.setLine(19, line); //we will collide into this line.
        assertTrue(ttrs.validPosition(ttrs.currentPiece(), ttrs.getPos()));
        ttrs.moveRight();
        assertTrue(ttrs.validPosition(ttrs.currentPiece(), ttrs.getPos()));
        assertEquals(4, ttrs.getPos()[1]);
    }
}
