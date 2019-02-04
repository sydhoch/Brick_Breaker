import javafx.scene.image.Image;
import javafx.animation.Timeline;

public class Ball extends Item {

    private static final String BOUNCER_IMAGE = "ball.gif";
    private static final int STARTING_X_VELOCITY = 180;
    private static final int STARTING_Y_VELOCITY = 60;

    Ball(Tests tester){
        setImage(new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE)));
        setStartingVelocity(tester);
        setVisible(true);

    }


    /**
     * makes the ball bounce off of the left right and top walls by switching the direction of X & Y velocities
     * @param screenWidth width of screen
     * @param screenHeight height of screen
    */
    public void bounce(double screenWidth, double screenHeight, Tests tester, Timeline animation){
        if(getX() < 0 || getX()>(screenWidth - getBoundsInLocal().getWidth())){
            setXVelocity(getXVelocity() * -1);
            if (tester!=null) {
                tester.setFirstEvent("Corner Bounce Back");
                animation.stop();
                tester.callTest();
            }
        }
        if(getY() < 0){
            setYVelocity(getYVelocity() * -1);
            if(tester!=null) {
                tester.setFirstEvent("Corner Bounce Back");
                animation.stop();
            }
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

    /**
     * Checks if ball is off of screen, indicating that it "fell"
     * @param screenHeight height of scene
     * @return if the ball is offscreen
     */
    public boolean ballFell(double screenHeight){
        return getY() > (screenHeight) && isVisible();
    }

    public void setStartingVelocity(Tests tester){
        if(tester!=null){
            setXVelocity(tester.getXVel());
            setYVelocity(tester.getYVel());
        }
        else{
            setXVelocity(STARTING_X_VELOCITY);
            setYVelocity(STARTING_Y_VELOCITY);
        }
    }

}
