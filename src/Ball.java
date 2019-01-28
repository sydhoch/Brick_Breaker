import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ball extends Item{

    private int myVelocityX = 180;
    private int myVelocityY = 60;
    public static final String BOUNCER_IMAGE = "ball.gif";

    Ball(){
        myImage = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE)));
    }

    /**
     * changes the Bouncer's x- and y-coordinate by its speed dampened by the given elapsedTime
     * @param elapsedTime
     */

    public void move(double elapsedTime){
        myImage.setX(myImage.getX() + myVelocityX * elapsedTime);
        myImage.setY(myImage.getY() + myVelocityY * elapsedTime);
    }

    /**
     * makes the ball bounce off of the left right and top walls by switching the direction of X & Y velocities
     * @param screenWidth width of screen
     * @param screenHeight height of screen
    */
    public void bounce(double screenWidth, double screenHeight){
        if(myImage.getX() < 0 || myImage.getX()>(screenWidth- myImage.getBoundsInLocal().getWidth())){
            myVelocityX *= -1;
        }
        if(myImage.getY() < 0){
            myVelocityY *= -1;
        }
    }

    /**
     * reverse direction of ball
     */
    public void BounceOffPad(){
            //myVelocityX*=1;
            myVelocityY*=-1;
    }
    public void BounceOff(){
        myVelocityX *= -1;
        myVelocityY *= -1;
    }

    public void placeForStart(int screenSize){
        myImage.setX(screenSize / 2 - myImage.getBoundsInLocal().getWidth() / 2);
        myImage.setY(screenSize / 2 - myImage.getBoundsInLocal().getHeight() / 2);
    }

    /**
     * @returns ball's ImageView
     */
    public ImageView getMyImage(){
        return myImage;
    }

    public boolean ballFell(double screenHeight){
        return myImage.getY() > (screenHeight);
    }

}
