import javax.swing.*;
import java.awt.*;

public class CrossyRoadViewer extends JFrame {
    private final int WINDOW_WIDTH = 2000;
    private final int WINDOW_HEIGHT = 1500;
    private final int BANNER_HEIGHT = 23;
    private Image background;
    private CrossyRoad game;

    public CrossyRoadViewer(CrossyRoad game) {
        this.game = game;
        this.setDefaultCloseOperation(3);
        this.setTitle("--* 1v1 Crossy Roads *--");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void drawGameObjects(Graphics g) {

    }

    public void drawBackground(Graphics g) {

    }

    public void drawGameState(Graphics g) {

    }

    public void paint(Graphics g) {

    }
}
