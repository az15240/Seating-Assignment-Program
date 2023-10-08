/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seatingassignment;

/**
 * @author az15240
 */
public class Pair {
    
    private int x;
    private int y;
    
    /**
     * Create a Pair object using x and y coordinates
     * @param _x x coordinate of the position
     * @param _y y coordinate of the position
     */
    public Pair(int _x, int _y) {
        x=_x;
        y=_y;
    }
    
    /**
     * Get the x coordinate of the pair
     * @return x coordinate of the pair
     */
    public int getX() {
        return x;
    }
    
    /**
     * Set the x coordinate of the pair to _x
     * @param _x x coordinate assigned
     */
    public void setX(int _x) {
        x=_x;
    }
    
    /**
     * Get the y coordinate of the pair
     * @return y coordinate of the pair
     */
    public int getY() {
        return y;
    }
    
    /**
     * Set the y coordinate of the pair to _y
     * @param _y y coordinate assigned
     */
    public void setY(int _y) {
        y=_y;
    }
    
    @Override
    public boolean equals(Object var) {
        if(var instanceof Pair) {
            Pair pr = (Pair)var;
            return this.x==pr.getX() && this.y==pr.getY();
        }
        return false;
    }
    
    /**
     * Overrides the toString method, return the String format of the pair
     * @return String format of the pair
     */
    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}
