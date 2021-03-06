import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import java.util.*;
/**
 * @author leahschwartz
 *
 * Handles creation of each new level, clearing old bricks and powerups, reading and configuring bricks from a
 * text file, placing objects, and setting background colors
 * Dependent on Ball, BallTransporting Brick, MultiHit Brick, PermanentBrick, PowerUp Brick, Brick Item, LevelText,
 * Paddle, PowerUp, Player, and GameText classes
 *
 * Note: If trying to add a new level, create a new configuration file with the format "level" + number + ".txt"
 * and change MAX_LEVEL in player class to reflect new maximum level
 *
 */

public class LevelConfiguration {

    private Ball myBall;
    private Paddle myPaddle;
    private List<List<Brick>> myBricks;
    private List<PowerUp> myPowerUps;
    private LevelText myLevelText;
    private int SCREEN_SIZE;
    private Group myRoot;
    private Player myPlayer;
    private Scene myScene;
    private Timeline myAnimation;
    private int numBrickCols;
    private int numBrickRows;
    private static final String brickFileNameStart = "level";
    private static final String brickFileNameEnd = ".txt";
    private static final List<Color> possibleLevelColors = new ArrayList<>(Arrays.asList(
        Color.WHITE, Color.LAVENDER, Color.LIGHTPINK, Color.LIGHTBLUE));
    private static final double FRACTION_OF_SCREEN_WIDTH_FOR_BRICKS = 1.0/2.0;
    private static final double BALL_SPEED_INCREASE_PERCENT = 1.0/5.0;
    private static final String POWERUP_BRICK_SYMBOL = "*";
    private static final String PERMANENT_BRICK_SYMBOL = "#";
    private static final String BALL_TRANSPORT_BRICK_SYMBOL = "-";

    /**
     * Creates LevelConfiguration object which need items from game in order to reset and properly place them
     * for a new level
     *
     * @param ball player's ball
     * @param paddle in-game paddle for hitting ball
     * @param bricks list of bricks
     * @param root holds all onscreen images
     * @param player object representing and maintaining player-related information
     * @param powerUps list of powerups
     * @param levelText in-game text appearing at start of each level informing player of level number and to press space
     * @param SIZE size of screen
     * @param animation game animation
     * @param scene gameplay scene
     */
    public LevelConfiguration(Ball ball, Paddle paddle, List<List<Brick>> bricks, Group root, Player player,
                              List<PowerUp> powerUps, LevelText levelText, int SIZE, Timeline animation, Scene scene){
        myBall = ball;
        myPaddle = paddle;
        myBricks = bricks;
        myPowerUps = powerUps;
        SCREEN_SIZE = SIZE;
        myLevelText = levelText;
        myRoot = root;
        myAnimation = animation;
        myScene = scene;
        myPlayer = player;
    }

    /**
     * Resets and places all items to prepare for a new level, as well as sets up text and pauses animation til
     * space bar is pressed to begin
     *
     * @param levelNum number level to create, decides which brick configuration file is read
     */
    public void createNewLevel(int levelNum){
        resetScreenForNewLevel();
        increaseBallSpeed(levelNum);
        fillBrickList(readBrickFile(brickFileNameStart + levelNum + brickFileNameEnd));
        myScene.setFill(pickRandomBackground());
        myLevelText.updateText();
        myAnimation.pause();
    }

    private Color pickRandomBackground(){
        Collections.shuffle(possibleLevelColors);
        return possibleLevelColors.get(0);
    }

    /**
     * Puts ball and paddle in correct starting places on screen
     */
    public void placeItemsForStart(){
        myBall.placeItem(
                SCREEN_SIZE / 2.0 - myBall.getWidth() / 2, SCREEN_SIZE / 2.0);
        myPaddle.placeItem(SCREEN_SIZE / 2.0 - myPaddle.getWidth() / 2, SCREEN_SIZE - myPaddle.getHeight());
    }

    private void resetScreenForNewLevel(){
        destroyAllBricks();
        destroyAllPowerUps();
        placeItemsForStart();
    }

    private void destroyAllBricks() {
        for (List<Brick> brickRow : myBricks) {
            for (Brick brick : brickRow) {
                brick.destroy();
            }
        }
        myBricks.clear();
    }

    private void destroyAllPowerUps(){
        for (PowerUp powerUp : myPowerUps){
            powerUp.destroy(myPaddle, myBall, myPlayer);
        }
        myPowerUps.clear();
    }

    private void increaseBallSpeed(int levelNum){
        levelNum -= 1; // accounts for levels starting at 1 not 0 so that level 1 gets no added speed
        myBall.setXVelocity(Ball.getStartingXVelocity() + (Ball.getStartingXVelocity() * levelNum * BALL_SPEED_INCREASE_PERCENT));
        myBall.setYVelocity(Ball.getStartingYVelocity() + (Ball.getStartingYVelocity() * levelNum * BALL_SPEED_INCREASE_PERCENT));
    }

    /**
     * Reads information from block configuration text file, including mySize of screen, number of block rows, number of
     * block columns, and configuration of blocks
     * @param fileName name of block configuring file
     * @return configuration of blocks onscreen represented by ints
     */
    private String[][] readBrickFile(String fileName){
        Scanner scannie = new Scanner(GamePlay.class.getClassLoader().getResourceAsStream(fileName));
        numBrickCols = scannie.nextInt();
        numBrickRows = scannie.nextInt();
        String[][] brickConfigs = new String[numBrickRows][numBrickCols];
        for (int i = 0; i < numBrickRows; i ++){
            for (int j = 0; j < numBrickCols; j ++){
                brickConfigs[i][j] = scannie.next();
            }
        }
        return brickConfigs;
    }

    /**
     * Fills myBricks with Brick objects with health and placement as specified by brickConfigs
     * @param brickConfigs represents configuration of blocks onscreen
     */
    private void fillBrickList(String[][] brickConfigs) {
        for (int i = 0; i < numBrickRows; i++) {
            List<Brick> brickRow = new ArrayList<>();
            for (int j = 0; j < numBrickCols; j++) {
                Brick brick = makeBrick(brickConfigs[i][j]);
                brick.setSize(calcBrickWidth(), calcBrickHeight());
                brick.placeItem(j * brick.getWidth(), i * brick.getHeight());
                brickRow.add(brick);
                myRoot.getChildren().add(brick.getImage());
            }
            myBricks.add(brickRow);
        }
    }

    private double calcBrickHeight(){
        return SCREEN_SIZE / numBrickRows * FRACTION_OF_SCREEN_WIDTH_FOR_BRICKS;
    }

    private double calcBrickWidth(){
        return SCREEN_SIZE / numBrickCols;
    }


    private Brick makeBrick(String brickSymbol){
        if (brickSymbol.matches("\\d+")){ // regex for integer
            int health = Integer.parseInt(brickSymbol);
        if (health >= 1){
            return new MultiHitBrick(health);
        }
        }
        if (brickSymbol.equals(POWERUP_BRICK_SYMBOL)) {
        return new PowerUpBrick();
        }
        if (brickSymbol.equals(BALL_TRANSPORT_BRICK_SYMBOL)){
            return new BallTransportingBrick();
        }
        if (brickSymbol.equals(PERMANENT_BRICK_SYMBOL)){
            return new PermanentBrick();
        }
        return new MultiHitBrick(0);
    }
}
