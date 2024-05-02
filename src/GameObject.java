public class GameObject {
    private double x;
    private double y;
    private double dx;
    private double dy;
    private boolean isShown;

    public GameObject(double x, double y, double dx, double dy, boolean isShown) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.isShown = isShown;
    }

    public double getX() {
        return x;
    }

    public double getY() {
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

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static void checkCollision(Obstacle o, Player p) {
        double oX = o.getX(), oY = o.getY(), pX = p.getX(), pY = p.getY();
        if (oX > pX + 70 || oX + 60 < pX) {
            return;
        }
        if (oY + 40 < pY || oY > pY + 70) {
            return;
        }
        p.setHealth(p.getHealth() - 2);
        if (p.getY() < 425 && p.getY() > 210) {
            p.setXY(p.getX(), 590);
        } else if (p.getY() < 790 && p.getY() > 670) {
            p.setXY(p.getX(), 950);
        } else {
            p.setXY(p.getX(), p.getY() + 75);
        }
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
