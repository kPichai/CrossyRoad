public class GameObject {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private boolean isShown;

    public GameObject(int x, int y, int dx, int dy, boolean isShown) {
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

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public boolean isShown() {
        return isShown;
    }

    public void setShown(boolean isShown) {
        this.isShown = isShown;
    }

    public void move() {
        x += dx;
        y += dy;
    }


}
