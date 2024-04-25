import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CrossyRoad implements ActionListener, KeyListener {
    private int gameState;
    private Player playerOne;
    private Player playerTwo;
    private ArrayList<Obstacle> obstaclesToRight;
    private ArrayList<Obstacle> obstaclesToLeft;
    private CrossyRoadViewer window;

    public CrossyRoad() {
        window = new CrossyRoadViewer(this);
        gameState = 0;
        playerOne = new Player(300, 900, 0, 0, true);
        playerTwo = new Player(1427, 900, 0, 0, false);
        // Figure out way to randomize and create all the obstacles
        obstaclesToRight = new ArrayList<Obstacle>();
        obstaclesToLeft = new ArrayList<Obstacle>();
        obstaclesToRight.add(new Obstacle(0, 700, 5, 0, 0));
        for (int i = 0; i < 30; i++) {
            if (i < 15) {
                obstaclesToRight.add(new Obstacle(generateNewCarMovingRight(obstaclesToRight.get(obstaclesToRight.size() - 1).getX()), 700, 5, 0, 0));
            } else {
                if (i == 15) {
                    obstaclesToRight.add(new Obstacle(0, 263, 5, 0, 0));
                } else {
                    obstaclesToRight.add(new Obstacle(generateNewCarMovingRight(obstaclesToRight.get(obstaclesToRight.size() - 1).getX()), 263, 5, 0, 0));
                }
            }
        }
        obstaclesToLeft.add(new Obstacle(1763, 750, -5, 0, 0));
        for (int i = 0; i < 30; i++) {
            if (i < 15) {
                obstaclesToLeft.add(new Obstacle(generateNewCarMovingLeft(obstaclesToLeft.get(obstaclesToLeft.size() - 1).getX()), 750, -5, 0, 0));
            } else {
                if (i == 15) {
                    obstaclesToLeft.add(new Obstacle(1763, 313, -5, 0, 0));
                } else {
                    obstaclesToLeft.add(new Obstacle(generateNewCarMovingLeft(obstaclesToLeft.get(obstaclesToLeft.size() - 1).getX()), 313, -5, 0, 0));
                }
            }
        }
        window.addKeyListener(this);
        Toolkit.getDefaultToolkit().sync();
        Timer clock = new Timer(30, this);
        clock.start();
    }

    public int generateNewCarMovingRight(int x) {
        return x - ((int)(Math.random()*200) + 90);
    }

    public int generateNewCarMovingLeft(int x) {
        return x + ((int)(Math.random()*200) + 90);
    }

    public void checkObstacles() {
        for (int i = 0; i < obstaclesToRight.size(); i++) {
            Obstacle o = obstaclesToRight.get(i);
            if (o.getX() > 1727) {
                int y = o.getY();
                obstaclesToRight.remove(i);
                obstaclesToRight.add(new Obstacle(generateNewCarMovingRight(obstaclesToRight.get(obstaclesToRight.size()-1).getX()), y, 5, 0, 0));
            }
        }
        for (int i = 0; i < obstaclesToLeft.size(); i++) {
            Obstacle o = obstaclesToLeft.get(i);
            if (o.getX() < -40) {
                int y = o.getY();
                obstaclesToLeft.remove(i);
                obstaclesToLeft.add(new Obstacle(generateNewCarMovingLeft(obstaclesToLeft.get(obstaclesToLeft.size()-1).getX()), y, -5, 0, 0));
            }
        }
    }

    public int getGameState() {
        return gameState;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public ArrayList<Obstacle> getObstaclesToRight() {
        return obstaclesToRight;
    }

    public ArrayList<Obstacle> getObstaclesToLeft() {
        return obstaclesToLeft;
    }

    public CrossyRoadViewer getWindow() {
        return window;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public void actionPerformed(ActionEvent e) {
        for (Obstacle o: obstaclesToRight) {
            o.move();
        }
        for (Obstacle o: obstaclesToLeft) {
            o.move();
        }
        checkObstacles();
        playerOne.move();
        playerTwo.move();
        window.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_W:
                playerOne.setDy(0);
                break;
            case KeyEvent.VK_A:
                playerOne.setDx(0);
                break;
            case KeyEvent.VK_S:
                playerOne.setDy(0);
                break;
            case KeyEvent.VK_D:
                playerOne.setDx(0);
                break;
            case KeyEvent.VK_LEFT:
                playerTwo.setDx(0);
                break;
            case KeyEvent.VK_RIGHT:
                playerTwo.setDx(0);
                break;
            case KeyEvent.VK_UP:
                playerTwo.setDy(0);
                break;
            case KeyEvent.VK_DOWN:
                playerTwo.setDy(0);
                break;
        }
    }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_W:
                playerOne.setDy(-5);
                break;
            case KeyEvent.VK_A:
                playerOne.setDx(-5);
                break;
            case KeyEvent.VK_S:
                playerOne.setDy(5);
                break;
            case KeyEvent.VK_D:
                playerOne.setDx(5);
                break;
            case KeyEvent.VK_LEFT:
                playerTwo.setDx(-5);
                break;
            case KeyEvent.VK_RIGHT:
                playerTwo.setDx(5);
                break;
            case KeyEvent.VK_UP:
                playerTwo.setDy(-5);
                break;
            case KeyEvent.VK_DOWN:
                playerTwo.setDy(5);
                break;
        }
    }

    public void runGame() {
        window.repaint();
    }

    public static void main(String[] args) {
        CrossyRoad c1 = new CrossyRoad();
        c1.runGame();
    }
}
