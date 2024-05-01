import javax.swing.*;
import java.awt.*;

public class Obstacle extends GameObject {
    private Image[] obstacleTypes;
    private Image type;

    private int imageNum;

    public Obstacle(int x, int y, int dx, int dy, int obstacleType) {
        super(x, y, dx, dy, true);
        obstacleTypes = new Image[2];
        // quick fix for now, change later
        obstacleTypes[0] = new ImageIcon("Resources/car_1.png").getImage();
        obstacleTypes[1] = new ImageIcon("Resources/car_1.png").getImage();
        if (obstacleType == 1) {
            type = obstacleTypes[0];
        } else {
            // imageNum = (int)(Math.random()*6) + 1;
            // type = obstacleTypes[imageNum];
            type = obstacleTypes[1];
        }
    }

    public int getImageNum() {
        return imageNum;
    }

    public void drawObstacle(Graphics g, CrossyRoadViewer window) {
        if (getDx() >= 0) {
            g.drawImage(type, this.getX(), this.getY(), 60, 40, window);
        } else if (getDx() < 0) {
            g.drawImage(type, getX() + (60), getY(), -60, 40, window);
        }

    }
}
