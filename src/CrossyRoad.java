import java.util.ArrayList;

public class CrossyRoad {
    private int gameState;
    private Player playerOne;
    private Player playerTwo;
    private ArrayList<Obstacle> obstacles;
    private CrossyRoadViewer window;

    public CrossyRoad() {
        window = new CrossyRoadViewer(this);
        gameState = 0;
        playerOne = new Player(FINISH THIS);
        playerTwo = new Player(FINISH THIS);
        // Figure out way to randomize and create all the obstacles
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

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public CrossyRoadViewer getWindow() {
        return window;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public void runGame() {

    }

    public static void main(String[] args) {
        CrossyRoad c1 = new CrossyRoad();
        c1.runGame();
    }
}
