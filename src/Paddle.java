
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.lang.*;


public class Paddle extends Item{

    public static final String PADDLE_IMAGE = "paddle.gif";


    public Paddle(){
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        myImage = new ImageView(image);
        myXVelocity = 1000;

    }


//    public void placeForStart(int screenSize){
//        myImage.setX(screenSize / 2 - myImage.getBoundsInLocal().getWidth() / 2);
//        myImage.setY(screenSize - myImage.getBoundsInLocal().getHeight());
//    }



    /**
     * Moves paddle when direction keys are pressed (only RIGHT and LEFT are handled)
     * @param code key pressed by user
     */
    public void handleSideKeyInput(KeyCode code, double screenWidth, double elapsedTime){
        if (code == KeyCode.RIGHT && myImage.getBoundsInLocal().getMaxX() < screenWidth) {
            myXVelocity = Math.abs(myXVelocity);
            move(elapsedTime);
        }
        else if (code == KeyCode.LEFT && myImage.getBoundsInLocal().getMinX() > 0) {
            myXVelocity = Math.abs(myXVelocity) * -1;
            move(elapsedTime);
        }
    }

    public void move(double elapsedTime){
        myImage.setX(myImage.getX() + myXVelocity * elapsedTime);
    }




    /**
     * Gets paddle image
     * @return paddle image as ImageView
     */
    public ImageView getImage(){
        return myImage;
    }
}
