import javafx.scene.Group;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PowerUp extends Item{

    private static final int FALLING_Y_VELOCITY = 100;
    private powerUpType myType;
    private static final String POWERUP_IMAGE_NAME_ENDING = ".gif";
    private boolean isActive;
    private int timeLeft;
    private static int TOTAL_TIME = 10000;

    private Group myRoot;
    private Ball myBall;
    private ArrayList<ArrayList<Brick>> myBricks;
    private Paddle myPaddle;

    public PowerUp(Group root, Paddle paddle, Ball ball, ArrayList<ArrayList<Brick>> bricks){
        chooseType();
        setImage(pickImageName());
        setCanSee(false);
        myRoot = root;
        myBall = ball;
        myBricks = bricks;
        myPaddle = paddle;
    }

    enum powerUpType {
        POINTS_POWER, PADDLE_SIZE_POWER;
    }

    private String pickImageName(){
        return (myType.toString().toLowerCase() + POWERUP_IMAGE_NAME_ENDING);
    }

    private void chooseType(){
        Random rand = new Random();
        myType = powerUpType.values()[rand.nextInt(powerUpType.values().length)];
    }

    /**
     * Causes powerup to fall down toward bottom of screen and become visible
     */
    public void startFalling() {
        setYVelocity(FALLING_Y_VELOCITY);
        setCanSee(true);
    }

    /**
     * Activates powerup by initiating some kind of special effect based on powerup type and sets timer after which
     * powerup will be deactivated and the special effect will be reversed back to normal
     */
    public void activate(){
        setCanSee(false);
        doPower();
        startTimer();
    }

    private void startTimer(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                deactivate();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, TOTAL_TIME);
    }

    private void doPower(){
        switch (myType){
//            case POINTS_POWER:
//                for (ArrayList<Brick> brickRow : myBricks){
//                    for (Brick myBrick : brickRow){
//                        myBrick.beDoubleValue();
//                    }
//                }
//                break;
            case PADDLE_SIZE_POWER:
                myPaddle.lengthen();
                break;
        }
    }

    private void deactivate(){
        switch (myType){
//            case POINTS_POWER:
//                for (ArrayList<Brick> brickRow : myBricks){
//                    for (Brick brick : brickRow){
//                        brick.undoDoubleValue();
//                    }
//                }
//                break;
            case PADDLE_SIZE_POWER:
                myPaddle.undoLengthen();
                break;
        }
    }
}
