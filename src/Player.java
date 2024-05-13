// Created by Kieran Pichai

import javax.swing.*;
import java.awt.*;

public class Player extends GameObject {

    // Instance variables for each player
    private boolean isPlayerOne;
    private int health;
    private EggBlaster eggBlaster;
    private Image image;

    // Constructor that calls the superclass
    public Player(double x, double y, double dx, double dy, boolean isPlayerOne) {
        super(x, y, dx, dy, true);
        this.isPlayerOne = isPlayerOne;
        this.health = 4;
        eggBlaster = new EggBlaster(this.getX(), this.getY(), 0, 0);

        // Loads in image of player
        image = new ImageIcon("Resources/player_icon.png").getImage();
    }

    // Getters and setters for certain instance variables
    public int getHealth() {
        return health;
    }

    public EggBlaster getEggBlaster() {
        return eggBlaster;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    // Draws the player in specific orientations (as well as the egg blaster if in use)
    public void drawPlayer(Graphics g, CrossyRoadViewer window) {

        // Only draws if the player is currently shown
        if (getIsShown()) {

            // Draws the players egg blaster
            eggBlaster.drawEggBlaster(g, window);

            // Draws the player depending on which direction its facing
            if (getDx() <= 0) {
                g.drawImage(image, (int)getX(), (int)getY(), 70, 70, window);
            } else {
                g.drawImage(image, (int)getX() + (70), (int)getY(), -70, 70, window);
            }

            // Draws a little number beneath each player to indicate whether its player one or two
            g.setColor(Color.red);
            if (isPlayerOne) {
                window.displayCenteredText(g, "1", 70, 30, (int)getX(), (int)getY() + 70, 0);
            } else {
                window.displayCenteredText(g, "2", 70, 30, (int)getX(), (int)getY() + 70, 0);
            }
        }
    }

    // Uses the egg blaster
    public void useEggBlaster(CrossyRoad c) {
        if (isPlayerOne) {
            eggBlaster.use(c.getPlayerTwo());
        } else {
            eggBlaster.use(c.getPlayerOne());
        }
    }

    // Overrides super class method and moves the player depending on whether moving it might move it outside the screen (not ideal)
    @Override
    public void move() {

        // Checks if its moving out of the left or right of the screen
        if ((getX() > 1727 - 70 && getDx() > 0) || (getX() < 0 && getDx() < 0)) {
            if (!((getY() > 1063 - 70 && getDy() > 0) || (getY() < 0 && getDy() < 0))) {
                setY(getY() + getDy());
            }
        } else {

            // Checks if its moving out of the top or bottom of the screen
            setX(getX() + getDx());
            if (!((getY() > 1063 - 70 && getDy() > 0) || (getY() < 0 && getDy() < 0))) {
                setY(getY() + getDy());
            }
        }
    }
}
