import javax.swing.*;
import java.awt.*;

public class Obstacle extends GameObject {
    private Image[] obstacleTypes;
    private Image type;
    private boolean isLeftCar;
    private int imageNum;

    public Obstacle(double x, double y, int dx, int dy, int obstacleType, boolean isLeftCar) {
        super(x, y, dx, dy, true);
        obstacleTypes = new Image[2];
        this.isLeftCar = isLeftCar;
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
        if (isLeftCar) {
            if (getDx() > 0) {
                g.drawImage(type, (int)this.getX(), (int)this.getY(), 60, 40, window);
            } else if (getDx() <= 0) {
                g.drawImage(type, (int)this.getX() + (60), (int)this.getY(), -60, 40, window);
            }
        } else {
            if (getDx() >= 0) {
                g.drawImage(type, (int)this.getX(), (int)this.getY(), 60, 40, window);
            } else if (getDx() < 0) {
                g.drawImage(type, (int)this.getX() + (60), (int)this.getY(), -60, 40, window);
            }
        }
    }
}
