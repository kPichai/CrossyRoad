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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
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

    public static boolean checkCollision(Obstacle o, Player p) {
        double oX = o.getX(), oY = o.getY(), pX = p.getX(), pY = p.getY();
        if (oX > pX + 70 || oX + 60 < pX) {
            return false;
        }
        if (oY + 40 < pY || oY > pY + 70) {
            return false;
        }
        if (p.getY() < 325 && p.getY() > 215) {
            p.setXY(p.getX(), 500);
        } else if (p.getY() < 780 && p.getY() > 670) {
            p.setXY(p.getX(), 950);
        } else {
            p.setXY(p.getX(), p.getY() + 75);
        }
        p.setHealth(p.getHealth() - 2);
        return true;
    }

    public static boolean checkCollision(EggBlaster e, Player p) {
        double eX = e.getxInUse(), eY = e.getyInUse(), pX = p.getX(), pY = p.getY();
        if (eX > pX + 70 || eX + 120 < pX) {
            return false;
        }
        if (eY + 120 < pY || eY > pY + 70) {
            return false;
        }
        p.setHealth(p.getHealth() - 2);
        return true;
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
