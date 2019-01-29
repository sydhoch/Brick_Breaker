import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.text.*;



public class GameSetUp {

    private int size;
    private Scene myScene;
    private int numBrickCols;
    private int numBrickRows;
    private Text gameOverText;
    private Status myStatus;
//    private Player myPlayer;
    private Paddle myPaddle;
    private Ball myBall;
    private ArrayList<ArrayList<Brick>> myBricks;
   // private int levelNum = 1;

    public ArrayList<ArrayList<Brick>> getBricks() {
        return myBricks;
    }


    public Ball getBall() {
        return myBall;
    }


    public Paddle getPaddle() {
        return myPaddle;
    }


//    public Player getPlayer() {
//        return myPlayer;
//    }


    public Status getStatus() {
        return myStatus;
    }

    public Text getGameOverText() {
        return gameOverText;
    }


    /**
     * Sets up a scene with paddle, ball, and blocks configured by a text file
     * @param fileName name of block configuring file
     * @param background color of screen background
     */
    public GameSetUp(String fileName, Paint background, double elapsedTime){
        fillBrickList(readBrickFile(fileName), numBrickCols, numBrickRows);

//        PlayerSetUp(3);
        myScene = setGameStage(background, elapsedTime);
//        countTotalBricks();
    }

    /**
     * Gets Scene created by GameSetUp
     * @return game Scene
     */
    public Scene getScene(){
        return myScene;
    }



//    /**
//     * Changes properties of objects on screen to make them seem animated
//     * @param elapsedTime how often method is run
//     */
//    public void step (double elapsedTime) {
//        myStatus.updateStatusText(myPlayer.getLives(), levelNum, myPlayer.getScore());
//        myBall.move(elapsedTime);
//        myBall.bounce(myScene.getWidth(), myScene.getHeight());
//        checkBallHitsPaddle();
//        checkBallBrickCollision();
//        if (myBall.ballFell(myScene.getHeight()) && myBall.getImage().isVisible()) {
//            myPlayer.loseLife();
//            if (myPlayer.getLives() > 0) {
//                placeItemsForStart();
//            }
//        }
//        if (myPlayer.getLives() == 0 || countTotalBricks() == countDestroyedBricks()){
//            endGame();
//        }
//    }

//    private int countDestroyedBricks(){
//        int destroyedBricks = 0;
//        for (ArrayList<Brick> brickRow : myBricks) {
//            for (Brick myBrick : brickRow) {
//                if (myBrick.getHealth() == 0) {
//                    destroyedBricks += 1;
//                }
//            }
//        }
//        return destroyedBricks;
//    }
//
//
//    private int countTotalBricks(){
//        int totalBricks = 0;
//        for (ArrayList<Brick> brickRow : myBricks){
//            totalBricks += brickRow.size();
//        }
//        return totalBricks;
//    }
//

//    private void endGame(){
//        if(countDestroyedBricks() != countTotalBricks()){
//            displayGameOverMessage("You Lost :(", Color.RED);
//        }
//        else{
//            displayGameOverMessage("You Won!", Color.GREEN);
//            myBall.getImage().setVisible(false);
//        }
//    }

//    private void displayGameOverMessage(String message, Color color){
//        gameOverText.setText(message);
//        gameOverText.setFont(new Font(20));
//        gameOverText.setFill(color);
//        gameOverText.setX(size / 2 - (gameOverText.getBoundsInLocal().getWidth() / 2));
//        gameOverText.setY(size / 2);
//    }

//    private void PlayerSetUp(int lives){
//        myPlayer = new Player(lives);
//    }

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
     * @param numBrickCols number of columns of countTotalBricks
     * @param numBrickRows number of rows of countTotalBricks
     */
    private void fillBrickList(int[][] brickConfigs, int numBrickCols, int numBrickRows) {
        myBricks = new ArrayList<>();
        for (int i = 0; i < numBrickRows; i++) {
            ArrayList<Brick> brickRow = new ArrayList<>();
            for (int j = 0; j < numBrickCols; j++) {
                if (brickConfigs[i][j] > 0) {
                    Brick myBrick = new Brick(brickConfigs[i][j]);
                    myBrick.setSize(calcBrickWidth(numBrickCols), calcBrickHeight(numBrickRows));
                    myBrick.placeItem(j * calcBrickWidth(numBrickCols),i * calcBrickHeight(numBrickRows));
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



    //////////////////////////////////////////////////////////////////////////
    private Scene setGameStage (Paint background, double elapsedTime) {
        // create a place to see the shapes
        Group root = new Group();
        var scene = new Scene(root, size, size, background);

        gameOverText = new Text();
        root.getChildren().add(gameOverText);

        myStatus = new Status(size);
        root.getChildren().add(myStatus.getStatusText());

        myBall = new Ball();
        myPaddle = new Paddle();

       // placeItemsForStart();
        root.getChildren().add(myBall.getImage());
        root.getChildren().add(myPaddle.getImage());

        for (ArrayList<Brick> brickRow : myBricks){
            for (Brick myBrick : brickRow){
                root.getChildren().add(myBrick.getImage());
            }
        }

        //scene.setOnKeyPressed(key -> handleCheatKeys(key.getCode(), elapsedTime));

        return scene;
    }


    /////////////////////////////////////////////////////////////////////////////


//    private void checkBallBrickCollision(){
//        for (ArrayList<Brick> brickRow : myBricks){
//            for (Brick myBrick : brickRow){
//                if (myBrick.getHealth() > 0 && myBall.getImage().getBoundsInParent().intersects(myBrick.getImage().getBoundsInParent())) {
//                    myBrick.decreaseHealth();
//                    myBall.BounceOff();
//                    myPlayer.increaseScore(1);
//                }
//            }
//        }
//    }
//
//    private void checkBallHitsPaddle(){
//        if(myBall.getImage().getBoundsInLocal().intersects(myPaddle.getImage().getBoundsInLocal())){
//            myBall.BounceOffPad();
//        }
//    }

//    private void placeItemsForStart(){
//        myBall.getImage().setVisible(true);
//        myBall.placeItem(size / 2 - myBall.getImage().getBoundsInLocal().getWidth() / 2,
//                size / 2 - myBall.getImage().getBoundsInLocal().getHeight() / 2);
//        myPaddle.placeItem(size / 2 - myPaddle.getImage().getBoundsInLocal().getWidth() / 2,
//                size - myPaddle.getImage().getBoundsInLocal().getHeight());
//    }
//
//    private void handleCheatKeys(KeyCode code, double elapsedTime){
//        if (code.getChar().equals("L")){
//           myPlayer.gainLife();
//        }
//        if (code.getChar().equals("R")){
//            placeItemsForStart();
//        }
//        if (code.isArrowKey()){
//            myPaddle.handleSideKeyInput(code, myScene.getWidth(), elapsedTime);
//        }
//
//    }



}
