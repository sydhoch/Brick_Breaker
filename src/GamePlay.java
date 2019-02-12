import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class GamePlay {

    private Ball myBall;
    private Paddle myPaddle;
    private List<List<Brick>> myBricks;
    private Player myPlayer;
    //private HighScore myHighScore;
    private StatusText myStatus;
    private LevelText myLevelText;
    private GameOverText myGameOverText;
    private Scene myScene;
    private boolean gameOver;
    private boolean testKeyHit = false;
    private Group myRoot;
    private GameInteractions interacter;
    private LevelConfiguration levelSetter;
    private List<PowerUp> myPowerUps;
    private static final int SCREEN_SIZE = 500;
    private static final Color BACKGROUND = Color.WHITESMOKE;
    private static final int FRAMES_PER_SECOND = 60;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;

    private Timeline animation;
    private boolean animationRunning;

    private String test;
    private Tests tester;
    private int testNum=0;

    public GamePlay(){
        myRoot = new Group();
        myBall = new Ball();
        myPaddle = new Paddle();
        myPlayer = new Player();
        myBricks = new ArrayList<>();
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
        levelSetter = new LevelConfiguration(myBall, myPaddle, myBricks,myRoot, myPlayer, myPowerUps, myLevelText, SCREEN_SIZE, animation, myScene);
        setUpNewScene();
        resetForNewGame();
        interacter = new GameInteractions(myRoot, myBall, myBricks, myPaddle, myPlayer, myPowerUps, SCREEN_SIZE);
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
    }

    /**
     * Changes properties of objects on screen to make them seem animated
     */
    private void step (){
       // System.out.println(countRemainingBricks()  );

        if (isLevelOver()){
           // System.out.println("new level needed");
            myPlayer.increaseLevel();
            levelSetter.createNewLevel(myPlayer.getLevel());
        }
        else if (isGameOver()) {
            endGame();
        }

        if(testKeyHit){
            updateBallSettings();
            testKeyHit= false;
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
                if (myPlayer.getLives() > 0 && tester==null) {
                    levelSetter.placeItemsForStart();
                }
            }

            for (PowerUp powerUp : myPowerUps) {
                powerUp.move(SECOND_DELAY);
            }
        }

        if(test!=null){
            tester.testBallSpeedup(myBall.getStartingXVelocity(),myBall.getStartingYVelocity(),myBall.getXVelocity(),myBall.getYVelocity());
            animation.stop();
        }

    }

    private void checkForTest(KeyCode code){
        String levelnum= Integer.toString(myPlayer.getLevel());
        if(code.equals(code.COMMA)){
            testNum = 1;
            testKeyHit=true;
        }
        if(code.equals(code.PERIOD)){
            testNum = 2;
            testKeyHit=true;

        }
        if(code.equals(code.SLASH)){
            testNum = 3;
            testKeyHit=true;
        }
        if(testKeyHit){
            test = "test" + Integer.toString(testNum) + "_level" + levelnum + ".txt";
            //System.out.println(test);
        }
    }

    private boolean isLevelOver(){
        return myPlayer.getLives() > 0 && countRemainingBricks() == 0 && myPlayer.getLastLevel() != myPlayer.getLevel();
    }

    private boolean isGameOver(){
        return myPlayer.getLives() == 0 || (countRemainingBricks() == 0 && myPlayer.getLastLevel() == myPlayer.getLevel());
    }

    /**
     * Counts how many bricks have not had their health reduced to 0 (meaning they have not been "destroyed")
     * @return number of bricks with 0 health
     */
    private int countRemainingBricks(){
        int remainingBricks = 0;
        for (List<Brick> brickRow : myBricks) {
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
        myPlayer.getHighScoreObject().updateHighScore(myPlayer.getScore());
        myPlayer.reset();
        //myPlayer.resetHighScore();
    }


    private void updateBallSettings(){
        myBall.placeItem(tester.getPosX(),tester.getPosY());
        myBall.setXVelocity(tester.getXVel());
        myBall.setYVelocity(tester.getYVel());
        //myPaddle.placeItem(myScene.getWidth() / 2 - myPaddle.getWidth() / 2, myScene.getHeight() - myPaddle.getHeight());
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
        checkForTest(code);
        if(testKeyHit){
            System.out.print(test);
            tester = new Tests(test);
            //testKeyHit=false;
        }
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
