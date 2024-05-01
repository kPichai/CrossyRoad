public class GameObject {
    private int x;
    private int y;
    private double dx;
    private double dy;
    private boolean isShown;

    public GameObject(int x, int y, double dx, double dy, boolean isShown) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.isShown = isShown;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public boolean getIsShown() {
        return isShown;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void setIsShown(boolean isShown) {
        this.isShown = isShown;
    }

    public void resetDxDy() {
        dx = 0;
        dy = 0;
    }

    public static void checkCollision(Obstacle o, Player p) {
        int oX = o.getX(), oY = o.getY(), pX = p.getX(), pY = p.getY();
        if (oX > pX + 70 || oX + 60 < pX) {
            return;
        }
        if (oY + 40 < pY || oY > pY + 70) {
            return;
        }
        p.setHealth(0);
    }

    // TO-DO:
//    public static void checkCollision(EggBlaster o, Player p) {
//
//    }

    public void move() {
        x += dx;
        y += dy;
    }
}
