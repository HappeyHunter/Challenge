package com.happeyhunter.challenge.data;

/**
 * Holds map coordinates
 */
public class Coordinates {

    private int xCoord;
    private int yCoord;

    /**
     * Creates a Coordinates object
     *
     * @param x     X coordinates
     * @param y     Y coordinates
     */
    public Coordinates(int x, int y) {
        xCoord = x;
        yCoord = y;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
}
