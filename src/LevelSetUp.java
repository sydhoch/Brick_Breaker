import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class LevelSetUp {

    Ball myBall;
    Paddle myPaddle;
    ArrayList<ArrayList<Brick>> myBricks;
    int myLevelNum;
    Text myGameText;
    int SCREEN_SIZE;
    String levelStartMessage;

    public LevelSetUp(Ball ball, Paddle paddle, ArrayList<ArrayList<Brick>> bricks, int levelNum, Text gametext, int SIZE){
        myBall = ball;
        myPaddle = paddle;
        myBricks = bricks;
        myLevelNum = levelNum;
        myGameText = gametext;
        SCREEN_SIZE = SIZE;
        levelStartMessage = "Level " + levelNum + "! Press Space To Start";
        //createNextLevel();
    }

//    public void displayLevelStartMessage(){
//        myGameText.setVisible(true);
//        myGameText.setText(levelStartMessage);
//        myGameText.setFont(new Font(GAMETEXT_FONT_SIZE));
//        myGameText.setFill(color);
//        myGameText.setX(myScene.getWidth() / 2 - (myGameText.getBoundsInLocal().getWidth() / 2));
//        myGameText.setY(height);
//    }






}
