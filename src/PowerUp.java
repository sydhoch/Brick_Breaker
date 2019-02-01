import javafx.scene.Group;
import javafx.scene.image.Image;
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
        setImage(pickImage());
        setVisible(false);
        isActive = false;
    }

    enum powerUpType {
        POINTS_POWER, PADDLE_SIZE_POWER;
    }

    private Image pickImage(){
        return new Image(this.getClass().getClassLoader().getResourceAsStream((myType.toString().toLowerCase() + POWERUP_IMAGE_NAME_ENDING)));
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

    public void activate(Group root, Paddle paddle, Ball ball, ArrayList<ArrayList<Brick>> bricks){
        setVisible(false);
        isActive = true;
        switch (myType){
            case POINTS_POWER:
                for (ArrayList<Brick> brickRow : bricks){
                    for (Brick myBrick : brickRow){
                        myBrick.beDoubleValue();
                    }
                }
                break;
            case PADDLE_SIZE_POWER:
               paddle.lengthen();
               break;
           }
        startTimer(root, paddle, ball, bricks);
    }

    private void startTimer(Group root, Paddle paddle, Ball ball, ArrayList<ArrayList<Brick>> bricks){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                deactivate(root, paddle, ball, bricks);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, TOTAL_TIME);
    }

    public void deactivate(Group root, Paddle paddle, Ball ball, ArrayList<ArrayList<Brick>> bricks){
        isActive = false;
        switch (myType){
            case POINTS_POWER:
                for (ArrayList<Brick> brickRow : bricks){
                    for (Brick brick : brickRow){
                        brick.undoDoubleValue();
                    }
                }
                break;
            case PADDLE_SIZE_POWER:
                paddle.undoLengthen();
                break;
        }

    }




}
