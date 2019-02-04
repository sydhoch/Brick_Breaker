import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.Timeline;

public class GameInteractions {

    private Group myRoot;
    private Ball myBall;
    private ArrayList<ArrayList<Brick>> myBricks;
    private Paddle myPaddle;
    private Player myPlayer;
    private ArrayList<PowerUp> myPowerUps;
    private ArrayList<Integer> powerUpCollisions;
    private int numDestructions;

    private static final double PERCENT_COLLISIONS_WITH_POWERUPS = 1.0 / 3.0;

    public GameInteractions(Group root, Ball ball, ArrayList<ArrayList<Brick>> bricks, Paddle paddle,
                            Player player, ArrayList<PowerUp> powerUps){
        myRoot = root;
        myBall = ball;
        myBricks = bricks;
        myPaddle = paddle;
        myPlayer = player;
        myPowerUps = powerUps;
        powerUpCollisions = getPowerUpCollisions();
        numDestructions = 0;
    }

    /**
     * Bounces the ball off the paddle if they collide
     */
    public void checkBallHitsPaddle(){
        if(itemsCollide(myBall, myPaddle)){
            myBall.bounceOffPad();
        }
    }

    /**
     * Checks if a brick has been collided with by the ball and calls for the game to be updated by decreasing the
     * brick's health, bouncing the ball off, and possibly releasing powerups
     */
    public void checkBallBricksCollision(Tests tester, Timeline animation) {
        for (ArrayList<Brick> brickRow : myBricks) {
            for (Brick brick : brickRow) {
                if (itemsCollide(brick, myBall)) {
                    brick.decreaseHealth(tester, animation);
                    myBall.bounceOff();
                    if (brick.isDestroyed()) {
                        updateGameOnBrickDestruction(brick);
                    }
                }
            }
        }
    }

    /**
     * Activates a powerup if it collides with the paddle
     */
    public void checkPowerUpPaddleCollision() {
        for (PowerUp powerUp : myPowerUps) {
            if (itemsCollide(powerUp, myPaddle)) {
                powerUp.activate();
            }
        }
    }

    private boolean itemsCollide(Item a, Item b){
        return a.isVisible() && b.isVisible() &&
                a.getBoundsInParent().intersects(b.getBoundsInParent());
    }

    private void updateGameOnBrickDestruction(Brick brick){
            myPlayer.increaseScore(brick.getValue());
            System.out.println(powerUpCollisions);
            System.out.println(numDestructions );
            if (powerUpCollisions.contains(numDestructions)) {
                releasePowerUp(brick);
            }
        numDestructions += 1;  
    }

    private void releasePowerUp(Brick brick){
        PowerUp myPowerUp = new PowerUp(myRoot, myPaddle, myBall, myBricks);
        myPowerUps.add(myPowerUp);
        myRoot.getChildren().add(myPowerUp);
        myPowerUp.placeItem(brick.getX(), brick.getY());
        myPowerUp.startFalling();
    }


    private int calculateNumPowerUpCollisions(){
        return (int)(countTotalBricks() * PERCENT_COLLISIONS_WITH_POWERUPS);
    }

    private ArrayList<Integer> getPossibleCollisions(){
        ArrayList<Integer> possibleCollisions = new ArrayList<>();
        for (int i = 0; i < countTotalBricks(); i++){
            possibleCollisions.add(i);
        }
        return possibleCollisions;
    }

    private ArrayList<Integer> getPowerUpCollisions(){
        Random rand = new Random();
        powerUpCollisions = new ArrayList<>();
        ArrayList<Integer> possibleCollisions = getPossibleCollisions();
        for (int i = 0; i < calculateNumPowerUpCollisions(); i++){
            int randomIndex = rand.nextInt(possibleCollisions.size());
            int randoNum = possibleCollisions.get(randomIndex);
            possibleCollisions.remove(randomIndex);
            powerUpCollisions.add(randoNum);
        }
        System.out.println(powerUpCollisions);
        return powerUpCollisions;
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

}
