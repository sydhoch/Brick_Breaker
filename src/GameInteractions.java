import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Random;

public class GameInteractions {

//
//    public GameInteractions(){
//
//    }
//
//
//
//
//    private void checkBallBricksCollision(Group root, Ball ball, ArrayList<ArrayList<Brick>> bricks, Player player, ArrayList<PowerUp> powerUps) {
//        for (ArrayList<Brick> brickRow : bricks) {
//            for (Brick brick : brickRow) {
//                if (brick.isVisible() && brick.getParentBounds().intersects(brick.getParentBounds())) {
//                    brick.decreaseHealth();
//                    ball.bounceOff();
//                    if (!brick.isVisible()) {
//                        player.increaseScore(brick.getValue());
//                        if (powerUpCollisions.contains(numDestructions)) {
//                            PowerUp myPowerUp = new PowerUp();
//                            powerUps.add(myPowerUp);
//                            root.getChildren().add(myPowerUp.getImage());
//                            myPowerUp.placeItem(brick.getXCoordinate(), brick.getYCoordinate());
//                            myPowerUp.startFalling();
//                        }
//                        numDestructions += 1;
//                    }
//                }
//            }
//        }
//    }
//
//    private void checkPowerUpPaddleCollision(Group root, Ball ball, ArrayList<ArrayList<Brick>> bricks, ArrayList<PowerUp> powerUps) {
//
//        for (PowerUp myPowerUp : myPowerUps) {
//            if (myPowerUp.isVisible() && myPowerUp.getParentBounds().intersects(myPaddle.getParentBounds())) {
//                myPowerUp.activate(root, myPaddle, myBall, myBricks);
//            }
//        }
//    }
//
//
//
//    private int calculateNumPowerUpCollisions(){
//        return (int)(countTotalBricks() * PERCENT_OF_COLLISIONS_WITH_POWERUPS);
//    }
//
//    private ArrayList<Integer> getPossibleCollisions(){
//        ArrayList<Integer> possibleCollisions = new ArrayList<>();
//        for (int i = 0; i < countTotalBricks(); i++){
//            possibleCollisions.add(i);
//        }
//        return possibleCollisions;
//    }
//
//
//    private void choosePowerUpCollisions(){
//        Random rand = new Random();
//        powerUpCollisions = new ArrayList<>();
//        ArrayList<Integer> possibleCollisions = getPossibleCollisions();
//        for (int i = 0; i < calculateNumPowerUpCollisions(); i++){
//            int randomIndex = rand.nextInt(possibleCollisions.size());
//            int randoNum = possibleCollisions.get(randomIndex);
//            possibleCollisions.remove(randomIndex);
//            powerUpCollisions.add(randoNum);
//        }
//    }
//
//
//    private void checkBallHitsPaddle(Ball ball, Paddle paddle){
//        if(ball.getParentBounds().intersects(paddle.getParentBounds())){
//            ball.bounceOffPad();
//        }
//    }

}
