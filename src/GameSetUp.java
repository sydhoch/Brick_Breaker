import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import java.util.ArrayList;
import java.util.Scanner;

public class GameSetUp {

    private int size;
    private Scene myScene;
    private int numBrickCols;
    private int numBrickRows;

    private ArrayList<ArrayList<Brick>> myBricks;
    private Ball myBall;
    private Paddle myPaddle;

    /**
     * Sets up a scene with paddle, ball, and blocks configured by a text file
     * @param fileName name of block configuring file
     * @param background color of screen background
     */
    public GameSetUp(String fileName, Paint background){
        fillBrickList(readBrickFile(fileName), numBrickCols, numBrickRows);
        myScene = setGameStage(size, size, background);
    }

    /**
     * Gets Scene created by GameSetUp
     * @return game Scene
     */
    public Scene getScene(){
        return myScene;
    }

    /**
     * Changes properties of objects on screen to make them seem animated
     * @param elapsedTime how often method is run
     */
    public void step (double elapsedTime) {
        myBall.move(elapsedTime);
        checkBallBrickCollision();
    }

    /**
     * Reads information from block configuration text file, including size of screen, number of block rows, number of
     * block columns, and configuration of blocks
     * @param fileName name of block configuring file
     * @return configuration of blocks onscreen represented by ints
     */
    private int[][] readBrickFile(String fileName){
        Scanner scannie = new Scanner(Main.class.getClassLoader().getResourceAsStream(fileName));
        size = scannie.nextInt();
        numBrickCols = scannie.nextInt();
        numBrickRows = scannie.nextInt();
        int[][] brickConfigs = new int[numBrickCols][numBrickRows];
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
        myBricks = new ArrayList<>();
        for (int i = 0; i < numBrickRows; i++) {
            ArrayList<Brick> brickRow = new ArrayList<>();
            for (int j = 0; j < numBrickCols; j++) {
                if (brickConfigs[i][j] > 0) {
                    Brick myBrick = new Brick(calcBrickWidth(numBrickCols), calcBrickHeight(numBrickRows), brickConfigs[i][j]);
                    myBrick.getView().setX(i * calcBrickWidth(numBrickCols));
                    myBrick.getView().setY(j * calcBrickHeight(numBrickRows));
                    brickRow.add(myBrick);
                }
            }
            myBricks.add(brickRow);
        }
    }

    private int calcBrickHeight(int numBrickRows){
        return size / numBrickRows / 2;
    }

    private int calcBrickWidth(int numBrickCols){
        return size / numBrickCols;
    }

    private Scene setGameStage (int width, int height, Paint background) {
        // create one top level collection to organize the things in the scene
        var root = new Group();
        // create a place to see the shapes
        var scene = new Scene(root, width, height, background);

        myBall = new Ball();
        myBall.getView().setX(width / 2 - myBall.getView().getBoundsInLocal().getWidth() / 2);
        myBall.getView().setY(height / 2 - myBall.getView().getBoundsInLocal().getHeight() / 2);
        root.getChildren().add(myBall.getView());

        myPaddle = new Paddle();
        myPaddle.getView().setX(width / 2 - myPaddle.getView().getBoundsInLocal().getWidth() / 2);
        myPaddle.getView().setY(height - myPaddle.getView().getBoundsInLocal().getHeight());
        root.getChildren().add(myPaddle.getView());

        for (ArrayList<Brick> brickRow : myBricks){
            for (Brick myBrick : brickRow){
                root.getChildren().add(myBrick.getView());
            }
        }

        scene.setOnKeyPressed(key -> myPaddle.handleSideKeyInput(key.getCode()));

        return scene;
    }


    private void checkBallBrickCollision(){
        for (ArrayList<Brick> brickRow : myBricks){
            for (Brick myBrick : brickRow){
                if (myBall.getView().getBoundsInParent().intersects(myBrick.getView().getBoundsInParent())) {
                    myBrick.decreaseHealth();
                }
            }
        }
    }


}
