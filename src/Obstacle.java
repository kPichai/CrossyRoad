// Created by Kieran Pichai

import javax.swing.*;
import java.awt.*;

public class Obstacle extends GameObject {

    // All instance variables for the current cars
    private Image type;
    private boolean isLeftCar;

    // Constructor that initializes all positions and movement speeds / orientations of obstacle
    public Obstacle(double x, double y, int dx, int dy, int obstacleType, boolean isLeftCar) {
        super(x, y, dx, dy, true);
        this.isLeftCar = isLeftCar;

        // Loads car image in
        type = new ImageIcon("Resources/car_1.png").getImage();
    }

    // Draws the car based off of specific parameters
    public void drawObstacle(Graphics g, CrossyRoadViewer window) {

        // Draws the car depending on its orientation (left facing or right facing)
        if (isLeftCar) {
            g.drawImage(type, (int) this.getX() + (60), (int) this.getY(), -60, 40, window);
        } else {
            g.drawImage(type, (int)this.getX(), (int)this.getY(), 60, 40, window);
        }
    }
}
