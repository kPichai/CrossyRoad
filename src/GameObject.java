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

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public boolean isShown() {
        return isShown;
    }
    public void resetDxDy() {
        dx = 0;
        dy = 0;
    }

    public void setShown(boolean isShown) {
        this.isShown = isShown;
    }

    public void move() {
        x += dx;
        y += dy;
    }
}
