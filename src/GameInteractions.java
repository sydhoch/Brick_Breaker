import javafx.scene.Group;

import java.util.*;

import javafx.animation.Timeline;

public class GameInteractions {

    private Group myRoot;
    private Ball myBall;
    private ArrayList<ArrayList<Brick>> myBricks;
    private Paddle myPaddle;
    private Player myPlayer;
    private ArrayList<PowerUp> myPowerUps;
    ArrayList<PowerUpFactory> myPowerUpFactories;
    private ArrayList<Integer> powerUpCollisions;
    private int numDestructions;
    //RandomPowerUpFactory myPowerFactory;

    public GameInteractions(Group root, Ball ball, ArrayList<ArrayList<Brick>> bricks, Paddle paddle,
                            Player player, ArrayList<PowerUp> powerUps){
        myRoot = root;
        myBall = ball;
        myBricks = bricks;
        myPaddle = paddle;
        myPlayer = player;
        myPowerUps = powerUps;
        myPowerUpFactories = new ArrayList<>(Arrays.asList(new PaddleSizePowerUpFactory(myPaddle), new PointsPowerUpFactory(myPlayer),
                new BallSpeedPowerUpFactory(myBall)));
        //myPowerFactory = new RandomPowerUpFactory();


    }

    /**
     * Bounces the ball off the paddle if they collide
     */
    public void checkBallHitsPaddle(){
        if(myBall.collidesWith(myPaddle)){
            myBall.
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
                if (brick.collidesWith(myBall)) {
                    myPlayer.increaseScore(myPlayer.getScoreIncrement());
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
            if (powerUp.collidesWith(myPaddle)) {
                powerUp.activate();
            }
        }
    }

    private void updateGameOnBrickDestruction(Brick brick){
      //  myPlayer.increaseScore(brick.getValue());
        if (brick.hasPowerUp()){
            releasePowerUp(brick);
        }
    }

    private void releasePowerUp(Brick brick){
        PowerUp myPowerUp = createRandomPowerUp();
        myPowerUps.add(myPowerUp);
        myRoot.getChildren().add(myPowerUp.getImage());
        myPowerUp.placeItem(brick.getXCoordinate(), brick.getYCoordinate());
        myPowerUp.startFalling();

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



//       private class RandomPowerUpFactory {
//
//        private ArrayList<PaddleSizePowerUpFactory> myPowerUpFactories;
//        private RandomPowerUpFactory(){
//            myPowerUpFactories = new ArrayList<>(Arrays.asList(new PaddleSizePowerUpFactory()));
//        }

        private PowerUp createRandomPowerUp(){
            Collections.shuffle(myPowerUpFactories);
            return myPowerUpFactories.get(0).create();

        }
//    }


}




