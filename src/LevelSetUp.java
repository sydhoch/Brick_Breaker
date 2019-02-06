import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.ServiceConfigurationError;

public class LevelSetUp {

    private Ball myBall;
    private Paddle myPaddle;
    private ArrayList<ArrayList<Brick>> myBricks;
    private LevelText myLevelText;
    private int SCREEN_SIZE;
    private Group myRoot;
    private Timeline myAnimation;
    private static final String brickFileNameStart = "level";
    private static final String brickFileNameEnd = ".txt";

    public LevelSetUp(Ball ball, Paddle paddle, ArrayList<ArrayList<Brick>> bricks, Group root, int levelNum,
                      LevelText levelText, int SIZE, Timeline animation){
        myBall = ball;
        myPaddle = paddle;
        myBricks = bricks;
        SCREEN_SIZE = SIZE;
        myLevelText = levelText;
        myRoot = root;
        myAnimation = animation;
    }

    public void createNextLevel(int myLevelNum){
        myBricks.clear();
        BrickConfigurar myBrickConfig = new BrickConfigurar(brickFileNameStart + myLevelNum + brickFileNameEnd,
                myRoot, SCREEN_SIZE, myBricks);
        myLevelText.updateText();
        placeItemsForStart();
        myAnimation.pause();
    }

    public void placeItemsForStart(){
        myBall.placeItem(
                SCREEN_SIZE / 2.0 - myBall.getWidth() / 2, SCREEN_SIZE / 2.0);
        myPaddle.placeItem(SCREEN_SIZE / 2.0 - myPaddle.getWidth() / 2, SCREEN_SIZE - myPaddle.getHeight());
    }




}
