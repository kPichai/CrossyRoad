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
        this.health = 4;
        image = new ImageIcon("Resources/player_icon.png").getImage();
        eggBlaster = new EggBlaster(this.getX(), this.getY(), 0, 0);
    }

    public boolean isPlayerOne() {
        return isPlayerOne;
    }

    public int getHealth() {
        return health;
    }

    public EggBlaster getEggBlaster() {
        return eggBlaster;
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
            g.setColor(Color.red);
            if (isPlayerOne) {
                window.displayCenteredText(g, "1", 70, 30, (int)getX(), (int)getY() + 70, 0);
                if (eggBlaster.isInUse()) {
                    eggBlaster.drawEggBlaster(g, window);
                }
            } else {
                window.displayCenteredText(g, "2", 70, 30, (int)getX(), (int)getY() + 70, 0);
                if (eggBlaster.isInUse()) {
                    eggBlaster.drawEggBlaster(g, window);
                }
            }

        }
    }

    public void useEggBlaster(CrossyRoad c) {
        if (isPlayerOne) {
            eggBlaster.use(c.getPlayerTwo());
        } else {
            eggBlaster.use(c.getPlayerOne());
        }
    }

    @Override
    public void move() {
        if ((getX() > 1727 - 70 && getDx() > 0) || (getX() < 0 && getDx() < 0)) {
            if ((getY() > 1063 - 70 && getDy() > 0) || (getY() < 0 && getDy() < 0)) {
                return;
            } else {
                setY(getY() + getDy());
            }
        } else {
            setX(getX() + getDx());
            if (!((getY() > 1063 - 70 && getDy() > 0) || (getY() < 0 && getDy() < 0))) {
                setY(getY() + getDy());
            }
        }
    }
}
