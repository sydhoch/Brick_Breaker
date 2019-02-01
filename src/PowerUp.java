import javafx.scene.Group;
import javafx.scene.image.ImageView;

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
    private static int TOTAL_TIME = 8000;

    public PowerUp(){
        chooseType();
        setImageView(pickImage());
        setVisible(false);
        isActive = false;
    }

    enum powerUpType {
        POINTS_POWER, PADDLE_SIZE_POWER;
    }

    private ImageView pickImage(){
        return createImageView(myType.toString().toLowerCase() + POWERUP_IMAGE_NAME_ENDING);
    }


    private void chooseType(){
        Random rand = new Random();
        myType = powerUpType.values()[rand.nextInt(powerUpType.values().length)];
        System.out.println(myType.toString());
    }

    public void startFalling() {
        setYVelocity(FALLING_Y_VELOCITY);
        setVisible(true);
    }

    public void activate(Group root, Paddle myPaddle, Ball myBall, ArrayList<ArrayList<Brick>> myBricks){
        setVisible(false);
        isActive = true;
        switch (myType){
            case POINTS_POWER:
                for (ArrayList<Brick> brickRow : myBricks){
                    for (Brick myBrick : brickRow){
                        myBrick.beDoubleValue();
                    }
                }
                break;
            case PADDLE_SIZE_POWER:
               myPaddle.lengthen();
               break;
           }
        startTimer(root, myPaddle, myBall, myBricks);
    }

    private void startTimer(Group root, Paddle myPaddle, Ball myBall, ArrayList<ArrayList<Brick>> myBricks){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                deactivate(root, myPaddle, myBall, myBricks);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, TOTAL_TIME);
    }

    public void deactivate(Group root, Paddle myPaddle, Ball myBall, ArrayList<ArrayList<Brick>> myBricks){
        isActive = false;
        switch (myType){
            case POINTS_POWER:
                for (ArrayList<Brick> brickRow : myBricks){
                    for (Brick myBrick : brickRow){
                        myBrick.undoDoubleValue();
                    }
                }
                break;
            case PADDLE_SIZE_POWER:
                myPaddle.undoLengthen();
                break;
        }

    }




}
