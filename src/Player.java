import javax.swing.*;
import java.awt.*;

public class Player extends GameObject {
    private boolean isPlayerOne;
    private int health;
    private EggBlaster eggBlaster;

    private Image image;

    public Player(double x, double y, double dx, double dy, boolean isPlayerOne) {
        super(x, y, dx, dy, true);
        this.isPlayerOne = isPlayerOne;
        this.health = 1;
        image = new ImageIcon("Resources/player_icon.png").getImage();
        eggBlaster = new EggBlaster(this.getX(), this.getY(), 0, 0);
    }

    public boolean isPlayerOne() {
        return isPlayerOne;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void drawPlayer(Graphics g, CrossyRoadViewer window) {
        if (getIsShown()) {
            if (getDx() <= 0) {
                g.drawImage(image, (int)getX(), (int)getY(), 70, 70, window);
            } else {
                g.drawImage(image, (int)getX() + (70), (int)getY(), -70, 70, window);
            }
        }
    }
}
