import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class CrossyRoadViewer extends JFrame {
    private final int WINDOW_WIDTH = 1727;
    private final int WINDOW_HEIGHT = 1063;
    private final int BANNER_HEIGHT = 23;
    private Image background;
    private CrossyRoad game;

    public CrossyRoadViewer(CrossyRoad game) {
        this.game = game;

        this.setDefaultCloseOperation(3);
        this.setTitle("--*   1v1 Crossy Roads   *--");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

        createBufferStrategy(2);
    }

    public void drawGameObjects(Graphics g) {
        for (Obstacle o: game.getObstaclesToRight()) {
            o.drawObstacle(g, this);
        }
        for (Obstacle o: game.getObstaclesToLeft()) {
            o.drawObstacle(g, this);
        }
        game.getPlayerOne().drawPlayer(g, this);
        game.getPlayerTwo().drawPlayer(g, this);
    }

    public void drawBackground(Graphics g) {
        // temporary
        g.drawRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public void drawGameState(Graphics g) {

    }

    public void myPaint(Graphics g) {
        drawBackground(g);
        drawGameObjects(g);
    }

    @Override
    public void paint(Graphics g) {
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null) {
            System.out.println("Here");
            return;
        }

        Graphics g2 = null;

        try {
            g2 = bf.getDrawGraphics();
            // myPaint does the actual drawing, as described in ManyBallsView
            myPaint(g2);
        }
        finally {
            // It is best to dispose() a Graphics object when done with it.
            g2.dispose();
        }

        // Shows the contents of the backbuffer on the screen.
        bf.show();

        //Tell the System to do the Drawing now, otherwise it can take a few extra ms until
        //Drawing is done which looks very jerky
        Toolkit.getDefaultToolkit().sync();
    }
}
