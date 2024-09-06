package org.cis1200.tetris;

import java.awt.*;

public abstract class Piece {
    int[][] shape;

    int color;

    public Piece(int[][] s, int c) {
        shape = s;
        color = c;
    }

    public void ccwRotate() {
        int[][] image = new int[shape.length][shape[0].length];
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                image[shape.length - (j + 1)][i] = shape[i][j];
            }
        }
        shape = image;
    }

    public void cwRotate() {
        int[][] image = new int[shape.length][shape[0].length];
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                image[j][shape[0].length - (i + 1)] = shape[i][j];
            }
        }
        shape = image;
    }

    public int[][] getShape() {
        return shape.clone();
    }

    public int getColor() {
        return color;
    }

}
