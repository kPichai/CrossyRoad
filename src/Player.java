import javax.swing.*;
import java.awt.*;

public class Player extends GameObject {
    private boolean isPlayerOne;
    private int health;
    private EggBlaster eggBlaster;

    private Image image;

    public Player(int x, int y, int dx, int dy, boolean isPlayerOne) {
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
        g.drawImage(image, getX(), getY(), 50, 50, window);
    }
}
