import javafx.scene.input.KeyCode;
import java.lang.*;
/**
 * @author leahschwartz and sydneyhochberg
 *
 * Simulates a paddle for player to move and ball to bounce off
 *
 */

public class Paddle extends Item{

    private static final String PADDLE_IMAGE = "paddle.gif";
    private static final int PADDLE_SPEED = 1000;

    public Paddle(){
        setImage(PADDLE_IMAGE);
        setXVelocity(PADDLE_SPEED);
        setCanSee(true);
    }

    public void lengthen(){
        setSize(getWidth() * 2, getHeight());
    }

    public void undoLengthen(){
        setSize(getWidth() / 2, getHeight());
    }

    /**
     * Moves paddle when direction keys are pressed (only RIGHT and LEFT are handled)
     * @param code key pressed by user
     */
    public void handleSideKeyInput(KeyCode code, double screenWidth, double elapsedTime){
        if (code == KeyCode.RIGHT && getMaxX() < screenWidth) {
            setXVelocity(Math.abs(getXVelocity()));
            move(elapsedTime);
        }
        else if (code == KeyCode.LEFT && getMinX() > 0) {
            setXVelocity(Math.abs(getXVelocity()) * -1);
            move(elapsedTime);
        }
    }

    public double getCenter(){
        return getXCoordinate() + getWidth()/2;
    }
    public double getCenterOfLeftSide(){
        return getXCoordinate() + getWidth()/4;
    }
    public double getCenterOfRightSide(){
        return getCenter()+getCenterOfLeftSide();
    }
}
