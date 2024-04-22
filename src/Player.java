import java.awt.*;

public class Player extends GameObject {
    private boolean isPlayerOne;
    private int health;
    private EggBlaster eggBlaster;

    public Player(int x, int y, int dx, int dy, boolean isPlayerOne, int health) {
        super(x, y, dx, dy, true);
        this.isPlayerOne = isPlayerOne;
        this.health = health;
        eggBlaster = new EggBlaster(FINISH THIS PART);
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

    public void drawPlayer(Graphics g) {

    }
}
