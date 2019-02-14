import javafx.scene.input.KeyCode;
import java.lang.*;
/**
 * @author leahschwartz and sydneyhochberg
 *
 * Simulates a paddle for player to move and ball to bounce off
 * Inherits from abstract Item class
 * Dependent on Item class
 */

public class Paddle extends Item{

    private static final String PADDLE_IMAGE = "paddle.gif";
    private static final int PADDLE_SPEED = 1000;

    /**
     * Creates a paddle with image of a paddle and a velocity only in the X direction
     */
    public Paddle(){
        setImage(PADDLE_IMAGE);
        setXVelocity(PADDLE_SPEED);
        setCanSee(true);
    }

    /**
     * Makes paddle double in length
     */
    public void lengthen(){
        setSize(getWidth() * 2, getHeight());
    }

    /**
     * Makes paddle half as long
     */
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

    /**
     * Calculates coordinate of center of paddle based on current position
     * @return coordinate of center
     */
    public double getCenter(){
        return getXCoordinate() + getWidth()/2;
    }

    /**
     * Calculates coordinate of center of left side of paddle based on current position
     * @return coordinate of center of left side
     */
    public double getCenterOfLeftSide(){
        return getXCoordinate() + getWidth()/4;
    }

    /**
     * Calculates coordinate of center of right side of paddle based on current position
     * @return coordinate of center of right side
     */
    public double getCenterOfRightSide(){
        return getCenter()+getCenterOfLeftSide();
    }
}
