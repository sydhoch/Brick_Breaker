import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ball extends Item{

    private static final String BOUNCER_IMAGE = "ball.gif";
    private static final int STARTING_X_VELOCITY = 180;
    private static final int STARTING_Y_VELOCITY = 60;

    Ball(){
        setImage(new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE)));
        setStartingVelocity();
        setVisible(true);
    }

    /**
     * changes the Bouncer's x- and y-coordinate by its speed dampened by the given elapsedTime
     * @param elapsedTime
     */

//    public void move(double elapsedTime){
//        //placeItem(getXCoordinate() + getXVelocity() * elapsedTime,getYCoordinate() + getYVelocity() * elapsedTime);
//    }

    /**
     * makes the ball bounce off of the left right and top walls by switching the direction of X & Y velocities
     * @param screenWidth width of screen
     * @param screenHeight height of screen
    */
    public void bounce(double screenWidth, double screenHeight){
        if(getXCoordinate() < 0 || getXCoordinate()>(screenWidth - getImage().getBoundsInLocal().getWidth())){

            setXVelocity(getXVelocity() * -1);
        }
        if(getYCoordinate() < 0){
            setYVelocity(getYVelocity() * -1);
        }
    }

    /**
     * reverse direction of ball
     */
    public void bounceOffPad(){
        setYVelocity(getYVelocity() * -1);
    }

    public void bounceOff(){
        setYVelocity(getYVelocity() * -1);
        setXVelocity(getXVelocity() * -1);
    }

    public boolean ballFell(double screenHeight){
        return getYCoordinate() > (screenHeight);
    }

    public void setStartingVelocity(){
        setXVelocity(STARTING_X_VELOCITY);
        setYVelocity(STARTING_Y_VELOCITY);
    }

}
