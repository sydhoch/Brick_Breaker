import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ball extends Item{

    private static final String BOUNCER_IMAGE = "ball.gif";
    private static final int STARTING_X_VELOCITY = 180;
    private static final int STARTING_Y_VELOCITY = 60;

    Ball(){
        myImage = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE)));
        myXVelocity = STARTING_X_VELOCITY;
        myYVelocity = STARTING_Y_VELOCITY;
    }

    /**
     * changes the Bouncer's x- and y-coordinate by its speed dampened by the given elapsedTime
     * @param elapsedTime
     */

    public void move(double elapsedTime){
        myImage.setX(myImage.getX() + myXVelocity * elapsedTime);
        myImage.setY(myImage.getY() + myYVelocity * elapsedTime);
    }

    /**
     * makes the ball bounce off of the left right and top walls by switching the direction of X & Y velocities
     * @param screenWidth width of screen
     * @param screenHeight height of screen
    */
    public void bounce(double screenWidth, double screenHeight){
        if(myImage.getX() < 0 || myImage.getX()>(screenWidth- myImage.getBoundsInLocal().getWidth())){
            myXVelocity *= -1;
        }
        if(myImage.getY() < 0){
            myYVelocity *= -1;
        }
    }

    /**
     * reverse direction of ball
     */
    public void bounceOffPad(){
            //myVelocityX*=1;
        myYVelocity *=-1;
    }
    public void bounceOff(){
        myXVelocity *= -1;
        myYVelocity *= -1;
    }

//    public void placeForStart(int x, int y){
//        myImage.setX(screenSize / 2 - myImage.getBoundsInLocal().getWidth() / 2);
//        myImage.setY(screenSize / 2 - myImage.getBoundsInLocal().getHeight() / 2);
//    }



    public boolean ballFell(double screenHeight){
        return myImage.getY() > (screenHeight);
    }

}
