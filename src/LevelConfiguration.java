import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.*;


public class LevelConfiguration {

    private Ball myBall;
    private Paddle myPaddle;
    private ArrayList<ArrayList<Brick>> myBricks;
    private ArrayList<PowerUp> myPowerUps;
    private LevelText myLevelText;
    private int SCREEN_SIZE;
    private Group myRoot;
    private Scene myScene;
    private Timeline myAnimation;
    private int numBrickCols;
    private int numBrickRows;
    private static final String brickFileNameStart = "level";
    private static final String brickFileNameEnd = ".txt";
    private static final ArrayList<Color> possibleLevelColors = new ArrayList<>(Arrays.asList(
        Color.WHITE, Color.LAVENDER, Color.LIGHTPINK, Color.LIGHTBLUE));
    private static final double FRACTION_OF_SCREEN_WIDTH_FOR_BRICKS = 1.0/2.0;

    public LevelConfiguration(Ball ball, Paddle paddle, ArrayList<ArrayList<Brick>> bricks, Group root,
                              ArrayList<PowerUp> powerUps, LevelText levelText, int SIZE, Timeline animation, Scene scene){
        myBall = ball;
        myPaddle = paddle;
        myBricks = bricks;
        myPowerUps = powerUps;
        SCREEN_SIZE = SIZE;
        myLevelText = levelText;
        myRoot = root;
        myAnimation = animation;
        myScene = scene;
    }

    public void createNewLevel(int myLevelNum){
        resetScreenForNewLevel();
        fillBrickList(readBrickFile(brickFileNameStart + myLevelNum + brickFileNameEnd));
        myScene.setFill(pickRandomBackground());
        myLevelText.updateText();
        myAnimation.pause();
    }

    private Color pickRandomBackground(){
        Collections.shuffle(possibleLevelColors);
        return possibleLevelColors.get(0);
    }

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
        for (ArrayList<Brick> brickRow : myBricks) {
            for (Brick brick : brickRow) {
                brick.destroyBrick();
            }
        }
    }

    private void destroyAllPowerUps(){
        for (PowerUp powerUp : myPowerUps){
            powerUp.setCanSee(false);
        }
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
            ArrayList<Brick> brickRow = new ArrayList<>();
            for (int j = 0; j < numBrickCols; j++) {
                Brick brick = new Brick(0);
                if (brickConfigs[i][j].matches("\\d+")){
                    int health = Integer.parseInt(brickConfigs[i][j]);
                    if (health == 1) {
                        brick = new Brick(health);
                    }
                    if (health > 1){
//                        System.out.println("in multibrick making");
//                        System.out.println(health);
                        brick = new MultiHitBrick(health);
                    }
                }
                if (brickConfigs[i][j].equals("*")) {
                    brick = new PowerUpBrick();
                }
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

}
