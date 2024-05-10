import javax.swing.*;
import java.awt.*;

public class EggBlaster extends GameObject {
    private final int INITIAL_EGG_AMOUNT = 3;
    private boolean isInUse;
    private int eggsLeft;
    private double xInUse;
    private double yInUse;
    private Player p;
    private Image reticle;

    public EggBlaster(double x, double y, double dx, double dy) {
        super(x, y, dx, dy, false);
        this.isInUse = false;
        this.eggsLeft = INITIAL_EGG_AMOUNT;
        reticle = new ImageIcon("Resources/reticle.png").getImage();
    }

    public boolean isInUse() {
        return isInUse;
    }

    public double getxInUse() {
        return xInUse;
    }

    public double getyInUse() {
        return yInUse;
    }

    public int getEggsLeft() {
        return eggsLeft;
    }

    public void toggleInUse() {
        isInUse = !isInUse;
    }

    public void use(Player p) {
        this.p = p;
        xInUse = p.getX() - 15;
        yInUse = p.getY() - 15;
        toggleInUse();
        eggsLeft--;
    }

    public void hit() {
        GameObject.checkCollision(this, p);
        toggleInUse();
    }

    public void drawEggBlaster(Graphics g, CrossyRoadViewer window) {
        if (isInUse) {
            g.drawImage(reticle, (int)xInUse, (int)yInUse, 120, 120, window);
        }
    }
}
