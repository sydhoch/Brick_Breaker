import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

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
    //private int brickHitValue;
    private boolean gameOver;
    private int numDestructions;
    private Group root;
    private ArrayList<PowerUp> myPowerUps;
    private ArrayList<Integer> powerUpCollisions;
    private static final double PERCENT_OF_COLLISIONS_WITH_POWERUPS = 1;
    private static final int NUM_STARTING_LIVES = 3;
    private static final int NUM_STARTING_LEVEL = 0;
    private static final int GAMETEXT_FONT_SIZE = 20;
    private static final int SCREEN_SIZE = 500;
    private static final Color BACKGROUND = Color.WHITESMOKE;
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;

    private Timeline animation;

    private Boolean testFile; //true if test file is not null

    public GamePlay(double elapsedTime, String test){
        testFile = (test!=null);
        if(testFile){
            Tests tester = new Tests(test);
        }
        root = new Group();
        BrickConfigurar brickSet = new BrickConfigurar("example.txt", root, elapsedTime);
        myBall = new Ball();
        myPaddle = new Paddle();
        myBricks = brickSet.getBricks();
        myGameText = new Text();
        myPlayer = new Player(NUM_STARTING_LIVES);
        myScene = new Scene(root, SCREEN_SIZE, SCREEN_SIZE, BACKGROUND);
        levelNum = NUM_STARTING_LEVEL;
        placeItemsForStart();
       // brickHitValue = 1;
        myStatus = new Status(SCREEN_SIZE);
        gameOver = false;
        numDestructions = 0;
        myPowerUps = new ArrayList<>();
        choosePowerUpCollisions();
        setUpNewGame(Color.WHITESMOKE, elapsedTime);



        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
    }

    public Scene getScene() {
        return myScene;
    }

    private Scene setUpNewGame (Paint background, double elapsedTime) {
        // create a place to see the shapes
       // root = new Group();

        root.getChildren().add(myGameText);
        root.getChildren().add(myStatus.getStatusText());
        root.getChildren().add(myBall);
        root.getChildren().add(myPaddle);

        //scene.setOnKeyPressed(key -> handleCheatKeys(key.getCode(), elapsedTime));

        return myScene;
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
        checkPowerUpPaddleCollision();
        if (myBall.ballFell(myScene.getHeight()) && myBall.isVisible()) {
            myBall.setVisible(true);
            myPlayer.loseLife();
            if (myPlayer.getLives() > 0) {
                placeItemsForStart();

            }
        }
        for (PowerUp myPowerUp : myPowerUps) {
            myPowerUp.move(elapsedTime);
        }
        if (myPlayer.getLives() == 0 || countRemainingBricks() == 0){
            endGame();
        }
    }

    /**
     * Counts how many bricks have not had their health reduced to 0 (meaning they have not been "destroyed")
     * @return number of bricks with 0 health
     */
    private int countRemainingBricks(){
        int remainingBricks = 0;
        for (ArrayList<Brick> brickRow : myBricks) {
            for (Brick myBrick : brickRow) {
                if (myBrick.getHealth() != 0) {
                    remainingBricks += 1;
                }
            }
        }
        return remainingBricks;
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
        if(countRemainingBricks() != 0){
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
        if (testFile){

        }
        else {
            myBall.placeItem(
                    myScene.getWidth() / 2 - myBall.getWidth() / 2, myScene.getHeight() / 2 - myBall.getHeight() / 2);
            myBall.setStartingVelocity();
            myPaddle.placeItem(myScene.getWidth() / 2 - myPaddle.getWidth() / 2, myScene.getHeight() - myPaddle.getHeight());
        }
    }


    private void checkBallBrickCollision() {
        for (ArrayList<Brick> brickRow : myBricks) {
            for (Brick myBrick : brickRow) {
                if (myBrick.isVisible() && myBall.getBoundsInParent().intersects(myBrick.getBoundsInParent())) {
                    myBrick.decreaseHealth();
                    myBall.bounceOff();
                    if (!myBrick.isVisible()) {
                        myPlayer.increaseScore(myBrick.getValue());
                        if (powerUpCollisions.contains(numDestructions)) {
                            PowerUp myPowerUp = new PowerUp();
                            myPowerUps.add(myPowerUp);
                            root.getChildren().add(myPowerUp);
                            myPowerUp.placeItem(myBrick.getX(), myBrick.getY());
                            myPowerUp.startFalling();
                        }
                        numDestructions += 1;
                    }
                }
            }
        }
    }

    private void checkPowerUpPaddleCollision(){
        for (PowerUp myPowerUp : myPowerUps) {
            if (myPowerUp.isVisible() && myPowerUp.getBoundsInParent().intersects(myPaddle.getBoundsInParent())) {
                myPowerUp.activate(root, myPaddle, myBall, myBricks);
                }
            }
        }



    private int calculateNumPowerUpCollisions(){
        return (int)(countTotalBricks() * PERCENT_OF_COLLISIONS_WITH_POWERUPS);
    }

    private ArrayList<Integer> getPossibleCollisions(){
        ArrayList<Integer> possibleCollisions = new ArrayList<>();
        for (int i = 0; i < countTotalBricks(); i++){
            possibleCollisions.add(i);
        }
        return possibleCollisions;
    }


    private void choosePowerUpCollisions(){
        Random rand = new Random();
        powerUpCollisions = new ArrayList<>();
        ArrayList<Integer> possibleCollisions = getPossibleCollisions();
        for (int i = 0; i < calculateNumPowerUpCollisions(); i++){
            int randomIndex = rand.nextInt(possibleCollisions.size());
            int randoNum = possibleCollisions.get(randomIndex);
            possibleCollisions.remove(randomIndex);
            powerUpCollisions.add(randoNum);
        }
    }


    private void checkBallHitsPaddle(){
        if(myBall.getBoundsInParent().intersects(myPaddle.getBoundsInParent())){
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
            //not a cheat key
            if (code.isArrowKey()) {
                myPaddle.handleSideKeyInput(code, myScene.getWidth(), elapsedTime);
            }
        }
    }
    // attach "game loop" to timeline to play it
    public void handleAllKeys(KeyCode code, double elapsedTime){
        handleRunKeys(code, animation);
        handleCheatKeys(code, elapsedTime);
    }

    public void handleRunKeys(KeyCode code, Timeline animation){
        if (code.equals(code.SPACE)){
            playOrPause(animation);
        }
    }

    public void playOrPause(Timeline animation){
        if (animation.getStatus().equals(Animation.Status.RUNNING)){
            animation.pause();
        }
        else{
            animation.play();
        }
    }






}
