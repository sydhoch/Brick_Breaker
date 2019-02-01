import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


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

    public GamePlay(double elapsedTime){
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
    }

    public Scene getScene() {
        return myScene;
    }

    private Scene setUpNewGame (Paint background, double elapsedTime) {
        // create a place to see the shapes
       // root = new Group();

        root.getChildren().add(myGameText);
        root.getChildren().add(myStatus.getStatusText());
        root.getChildren().add(myBall.getImage());
        root.getChildren().add(myPaddle.getImage());

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
        myBall.placeItem(
                myScene.getWidth() / 2 - myBall.getWidth() / 2, myScene.getHeight() / 2 - myBall.getHeight() / 2);
        myBall.setStartingVelocity();
        myPaddle.placeItem(myScene.getWidth() / 2 - myPaddle.getWidth() / 2, myScene.getHeight() - myPaddle.getHeight());
    }


    private void checkBallBrickCollision() {
        for (ArrayList<Brick> brickRow : myBricks) {
            for (Brick myBrick : brickRow) {
                if (myBrick.isVisible() && myBall.getParentBounds().intersects(myBrick.getParentBounds())) {
                    myBrick.decreaseHealth();
                    myBall.bounceOff();
                    if (!myBrick.isVisible()) {
                        myPlayer.increaseScore(myBrick.getValue());
                        if (powerUpCollisions.contains(numDestructions)) {
                            PowerUp myPowerUp = new PowerUp();
                            myPowerUps.add(myPowerUp);
                            root.getChildren().add(myPowerUp.getImage());
                            myPowerUp.placeItem(myBrick.getXCoordinate(), myBrick.getYCoordinate());
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
            if (myPowerUp.isVisible() && myPowerUp.getParentBounds().intersects(myPaddle.getParentBounds())) {
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
