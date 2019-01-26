import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ball {
    public static final int BOUNCER_SPEED = 30;
    private int myVelocityX=1;
    private int myVelocityY=1;
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
        myBall.setX(myBall.getX() + BOUNCER_SPEED * elapsedTime);
    }

    public void bounce(double screenWidth, double screenHeight){
        if(myBall.getX()<0 || myBall.getX()>screenWidth){
            myVelocityX*=-1;
        }
        if(myBall.getY()<0 || myBall.getY()>screenHeight){
            myVelocityY*=-1;
        }
    }
    public ImageView getView(){
        return myBall;
    }

}
