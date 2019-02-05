import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

public class GamePlay {

    private Ball myBall;
    private Paddle myPaddle;
    private ArrayList<ArrayList<Brick>> myBricks;
    private Player myPlayer;
    private StatusText myStatus;
    //private Text myGameText;
    private LevelText myLevelText;
    private Scene myScene;
    private boolean gameOver;
    private Group myRoot;
    private GameInteractions interacter;
    private ArrayList<PowerUp> myPowerUps;
    private static final int NUM_STARTING_LIVES = 3;
   // private static final int GAMETEXT_FONT_SIZE = 20;
    private static final int SCREEN_SIZE = 500;
    private static final Color BACKGROUND = Color.WHITESMOKE;
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;

    private Timeline animation;
    private boolean animationRunning;

    private Tests tester; //true if test file is not null

    public GamePlay(double elapsedTime, String test){
        if(test!=null){
            tester = new Tests(test);
        }
        myRoot = new Group();
        BrickConfigurar brickSet = new BrickConfigurar("example.txt", myRoot, elapsedTime, SCREEN_SIZE);
        myBall = new Ball(tester);
        myPaddle = new Paddle();
        myBricks = brickSet.getBricks();
       // myGameText = new Text();
        myPlayer = new Player(NUM_STARTING_LIVES);
        myLevelText = new LevelText(SCREEN_SIZE, myPlayer);
        myScene = new Scene(myRoot, SCREEN_SIZE, SCREEN_SIZE, BACKGROUND);

        myStatus = new StatusText(SCREEN_SIZE, myPlayer);
        gameOver = false;
        myPowerUps = new ArrayList<>();
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        interacter = new GameInteractions(myRoot, myBall, myBricks, myPaddle, myPlayer, myPowerUps);
        setUpNewGame();

    }

    public Scene getScene() {
        return myScene;
    }

//    private void displayGameMessage(String message, Color color, double height){
//        myGameText.setVisible(true);
//        myGameText.setText(message);
//        myGameText.setFont(new Font(GAMETEXT_FONT_SIZE));
//        myGameText.setFill(color);
//        myGameText.setX(myScene.getWidth() / 2 - (myGameText.getBoundsInLocal().getWidth() / 2));
//        myGameText.setY(height);
//    }

    private void setUpNewGame () {
        if(tester!=null){
            testPlaceItemsForStart(tester);
        }
        else {
            placeItemsForStart();
        }
        //displayGameMessage("Press Space To Start", Color.BLACK, myScene.getHeight() - myScene.getHeight() / 4);
        myLevelText.updateText();
        myStatus.updateText();
      // myRoot.getChildren().add(myGameText);
        myRoot.getChildren().add(myLevelText.getText());
        myRoot.getChildren().add(myStatus.getText());
        myRoot.getChildren().add(myBall);
        myRoot.getChildren().add(myPaddle);

        myScene.setOnKeyPressed(key -> handleAllKeys(key.getCode(), SECOND_DELAY));
    }

    /**
     * Changes properties of objects on screen to make them seem animated
     * @param elapsedTime how often method is run
     */
    public void step (double elapsedTime) {
        myStatus.updateText();
        if (!gameOver) {
            myBall.move(elapsedTime);
            myBall.bounce(myScene.getWidth(), myScene.getHeight(), tester, animation);
            interacter.checkBallHitsPaddle();
            interacter.checkBallBricksCollision(tester, animation);
            interacter.checkPowerUpPaddleCollision();
            if (myBall.ballFell(myScene.getHeight())) {
                //myBall.setVisible(true);
                myPlayer.loseLife(tester,animation);
                if (myPlayer.getLives() > 0) {
                    placeItemsForStart();
                }
            }
            for (PowerUp powerUp : myPowerUps) {
                powerUp.move(elapsedTime);
            }
            if (myPlayer.getLives() == 0 || countRemainingBricks() == 0) {
                endGame();
            }
        }
    }

    /**
     * Counts how many bricks have not had their health reduced to 0 (meaning they have not been "destroyed")
     * @return number of bricks with 0 health
     */
    private int countRemainingBricks(){
        int remainingBricks = 0;
        for (ArrayList<Brick> brickRow : myBricks) {
            for (Brick brick : brickRow) {
                if (brick.getHealth() != 0) {
                    remainingBricks += 1;
                }
            }
        }
        return remainingBricks;
    }


    /**
     * Determines if the winner won or lost in order to call the appropriate message
     */
    private void endGame(){
        gameOver = true;
//        if(countRemainingBricks() != 0){
//           // displayGameMessage("You Lost :(", Color.RED, myScene.getHeight() / 2);
//        }
//        else{
//            displayGameMessage("You Won!", Color.GREEN, myScene.getHeight() / 2);
//        }
        myLevelText.updateText();
    }


    private void placeItemsForStart(){
        myBall.placeItem(
                myScene.getWidth() / 2 - myBall.getWidth() / 2, myScene.getHeight() / 2 - myBall.getHeight() / 2);
        myPaddle.placeItem(myScene.getWidth() / 2 - myPaddle.getWidth() / 2, myScene.getHeight() - myPaddle.getHeight());
    }

    private void testPlaceItemsForStart(Tests tester){
        myBall.placeItem(tester.getPosX(),tester.getPosY());
        myPaddle.placeItem(myScene.getWidth() / 2 - myPaddle.getWidth() / 2, myScene.getHeight() - myPaddle.getHeight());
    }


    private void handleCheatKeys(KeyCode code, double elapsedTime){
            if (code.getChar().equals("L")) {
                myPlayer.gainLife();
            }
            if (code.getChar().equals("R")) {
                placeItemsForStart();
         }
    }

    private void handleAllKeys(KeyCode code, double elapsedTime){
        if (!gameOver) {
            handleRunKeys(code, animation, elapsedTime);
            handleCheatKeys(code, elapsedTime);
        }
    }

    private void handleRunKeys(KeyCode code, Timeline animation, double elapsedTime){
        if (code.equals(code.SPACE)){
            playOrPause(animation);
            if (!gameOver) {
               // myGameText.setVisible(false);
                myLevelText.disappear();
            }
        }
        if (code.isArrowKey()) {
            myPaddle.handleSideKeyInput(code, myScene.getWidth(), elapsedTime);
        }
    }

    private void playOrPause(Timeline animation){
        if (animation.getStatus().equals(Animation.Status.RUNNING)){
            animation.pause();
        }
        else{
            animation.play();
        }
    }


}
