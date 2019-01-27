
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.*;


public class Paddle {

    public static final String PADDLE_IMAGE = "paddle.gif";
    private ImageView myPaddle;
    private static final int PADDLE_SPEED = 5;


    public Paddle(){
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        myPaddle = new ImageView(image);

    }


    public void handleSideKeyInput(KeyCode code){
        if (code == KeyCode.RIGHT) {
            myPaddle.setX(myPaddle.getX() + PADDLE_SPEED);
        }
        else if (code == KeyCode.LEFT) {
            myPaddle.setX(myPaddle.getX() - PADDLE_SPEED);
        }

    }




    /**
     * Gets paddle image
     * @return paddle image as ImageView
     */
    public ImageView getView(){
        return myPaddle;
    }



}
