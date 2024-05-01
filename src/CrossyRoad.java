import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CrossyRoad implements ActionListener, KeyListener {
    private int gameState;
    private Player playerOne;
    private Player playerTwo;
    private int time;
    private ArrayList<Obstacle> obstaclesToRight;
    private ArrayList<Obstacle> obstaclesToLeft;
    private CrossyRoadViewer window;

    public CrossyRoad() {
        window = new CrossyRoadViewer(this);
        gameState = 0;
        time = 0;
        playerOne = new Player(300, 900, 0, 0, true);
        playerTwo = new Player(1427, 900, 0, 0, false);
        // Figure out way to randomize and create all the obstacles
        obstaclesToRight = new ArrayList<Obstacle>();
        obstaclesToLeft = new ArrayList<Obstacle>();
        obstaclesToRight.add(new Obstacle(0, 703, 5, 0, 0, false));
        for (int i = 0; i < 30; i++) {
            if (i < 15) {
                obstaclesToRight.add(new Obstacle(generateNewCarMovingRight(obstaclesToRight.get(obstaclesToRight.size() - 1).getX()), 703, 5, 0, 0, false));
            } else {
                if (i == 15) {
                    obstaclesToRight.add(new Obstacle(0, 248, 5, 0, 0, false));
                } else {
                    obstaclesToRight.add(new Obstacle(generateNewCarMovingRight(obstaclesToRight.get(obstaclesToRight.size() - 1).getX()), 248, 5, 0, 0, false));
                }
            }
        }
        obstaclesToLeft.add(new Obstacle(1763, 797, -5, 0, 0, true));
        for (int i = 0; i < 30; i++) {
            if (i < 15) {
                obstaclesToLeft.add(new Obstacle(generateNewCarMovingLeft(obstaclesToLeft.get(obstaclesToLeft.size() - 1).getX()), 797, -5, 0, 0, true));
            } else {
                if (i == 15) {
                    obstaclesToLeft.add(new Obstacle(1763, 342, -5, 0, 0, true));
                } else {
                    obstaclesToLeft.add(new Obstacle(generateNewCarMovingLeft(obstaclesToLeft.get(obstaclesToLeft.size() - 1).getX()), 342, -5, 0, 0, true));
                }
            }
        }
        window.addKeyListener(this);
        Toolkit.getDefaultToolkit().sync();
        Timer clock = new Timer(15, this);
        clock.start();
    }

    public double generateNewCarMovingRight(double x) {
        return x - ((int)(Math.random()*250) + 75);
    }

    public double generateNewCarMovingLeft(double x) {
        return x + ((int)(Math.random()*250) + 75);
    }

    public void checkWinner() {
        if (gameState == 1 || gameState == 2) {
            return;
        }
        if (playerOne.getY() <= 0) {
            gameState = 1;
            playerTwo.setIsShown(false);
            pauseGameMovement();
            return;
        } else if (playerTwo.getY() <= 0) {
            gameState = 2;
            playerOne.setIsShown(false);
            pauseGameMovement();
            return;
        }
        for (Obstacle o: obstaclesToRight) {
            GameObject.checkCollision(o, playerOne);
            GameObject.checkCollision(o, playerTwo);
        }
        for (Obstacle o: obstaclesToLeft) {
            GameObject.checkCollision(o, playerOne);
            GameObject.checkCollision(o, playerTwo);
        }
        if (playerOne.getHealth() == 0) {
            gameState = 2;
            playerOne.setIsShown(false);
            pauseGameMovement();
        } else if (playerTwo.getHealth() == 0) {
            gameState = 1;
            playerTwo.setIsShown(false);
            pauseGameMovement();
        }
    }

    public void checkObstacles() {
        for (int i = 0; i < obstaclesToRight.size(); i++) {
            Obstacle o = obstaclesToRight.get(i);
            if (o.getX() > 1727) {
                double y = o.getY();
                obstaclesToRight.remove(i);
                int j;
                for (j = obstaclesToRight.size() - 1; j >= 0; j--) {
                    if (obstaclesToRight.get(j).getY() == y) {
                        break;
                    }
                }
                i--;
                obstaclesToRight.add(new Obstacle(generateNewCarMovingRight(obstaclesToRight.get(j).getX()), y, 5, 0, 0, false));
            }
        }
        for (int i = 0; i < obstaclesToLeft.size(); i++) {
            Obstacle o = obstaclesToLeft.get(i);
            if (o.getX() < -60) {
                double y = o.getY();
                obstaclesToLeft.remove(i);
                int j;
                for (j = obstaclesToLeft.size() - 1; j >= 0; j--) {
                    if (obstaclesToLeft.get(j).getY() == y) {
                        break;
                    }
                }
                i--;
                obstaclesToLeft.add(new Obstacle(generateNewCarMovingLeft(obstaclesToLeft.get(j).getX()), y, -5, 0, 0, true));
            }
        }
    }

    public void pauseGameMovement() {
        for (Obstacle o: obstaclesToLeft) {
            o.resetDxDy();
        }
        for (Obstacle o: obstaclesToRight) {
            o.resetDxDy();
        }
        playerOne.resetDxDy();
        playerTwo.resetDxDy();
        window.repaint();
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
        time += 15;
        if (time > 5400) {
            gameState = -1;
        }
        for (Obstacle o: obstaclesToRight) {
            o.move();
        }
        for (Obstacle o: obstaclesToLeft) {
            o.move();
        }
        checkObstacles();
        playerOne.move();
        playerTwo.move();
        checkWinner();
        window.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (gameState == -1) {
            switch (e.getKeyCode()) {
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
    }

    public void keyPressed(KeyEvent e) {
        if (gameState == -1) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    playerOne.setDy(-2.5);
                    break;
                case KeyEvent.VK_A:
                    playerOne.setDx(-2.5);
                    break;
                case KeyEvent.VK_S:
                    playerOne.setDy(2.5);
                    break;
                case KeyEvent.VK_D:
                    playerOne.setDx(2.5);
                    break;
                case KeyEvent.VK_LEFT:
                    playerTwo.setDx(-2.5);
                    break;
                case KeyEvent.VK_RIGHT:
                    playerTwo.setDx(2.5);
                    break;
                case KeyEvent.VK_UP:
                    playerTwo.setDy(-2.5);
                    break;
                case KeyEvent.VK_DOWN:
                    playerTwo.setDy(2.5);
                    break;
            }
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
