import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javafx.scene.text.*;


public class BrickConfigurar {

    private double size;
    private int numBrickCols;
    private int numBrickRows;
    private Group myRoot;
    private ArrayList<ArrayList<Brick>> myBricks;


    private static final double FRACTION_OF_SCREEN_WIDTH_FOR_BRICKS = 1.0/2.0;
   // private static final double PERCENT_OF_BLOCKS_WITH_POWERUPS = 1;


    public ArrayList<ArrayList<Brick>> getBricks() {
        return myBricks;
    }

//
//    public Ball getBall() {
//        return myBall;
//    }
//    public Paddle getPaddle() {
//        return myPaddle;
//    }
//    public Status getStatus() {
//        return myStatus;
//    }
//    public Text getGameOverText() {
//        return gameOverText;
//    }
//    public ArrayList<PowerUp> getPowerUps(){
//        return myPowerUps;
//    }
//    public Scene getScene(){
//        return myScene;
//    }

    /**
     * Sets up a scene with paddle, ball, and blocks configured by a text file
     * @param fileName name of block configuring file
     */
    public BrickConfigurar(String fileName, Group root, double elapsedTime){
        myBricks = new ArrayList<>();
        fillBrickList(readBrickFile(fileName), numBrickCols, numBrickRows);
        myRoot = root;
        //myScene = setGameStage(background, elapsedTime);
        addBricksToRoot();
    }

    /**
     * Reads information from block configuration text file, including size of screen, number of block rows, number of
     * block columns, and configuration of blocks
     * @param fileName name of block configuring file
     * @return configuration of blocks onscreen represented by ints
     */
    private int[][] readBrickFile(String fileName){
        Scanner scannie = new Scanner(GamePlay.class.getClassLoader().getResourceAsStream(fileName));
        size = scannie.nextInt();
        numBrickCols = scannie.nextInt();
        numBrickRows = scannie.nextInt();
        int[][] brickConfigs = new int[numBrickRows][numBrickCols];
        for (int i = 0; i < numBrickRows; i ++){
            for (int j = 0; j < numBrickCols; j ++){
                brickConfigs[i][j] = scannie.nextInt();
            }
        }
        return brickConfigs;
    }

    /**
     * Fills myBricks with Brick objects with health and placement as specified by brickConfigs
     * @param brickConfigs represents configuration of blocks onscreen
     * @param numBrickCols number of columns of bricks
     * @param numBrickRows number of rows of bricks
     */
    private void fillBrickList(int[][] brickConfigs, int numBrickCols, int numBrickRows) {
        for (int i = 0; i < numBrickRows; i++) {
            ArrayList<Brick> brickRow = new ArrayList<>();
            for (int j = 0; j < numBrickCols; j++) {
                if (brickConfigs[i][j] > 0) {
                    Brick myBrick = new Brick(brickConfigs[i][j], brickConfigs[i][j]);
                    myBrick.setSize(calcBrickWidth(numBrickCols), calcBrickHeight(numBrickRows));
                    myBrick.placeItem(j * myBrick.getWidth(),i * myBrick.getHeight());
                    brickRow.add(myBrick);
                }
            }
            myBricks.add(brickRow);
        }
    }

    private double calcBrickHeight(int numBrickRows){
        return size / numBrickRows * FRACTION_OF_SCREEN_WIDTH_FOR_BRICKS;
    }

    private double calcBrickWidth(int numBrickCols){
        return size / numBrickCols;
    }

    private void addBricksToRoot(){
        for (ArrayList<Brick> brickRow : myBricks){
            for (Brick myBrick : brickRow){
                myRoot.getChildren().add(myBrick.getImage());
            }
        }
    }



//    //////////////////////////////////////////////////////////////////////////
//    private Scene setGameStage (Paint background, double elapsedTime) {
//        // create a place to see the shapes
//        Group root = new Group();
//        var scene = new Scene(root, size, size, background);
//
//        gameOverText = new Text();
//        root.getChildren().add(gameOverText);
//
//        myStatus = new Status(size);
//        root.getChildren().add(myStatus.getStatusText());
//
//        myBall = new Ball();
//        myPaddle = new Paddle();
//
//       // placeItemsForStart();
//        root.getChildren().add(myBall.getImage());
//        root.getChildren().add(myPaddle.getImage());
//
//        for (ArrayList<Brick> brickRow : myBricks){
//            for (Brick myBrick : brickRow){
//                root.getChildren().add(myBrick.getImage());
//            }
//        }
//
////        for (PowerUp power : myPowerUps){
////                root.getChildren().add(power.getImage());
////        }
//
//        myPowerUp = new PowerUp();
//        for (ImageView i : myPowerUp.getAllImageViews()) {
//            root.getChildren().add(i);
//        }
//
//        //scene.setOnKeyPressed(key -> handleCheatKeys(key.getCode(), elapsedTime));
//
//        return scene;
//    }





}
