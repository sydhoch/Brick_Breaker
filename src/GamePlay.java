import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;


public class GamePlay {

    private Ball myBall;
    private Paddle myPaddle;
    private ArrayList<ArrayList<Brick>> myBricks;
    private Player myPlayer;
    private Status myStatus;
    private Text myGameText;
    private Scene myScene;
    private int levelNum;
    private int brickHitValue;
    private boolean gameOver;
    private ArrayList<PowerUp> myPowerUps;

    private static final int NUM_STARTING_LIVES = 3;
    private static final int NUM_STARTING_LEVEL = 0;
    private static final int GAMETEXT_FONT_SIZE = 20;


    public GamePlay(Ball ball, Paddle paddle, ArrayList<ArrayList<Brick>> bricks, Status status, Text gameText, Scene scene, ArrayList<PowerUp> powerUps){
        myBall = ball;
        myPaddle = paddle;
        myBricks = bricks;
        myPlayer = new Player(NUM_STARTING_LIVES);
        myStatus = status;
        myGameText = gameText;
        myScene = scene;
        levelNum = NUM_STARTING_LEVEL;
        placeItemsForStart();
        brickHitValue = 1;
        gameOver = false;
        myPowerUps = powerUps;
    }


    /**
     * Changes properties of objects on screen to make them seem animated
     * @param elapsedTime how often method is run
     */
    public void step (double elapsedTime) {
        myStatus.updateStatusText(myPlayer.getLives(), levelNum, myPlayer.getScore());
        myBall.move(elapsedTime);
        myBall.bounce(myScene.getWidth(), myScene.getHeight());
        checkBallHitsPaddle();
        checkBallBrickCollision();
        if (myBall.ballFell(myScene.getHeight()) && myBall.isVisible()) {
            myBall.setVisible(true);
            myPlayer.loseLife();
            if (myPlayer.getLives() > 0) {
                placeItemsForStart();
            }
        }
        for (PowerUp power : myPowerUps){
            power.move(elapsedTime);
        }
        if (myPlayer.getLives() == 0 || countTotalBricks() == countDestroyedBricks()){
            endGame();
        }
    }

    /**
     * Counts how many bricks have had their health reduced to 0 (making them "destroyed")
     * @return number of bricks with 0 health
     */
    private int countDestroyedBricks(){
        int destroyedBricks = 0;
        for (ArrayList<Brick> brickRow : myBricks) {
            for (Brick myBrick : brickRow) {
                if (myBrick.getHealth() == 0) {
                    destroyedBricks += 1;
                }
            }
        }
        return destroyedBricks;
    }

    /**
     * Counts the total number of bricks the level started with
     * @return total number of bricks
     */
    private int countTotalBricks(){
        int totalBricks = 0;
        for (ArrayList<Brick> brickRow : myBricks){
            totalBricks += brickRow.size();
        }
        return totalBricks;
    }

    /**
     * Determines if the winner won or lost in order to call the appropriate message
     */
    private void endGame(){
        gameOver = true;
        if(countDestroyedBricks() != countTotalBricks()){
            displayGameOverMessage("You Lost :(", Color.RED);
        }
        else{
            displayGameOverMessage("You Won!", Color.GREEN);
            myBall.setVisible(false);
        }
    }

    /**
     * Displays a message in the middle of the screen at the end of the game
     * @param message phrase to be displayed
     * @param color color of the displayed phrase
     */
    private void displayGameOverMessage(String message, Color color){
        myGameText.setText(message);
        myGameText.setFont(new Font(GAMETEXT_FONT_SIZE));
        myGameText.setFill(color);
        myGameText.setX(myScene.getWidth() / 2 - (myGameText.getBoundsInLocal().getWidth() / 2));
        myGameText.setY(myScene.getHeight() / 2);
    }

    /**
     * Positions ball and paddle for beginning of game
     */
    private void placeItemsForStart(){
        myBall.placeItem(myScene.getWidth() / 2 - myBall.getWidth() / 2, myScene.getHeight() / 2 - myBall.getHeight() / 2);
        myBall.setStartingVelocity();
        myPaddle.placeItem(myScene.getWidth() / 2 - myPaddle.getWidth() / 2, myScene.getHeight() - myPaddle.getHeight());
    }


    private void checkBallBrickCollision() {
        for (ArrayList<Brick> brickRow : myBricks) {
            for (Brick myBrick : brickRow) {
                if (myBrick.isVisible() && myBall.getParentBounds().intersects(myBrick.getParentBounds())) {
                    System.out.println(myBrick.isVisible());
                    myBrick.decreaseHealth();
                    myBall.bounceOff();
                    myPlayer.increaseScore(brickHitValue);
                    if (myBrick.isVisible() && myBrick.hasPowerUp()) {
                        PowerUp power = getRandomPowerUp();
                        power.placeItem(myBrick.getXCoordinate(), myBrick.getYCoordinate());
                        power.startFalling();
                    }
                }
            }
        }
    }

    private PowerUp getRandomPowerUp(){
        Random rand = new Random();
        return myPowerUps.get(rand.nextInt(myPowerUps.size()));
    }

    private void checkBallHitsPaddle(){
        if(myBall.getParentBounds().intersects(myPaddle.getParentBounds())){
            myBall.bounceOffPad();
        }
    }

    public void handleCheatKeys(KeyCode code, double elapsedTime){
        if (!gameOver) {
            if (code.getChar().equals("L")) {
                myPlayer.gainLife();
            }
            if (code.getChar().equals("R")) {
                placeItemsForStart();
            }
            if (code.isArrowKey()) {
                myPaddle.handleSideKeyInput(code, myScene.getWidth(), elapsedTime);
            }
        }

    }

}
