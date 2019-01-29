import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class GamePlay {

    Ball myBall;
    Paddle myPaddle;
    ArrayList<ArrayList<Brick>> myBricks;
    Player myPlayer;
    Status myStatus;
    Text myGameText;
    Scene myScene;
    int levelNum;


    public GamePlay(Ball ball, Paddle paddle, ArrayList<ArrayList<Brick>> bricks, Status status, Text gameText, Scene scene){
        myBall = ball;
        myPaddle = paddle;
        myBricks = bricks;
        myPlayer = new Player(3);
        myStatus = status;
        myGameText = gameText;
        myScene = scene;
        levelNum = 0;
        placeItemsForStart();
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
        if (myBall.ballFell(myScene.getHeight()) && myBall.getImage().isVisible()) {
            myPlayer.loseLife();
            if (myPlayer.getLives() > 0) {
                placeItemsForStart();
            }
        }
        if (myPlayer.getLives() == 0 || countTotalBricks() == countDestroyedBricks()){
            endGame();
        }
    }

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


    private int countTotalBricks(){
        int totalBricks = 0;
        for (ArrayList<Brick> brickRow : myBricks){
            totalBricks += brickRow.size();
        }
        return totalBricks;
    }

    private void endGame(){
        if(countDestroyedBricks() != countTotalBricks()){
            displayGameOverMessage("You Lost :(", Color.RED);
        }
        else{
            displayGameOverMessage("You Won!", Color.GREEN);
            myBall.getImage().setVisible(false);
        }
    }

    private void displayGameOverMessage(String message, Color color){
        myGameText.setText(message);
        myGameText.setFont(new Font(20));
        myGameText.setFill(color);
        myGameText.setX(myScene.getWidth() / 2 - (myGameText.getBoundsInLocal().getWidth() / 2));
        myGameText.setY(myScene.getHeight() / 2);
    }

    private void placeItemsForStart(){
        myBall.getImage().setVisible(true);
        myBall.placeItem(myScene.getWidth() / 2 - myBall.getImage().getBoundsInLocal().getWidth() / 2,
                myScene.getHeight() / 2 - myBall.getImage().getBoundsInLocal().getHeight() / 2);
        myPaddle.placeItem(myScene.getWidth() / 2 - myPaddle.getImage().getBoundsInLocal().getWidth() / 2,
                myScene.getHeight() - myPaddle.getImage().getBoundsInLocal().getHeight());
    }


    private void checkBallBrickCollision(){
        for (ArrayList<Brick> brickRow : myBricks){
            for (Brick myBrick : brickRow){
                if (myBrick.getHealth() > 0 && myBall.getImage().getBoundsInParent().intersects(myBrick.getImage().getBoundsInParent())) {
                    myBrick.decreaseHealth();
                    myBall.BounceOff();
                    myPlayer.increaseScore(1);
                }
            }
        }
    }

    private void checkBallHitsPaddle(){
        if(myBall.getImage().getBoundsInLocal().intersects(myPaddle.getImage().getBoundsInLocal())){
            myBall.BounceOffPad();
        }
    }

    public void handleCheatKeys(KeyCode code, double elapsedTime){
        if (code.getChar().equals("L")){
            myPlayer.gainLife();
        }
        if (code.getChar().equals("R")){
            placeItemsForStart();
        }
        if (code.isArrowKey()){
            myPaddle.handleSideKeyInput(code, myScene.getWidth(), elapsedTime);
        }

    }

}
