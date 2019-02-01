import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Random;

public class GameInteractions {

    private Group myRoot;
    private Ball myBall;
    private ArrayList<ArrayList<Brick>> myBricks;
    private Paddle myPaddle;
    private Player myPlayer;
    private ArrayList<PowerUp> myPowerUps;
    private ArrayList<Integer> powerUpCollisions;
    private int numDestructions;

    private static final double PERCENT_OF_COLLISIONS_WITH_POWERUPS = 1;

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


    public void checkBallBricksCollision() {
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
                            myRoot.getChildren().add(myPowerUp);
                            myPowerUp.placeItem(myBrick.getX(), myBrick.getY());
                            myPowerUp.startFalling();
                        }
                        numDestructions += 1;
                    }
                }
            }
        }
    }

    public void checkPowerUpPaddleCollision() {

        for (PowerUp myPowerUp : myPowerUps) {
            if (myPowerUp.isVisible() && myPowerUp.getBoundsInParent().intersects(myPaddle.getBoundsInParent())) {
                myPowerUp.activate(myRoot, myPaddle, myBall, myBricks);
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
        return powerUpCollisions;
    }


    public void checkBallHitsPaddle(){
        if(myBall.getBoundsInParent().intersects(myPaddle.getBoundsInParent())){
            myBall.bounceOffPad();
        }
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
