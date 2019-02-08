import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class GamePlay {

    private Ball myBall;
    private Paddle myPaddle;
    private ArrayList<ArrayList<Brick>> myBricks;
    private Player myPlayer;
    private StatusText myStatus;
    private LevelText myLevelText;
    private GameOverText myGameOverText;
    private Scene myScene;
    private boolean gameOver;
    private Group myRoot;
    private GameInteractions interacter;
    private LevelConfiguration levelSetter;
    private ArrayList<PowerUp> myPowerUps;
   // private static final int NUM_STARTING_LIVES = 1;
    private static final int SCREEN_SIZE = 500;
    private static final Color BACKGROUND = Color.WHITESMOKE;
    private static final int FRAMES_PER_SECOND = 60;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
   // private static final int LAST_LEVEL_NUM = 2;

    private Timeline animation;
    private boolean animationRunning;

    private Tests tester; //true if test file is not null

    public GamePlay(String test){
        if(test!=null){
            tester = new Tests(test);
        }
        myRoot = new Group();
        myBall = new Ball(tester);
        myPaddle = new Paddle();
        myBricks = new ArrayList<>();
        myPlayer = new Player();
        myLevelText = new LevelText(SCREEN_SIZE, myPlayer);
        myGameOverText = new GameOverText(SCREEN_SIZE, myPlayer);
        myScene = new Scene(myRoot, SCREEN_SIZE, SCREEN_SIZE, BACKGROUND);
        myStatus = new StatusText(SCREEN_SIZE, myPlayer);
        gameOver = false;
        myPowerUps = new ArrayList<>();
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        levelSetter = new LevelConfiguration(myBall, myPaddle, myBricks,myRoot, myPowerUps, myLevelText, SCREEN_SIZE, animation, myScene);
        //interacter = new GameInteractions(myRoot, myBall, myBricks, myPaddle, myPlayer, myPowerUps);
        setUpNewScene();
        resetForNewGame();
        interacter = new GameInteractions(myRoot, myBall, myBricks, myPaddle, myPlayer, myPowerUps);
    }

    public Scene getScene() {
        return myScene;
    }

    private void setUpNewScene () {
        myRoot.getChildren().add(myLevelText.getText());
        myRoot.getChildren().add(myGameOverText.getText());
        myRoot.getChildren().add(myStatus.getText());
        myRoot.getChildren().add(myBall.getImage());
        myRoot.getChildren().add(myPaddle.getImage());

        myScene.setOnKeyPressed(key -> handleAllKeys(key.getCode()));
    }

    private void resetForNewGame(){
        myPlayer.reset();
        levelSetter.createNewLevel(myPlayer.getLevel());
        myStatus.updateText();
        myGameOverText.disappear();
        if(tester!=null){
            testPlaceItemsForStart(tester);
        }
        // read high score file

    }



    /**
     * Changes properties of objects on screen to make them seem animated
     */
    private void step (){
       // System.out.println(countRemainingBricks()  );

        if (myPlayer.getLives() > 0 && countRemainingBricks() == 0 && myPlayer.getLastLevel() != myPlayer.getLevel()){
           // System.out.println("new level needed");
            myPlayer.increaseLevel();
            levelSetter.createNewLevel(myPlayer.getLevel());
        }
        else if (myPlayer.getLives() == 0 || (countRemainingBricks() == 0 && myPlayer.getLastLevel() == myPlayer.getLevel())) {
            endGame();
        }

        myStatus.updateText();
        if (!gameOver) {
            myBall.move(SECOND_DELAY);
            myBall.bounce(myScene.getWidth(), myScene.getHeight(), tester, animation);
            interacter.checkBallHitsPaddle();
            interacter.checkBallBricksCollision(tester, animation);
            interacter.checkPowerUpPaddleCollision();
            if (myBall.ballFell(myScene.getHeight())) {
                myPlayer.loseLife(tester,animation);
                if (myPlayer.getLives() > 0) {
                    levelSetter.placeItemsForStart();
                }
            }

            for (PowerUp powerUp : myPowerUps) {
                powerUp.move(SECOND_DELAY);
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


    private void endGame(){
        gameOver = true;
        myGameOverText.updateText();
        // for saving high score
    }


    private void testPlaceItemsForStart(Tests tester){
        myBall.placeItem(tester.getPosX(),tester.getPosY());
        myPaddle.placeItem(myScene.getWidth() / 2 - myPaddle.getWidth() / 2, myScene.getHeight() - myPaddle.getHeight());
    }


    private void handleCheatKeys(KeyCode code){
            if (code.getChar().equals("L")) {
                myPlayer.gainLife();
            }
            if (code.getChar().equals("R")) {
                levelSetter.placeItemsForStart();
         }
            if (code.isDigitKey() && Integer.parseInt(code.getChar()) <= myPlayer.getLastLevel()){
                myPlayer.setLevel(Integer.parseInt(code.getChar()));
                levelSetter.createNewLevel(myPlayer.getLevel());
            }
    }

    private void handleAllKeys(KeyCode code){
        handleRestartKey(code);
        if (!gameOver) {
            handleRunKeys(code, animation);
            handleCheatKeys(code);
        }
    }

    private void handleRunKeys(KeyCode code, Timeline animation){
        if (code.equals(code.SPACE)){
            playOrPause(animation);
            if (!gameOver) {
                myLevelText.disappear();
            }
        }
        if (code.isArrowKey()) {
            myPaddle.handleSideKeyInput(code, myScene.getWidth(), SECOND_DELAY);
        }
    }

    private void handleRestartKey(KeyCode code){
        if (code.getChar().equals("N") && gameOver){
            myPlayer.reset();
            gameOver = false;
            resetForNewGame();
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
