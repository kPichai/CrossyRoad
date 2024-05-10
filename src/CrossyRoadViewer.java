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
        this.background = new ImageIcon("Resources/game_background.png").getImage();

        this.setDefaultCloseOperation(3);
        this.setTitle("--*   1v1 Crossy Roads   *--");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

        createBufferStrategy(2);
    }

    public CrossyRoad getGame() {
        return game;
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

    public void drawPlayerInfo(Graphics g) {
        g.drawRoundRect(0, WINDOW_HEIGHT - 60 - BANNER_HEIGHT, 120, 60, 10, 10);
        g.drawRoundRect(WINDOW_WIDTH - 120, WINDOW_HEIGHT - 60 - BANNER_HEIGHT, 120, 60, 10, 10);
        g.setColor(Color.WHITE);
        g.fillRoundRect(0, WINDOW_HEIGHT - 60 - BANNER_HEIGHT, 120, 60, 10, 10);
        g.fillRoundRect(WINDOW_WIDTH - 120, WINDOW_HEIGHT - 60 - BANNER_HEIGHT, 120, 60, 10, 10);
        g.drawRoundRect(10, WINDOW_HEIGHT - 50 - BANNER_HEIGHT, 100 - (25*(4 - game.getPlayerOne().getHealth())), 28, 10, 10);
        g.drawRoundRect(WINDOW_WIDTH - 110, WINDOW_HEIGHT - 50 - BANNER_HEIGHT, 100 - (25*(4 - game.getPlayerTwo().getHealth())), 28, 10, 10);
        g.setColor(Color.green);
        g.fillRoundRect(10, WINDOW_HEIGHT - 50 - BANNER_HEIGHT, 100 - (25*(4 - game.getPlayerOne().getHealth())), 28, 10, 10);
        g.fillRoundRect(WINDOW_WIDTH - 110, WINDOW_HEIGHT - 50 - BANNER_HEIGHT, 100 - (25*(4 - game.getPlayerTwo().getHealth())), 28, 10, 10);
    }

    public void drawBackground(Graphics g) {
        g.drawImage(background,0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
    }

    public void displayCenteredText(Graphics g, String text, int width, int height, int xPos, int yPos, int yShift) {
        int w = width;
        int h = height;
        // Uses FontMetrics object to get properties about the current font such as pixel height and total string width
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        // Calculates correct x and y position such that the center of the text is centered in the chosen box
        int x = xPos + (w - metrics.stringWidth(text)) / 2;
        int y = yPos + ((h - metrics.getHeight()) / 2) + metrics.getAscent();
        // Prints the string onto the window using the calculated x and y coordinates as well as the vertical shift
        g.drawString(text, x, y - yShift);
    }

    public void drawGameState(Graphics g) {
        int state = game.getGameState();
        if (state == 0) {
            g.drawRoundRect(200, 200, WINDOW_WIDTH-400, WINDOW_HEIGHT-400, 50, 50);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRoundRect(200, 200, WINDOW_WIDTH-400, WINDOW_HEIGHT-400, 50, 50);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            g.setColor(Color.BLACK);
            displayCenteredText(g, "Welcome to 1v1 Crossy Roads!", WINDOW_WIDTH-400, WINDOW_HEIGHT-400, 200, 200, 240);
            displayCenteredText(g, "There are 2 ways to win, first to the finish line or hit the other player with an egg.", WINDOW_WIDTH-400, WINDOW_HEIGHT-400, 200, 200, 80);
            displayCenteredText(g, "Player one - Use 'WASD' to move and 'Q' to your the egg blaster", WINDOW_WIDTH-400, WINDOW_HEIGHT-400, 200, 200, -80);
            displayCenteredText(g, "Player two - Use 'arrows keys' to move and '/' to use your egg blaster", WINDOW_WIDTH-400, WINDOW_HEIGHT-400, 200, 200, -240);
        } else if (state == 1) {
            g.drawRoundRect(300, 300, WINDOW_WIDTH-600, WINDOW_HEIGHT-600, 30, 30);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRoundRect(300, 300, WINDOW_WIDTH-600, WINDOW_HEIGHT-600, 30, 30);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 46));
            g.setColor(Color.BLACK);
            displayCenteredText(g, "Player 1 is the winner! Congrats!", WINDOW_WIDTH-400, WINDOW_HEIGHT-400, 200, 200, 0);
        } else if (state == 2) {
            g.drawRoundRect(300, 300, WINDOW_WIDTH-600, WINDOW_HEIGHT-600, 30, 30);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRoundRect(300, 300, WINDOW_WIDTH-600, WINDOW_HEIGHT-600, 30, 30);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 46));
            g.setColor(Color.BLACK);
            displayCenteredText(g, "Player 2 is the winner! Congrats!", WINDOW_WIDTH-400, WINDOW_HEIGHT-400, 200, 200, 0);
        }
    }

    public void myPaint(Graphics g) {
        drawBackground(g);
        drawGameObjects(g);
        drawGameState(g);
        drawPlayerInfo(g);
    }

    @Override
    public void paint(Graphics g) {
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null) {
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
