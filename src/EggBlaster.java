import java.awt.*;

public class EggBlaster extends GameObject {
    private final int INITIAL_EGG_AMOUNT = 3;
    private boolean isInUse;
    private int eggsLeft;

    public EggBlaster(int x, int y, int dx, int dy) {
        super(x, y, dx, dy, false);
        this.isInUse = false;
        this.eggsLeft = INITIAL_EGG_AMOUNT;
    }

    public boolean isInUse() {
        return isInUse;
    }

    public int getEggsLeft() {
        return eggsLeft;
    }

    public void changeEggsLeft() {
        eggsLeft--;
    }

    public void drawEggBlaster(Graphics g) {

    }
}
