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
    public static final int BOUNCER_SPEED = 50;
    private int myVelocityX=80;
    private int myVelocityY=40;
    private ImageView myView;
    public static final String BOUNCER_IMAGE = "ball.gif";

    Ball(){
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE));
        myView = new ImageView(image);
    }
    /**
     * changes the Bouncer's x- and y-coordinate by its speed dampened by the given elapsedTime
     * @param elapsedTime
     */

    public void move(double elapsedTime){
        myView.setX(myView.getX() + myVelocityX * elapsedTime);
        myView.setY(myView.getY() + myVelocityY * elapsedTime);
    }

    public void bounce(double screenWidth, double screenHeight){
        if(myView.getX()<0 || myView.getX()>(screenWidth-myView.getBoundsInLocal().getWidth())){
            myVelocityX*=-1;
        }
        if(myView.getY()<0){
            myVelocityY*=-1;
        }
    }
    public ImageView getView(){
        return myView;
    }

}
