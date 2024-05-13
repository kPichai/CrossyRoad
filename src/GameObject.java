// Created by Kieran Pichai

public class GameObject {

    // Instance variables for movement
    private double x;
    private double y;
    private double dx;
    private double dy;
    private boolean isShown;

    // Constructor that adds movement information
    public GameObject(double x, double y, double dx, double dy, boolean isShown) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.isShown = isShown;
    }

    // Getters and setters for all variables found below
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public boolean getIsShown() {
        return isShown;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setIsShown(boolean isShown) {
        this.isShown = isShown;
    }

    // Resets both velocities, completly stops that specific objects movement
    public void resetDxDy() {
        dx = 0;
        dy = 0;
    }

    // Sets the positions to a certain value
    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Checks for a collision between a player and an obstacle (car)
    public static boolean checkCollision(Obstacle o, Player p) {

        // Stores their positions for easier access
        double oX = o.getX(), oY = o.getY(), pX = p.getX(), pY = p.getY();

        // Checks the two cases where its impossible for them to be intersecting (x's are out of range or same with y)
        if (oX > pX + 70 || oX + 60 < pX) {
            return false;
        }
        if (oY + 40 < pY || oY > pY + 70) {

            // returns false to exit as its impossible for a collision to have occured
            return false;
        }

        // Checks in which part of the screen the collision occured so it can decrement the position correctly
        if (p.getY() < 325 && p.getY() > 215) {
            p.setXY(p.getX(), 500);
        } else if (p.getY() < 780 && p.getY() > 670) {
            p.setXY(p.getX(), 950);
        } else {
            p.setXY(p.getX(), p.getY() + 75);
        }
        p.setHealth(p.getHealth() - 2);

        // Returns true at end because its guaranteed a collision has occured if all the checks above passed
        return true;
    }

    // Checks the collision between an egg blaster and a player now
    public static boolean checkCollision(EggBlaster e, Player p) {

        // Exact same logic as the one above, just different values and ranges as the objects are different sizes
        double eX = e.getxInUse(), eY = e.getyInUse(), pX = p.getX(), pY = p.getY();
        if (eX > pX + 70 || eX + 120 < pX) {
            return false;
        }
        if (eY + 120 < pY || eY > pY + 70) {
            return false;
        }
        p.setHealth(p.getHealth() - 2);
        return true;
    }

    // Simply moves the player a certain amount
    public void move() {

        // Increments x and y by their specified velocities
        x += dx;
        y += dy;
    }
}