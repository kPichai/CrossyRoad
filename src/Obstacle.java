import java.awt.*;

public class Obstacle extends GameObject {
    private Image[] obstacleTypes;
    private Image type;

    private int imageNum;
3
    public Obstacle(int x, int y, int dx, int dy, int obstacleType) {
        super(x, y, dx, dy, true);
        obstacleTypes = new Image[7];
        // TODO: LOAD IN ALL CAR / OBSTACLE IMAGES
        if (obstacleType == 1) {
            type = obstacleTypes[0];
        } else {
            imageNum = (int)(Math.random()*6) + 1;
            type = obstacleTypes[imageNum];
        }
    }

    public int getImageNum() {
        return imageNum;
    }

    public void drawObstacle(Graphics g) {

    }
}
