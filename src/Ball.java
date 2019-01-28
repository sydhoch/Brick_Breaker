import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ball {
    private int myVelocityX=180;
    private int myVelocityY=60;
    private ImageView myBall;
    public static final String BOUNCER_IMAGE = "ball.gif";

    Ball(){
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE));
        myBall = new ImageView(image);
    }

    /**
     * changes the Bouncer's x- and y-coordinate by its speed dampened by the given elapsedTime
     * @param elapsedTime
     */

    public void move(double elapsedTime){
        myBall.setX(myBall.getX() + myVelocityX * elapsedTime);
        myBall.setY(myBall.getY() + myVelocityY * elapsedTime);
    }

    /**
     * makes the ball bounce off of the left right and top walls by switching the direction of X & Y velocities
     * @param screenWidth width of screen
     * @param screenHeight height of screen
    */
    public void bounce(double screenWidth, double screenHeight){
        if(myBall.getX()<0 || myBall.getX()>(screenWidth- myBall.getBoundsInLocal().getWidth())){
            myVelocityX*=-1;
        }
        if(myBall.getY()<0){
            myVelocityY*=-1;
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
        myVelocityX*=-1;
        myVelocityY*=-1;
    }

    public void centerBall(int screenSize){
        myBall.setX(screenSize / 2 - myBall.getBoundsInLocal().getWidth() / 2);
        myBall.setY(screenSize / 2 - myBall.getBoundsInLocal().getHeight() / 2);
    }

    /**
     * @returns ball's ImageView
     */
    public ImageView getView(){
        return myBall;
    }

    public boolean ballFell(double screenHeight){
        return myBall.getY()>(screenHeight);
    }

}
