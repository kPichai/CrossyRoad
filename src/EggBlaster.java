// Created by Kieran Pichai

import javax.swing.*;
import java.awt.*;

public class EggBlaster extends GameObject {

    // All instance variables for the egg blaster
    private final int INITIAL_EGG_AMOUNT = 3;
    private boolean isInUse;
    private int eggsLeft;
    private double xInUse;
    private double yInUse;
    private Player p;
    private Image reticle;

    // Constructor that initializes the egg blaster
    public EggBlaster(double x, double y, double dx, double dy) {
        super(x, y, dx, dy, false);
        this.isInUse = false;
        this.eggsLeft = INITIAL_EGG_AMOUNT;

        // Loads in egg blaster image
        reticle = new ImageIcon("Resources/reticle.png").getImage();
    }

    // Getters for certain instance variables
    public double getxInUse() {
        return xInUse;
    }

    public double getyInUse() {
        return yInUse;
    }

    public int getEggsLeft() {
        return eggsLeft;
    }

    // Toggles the isInUse variable between true and false depending on the current user input
    public void toggleInUse() {
        isInUse = !isInUse;
    }

    // Stores the player the egg blaster is aimed at and uses it
    public void use(Player p) {
        this.p = p;
        xInUse = p.getX() - 25;
        yInUse = p.getY() - 25;
        toggleInUse();
        eggsLeft--;
    }

    // Checks for collisions between player and egg blaster
    public void hit() {
        GameObject.checkCollision(this, p);

        // Egg blaster is no longer being moved so it turns it off
        toggleInUse();
    }

    // Draws the egg blaster
    public void drawEggBlaster(Graphics g, CrossyRoadViewer window) {

        // Only draws if its current being used
        if (isInUse) {
            g.drawImage(reticle, (int)xInUse, (int)yInUse, 120, 120, window);
        }
    }
}
