// Created by Kieran Pichai

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CrossyRoad implements ActionListener, KeyListener {

    // Instance variables used in code for various game states or actions
    private int gameState;
    private Player playerOne;
    private Player playerTwo;
    private int timeIntro;
    private int timeEggOne;
    private int timeEggTwo;
    private boolean playerOneInUse;
    private boolean playerTwoInUse;
    private ArrayList<Obstacle> obstaclesToRight;
    private ArrayList<Obstacle> obstaclesToLeft;
    private CrossyRoadViewer window;

    // Constructor for class which starts and initializes all the initial game objects
    public CrossyRoad() {

        // Creatse a window (CrossyRoadViewer) object which displays the informatino
        window = new CrossyRoadViewer(this);
        gameState = 0;
        timeIntro = 0;
        timeEggOne = 0;
        timeEggTwo = 0;
        playerOneInUse = false;
        playerTwoInUse = false;

        // Initializes both player objects and gives them initial positions
        playerOne = new Player(300, 900, 0, 0, true);
        playerTwo = new Player(1427, 900, 0, 0, false);

        // Randomizes and creates all the initial cars
        obstaclesToRight = new ArrayList<Obstacle>();
        obstaclesToLeft = new ArrayList<Obstacle>();

        // Adds first standard car, each one after is randomizes
        obstaclesToRight.add(new Obstacle(0, 703, 5, 0, 0, false));

        // For loop adds and randomizes every single car moving towards the right side of the screen
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

        // Sets first standard car
        obstaclesToLeft.add(new Obstacle(1763, 797, -5, 0, 0, true));

        // For loop adds and randomizes every single car moving towards the left side of the screen
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

        // Implements timer and keylistener to this class which allows the game to update every 15ms as well as have user input
        window.addKeyListener(this);
        Toolkit.getDefaultToolkit().sync();
        Timer clock = new Timer(15, this);
        clock.start();
    }

    // This method generates x positions of cars randomly based off the previous ones x position
    public double generateNewCarMovingRight(double x) {
        return x - ((int)(Math.random()*425) + 125);
    }

    // Same method as above except cars moving the left instead of right
    public double generateNewCarMovingLeft(double x) {
        return x + ((int)(Math.random()*425) + 125);
    }

    // This class is called each time actionPerformed is called and checks if the game is over
    public void checkWinner() {

        // Checks if game is already over, then there is nothing to change
        if (gameState == 1 || gameState == 2) {
            return;
        }

        // Checks if either player is at the top of the screen (finish line)
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

        // Checks if any player has collided with an obstacle
        for (Obstacle o: obstaclesToRight) {
            if (GameObject.checkCollision(o, playerOne) || GameObject.checkCollision(o, playerTwo)) {
                break;
            }
        }

        // Same check as above except for left moving obstacles
        for (Obstacle o: obstaclesToLeft) {
            if (GameObject.checkCollision(o, playerOne) || GameObject.checkCollision(o, playerTwo)) {
                break;
            }
        }

        // Is a player has no health left then the other wins
        if (playerOne.getHealth() <= 0) {

            // Sets winning game state
            gameState = 2;
            playerOne.setIsShown(false);
            pauseGameMovement();
        } else if (playerTwo.getHealth() <= 0) {

            // Sets winning game state
            gameState = 1;
            playerTwo.setIsShown(false);
            pauseGameMovement();
        }
    }

    // Checks if any of the obstacles have left the screen so they can randomize a new one
    public void checkObstacles() {

        // Starts by checking each obstacle moving to right
        for (int i = 0; i < obstaclesToRight.size(); i++) {
            Obstacle o = obstaclesToRight.get(i);

            // If the obstacle is off the screen, it removes it then generates a new one
            if (o.getX() > 1727) {
                double y = o.getY();
                obstaclesToRight.remove(i);
                int j;

                // Finds the last car in the array that has the same y value as the current one (so no double cars are generated)
                for (j = obstaclesToRight.size() - 1; j >= 0; j--) {
                    if (obstaclesToRight.get(j).getY() == y) {
                        break;
                    }
                }
                i--;

                // Generates the new car
                obstaclesToRight.add(new Obstacle(generateNewCarMovingRight(obstaclesToRight.get(j).getX()), y, 5, 0, 0, false));
            }
        }

        // This for loops follows the same logic as the one above except for cars moving to the left
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

    // This method when called resets the game so that it can be played multiple times in a row
    public void resetGame() {

        // Resets all instance variables
        gameState = 0;
        timeIntro = 0;
        timeEggOne = 0;
        timeEggTwo = 0;
        playerOneInUse = false;
        playerTwoInUse = false;

        // Resets player objects
        playerOne = new Player(300, 900, 0, 0, true);
        playerTwo = new Player(1427, 900, 0, 0, false);

        // And regenerates cars for left and right
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
    }

    // This method pauses movement of cars and players alike
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

    // Returns the current gamestate
    public int getGameState() {
        return gameState;
    }

    // Returns player one
    public Player getPlayerOne() {
        return playerOne;
    }

    // Same as above except player two
    public Player getPlayerTwo() {
        return playerTwo;
    }

    // Returns the various obstacles
    public ArrayList<Obstacle> getObstaclesToRight() {
        return obstaclesToRight;
    }

    // Same as above except for left
    public ArrayList<Obstacle> getObstaclesToLeft() {
        return obstaclesToLeft;
    }

    // Main action performed that is called upon any event or time tick
    public void actionPerformed(ActionEvent e) {

        // Increments intro timer so that the intro screen stays for specified amount of time
        timeIntro += 15;
        if (timeIntro > 5400) {
            gameState = -1;
        }

        // Checks if player one is using their egg blaster
        if (playerOneInUse) {

            // If so increments their timer so that the egg detonates after a specified amount of time
            timeEggOne += 15;
            if (timeEggOne > 1000) {
                timeEggOne = 0;
                playerOneInUse = false;
                playerOne.getEggBlaster().hit();
            }
        }

        // Same as the if statement block above except for player two
        if (playerTwoInUse) {
            timeEggTwo += 15;
            if (timeEggTwo > 1000) {
                timeEggTwo = 0;
                playerTwoInUse = false;
                playerTwo.getEggBlaster().hit();
            }
        }

        // Moves all the obstacles
        for (Obstacle o: obstaclesToRight) {
            o.move();
        }
        for (Obstacle o: obstaclesToLeft) {
            o.move();
        }
        checkObstacles();

        // Moves both players then checks if there is a winnner
        playerOne.move();
        playerTwo.move();
        checkWinner();

        // Repaints the window every loop which allows for smooth movement of cars and player alike
        window.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // This method stops the movement of a player when the key is released
    @Override
    public void keyReleased(KeyEvent e) {

        // Checks if the game state is one that allows for movement of players
        if (gameState == -1 || gameState == 3 || gameState == 4) {
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

    // This method updates the velocity of a player when certain keys or pressed
    public void keyPressed(KeyEvent e) {

        // Checks if its a game state that allows player movement
        if (gameState == -1 || gameState == 3 || gameState == 4) {

            // Cases for movement keys
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    playerOne.setDy(-3);
                    break;
                case KeyEvent.VK_A:
                    playerOne.setDx(-3);
                    break;
                case KeyEvent.VK_S:
                    playerOne.setDy(3);
                    break;
                case KeyEvent.VK_D:
                    playerOne.setDx(3);
                    break;
                case KeyEvent.VK_LEFT:
                    playerTwo.setDx(-3);
                    break;
                case KeyEvent.VK_RIGHT:
                    playerTwo.setDx(3);
                    break;
                case KeyEvent.VK_UP:
                    playerTwo.setDy(-3);
                    break;
                case KeyEvent.VK_DOWN:
                    playerTwo.setDy(3);
                    break;

                // Cases for egg blaster keys
                case KeyEvent.VK_Q:
                    if (!playerOneInUse && playerOne.getEggBlaster().getEggsLeft() > 0) {
                        playerOne.useEggBlaster(this);
                        playerOneInUse = true;
                    }
                    break;
                case KeyEvent.VK_SLASH:
                    if (!playerTwoInUse && playerTwo.getEggBlaster().getEggsLeft() > 0) {
                        playerTwo.useEggBlaster(this);
                        playerTwoInUse = true;
                    }
                    break;
            }
        }

        // Case for restart game functionality (can only occur if the game is already over)
        if (gameState == 1 || gameState == 2) {
            if (e.getKeyCode() == KeyEvent.VK_G) {
                resetGame();
            }
        }
    }

    // Main method creates a game object which automatically starts the game
    public static void main(String[] args) {
        CrossyRoad c1 = new CrossyRoad();
    }
}
