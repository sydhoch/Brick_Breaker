import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import java.lang.*;

public class Paddle extends Item{

    private static final String PADDLE_IMAGE = "paddle.gif";
    private static final int PADDLE_SPEED = 1000;
    private boolean isExtraWide;

    public Paddle(){
        setImage(new Image(this.getClass().getClassLoader().getResourceAsStream((PADDLE_IMAGE))));
        setXVelocity(PADDLE_SPEED);
        setVisible(true);
        isExtraWide = false;
    }

    public void lengthen(){
       // if (!isExtraWide) {
            setSize(getWidth() * 2, getHeight());
           // isExtraWide = true;
       // }
    }
//
    public void undoLengthen(){
      //  if (isExtraWide) {
            setSize(getWidth() / 2, getHeight());
        //    isExtraWide = false;
       // }
    }

    /**
     * Moves paddle when direction keys are pressed (only RIGHT and LEFT are handled)
     * @param code key pressed by user
     */
    public void handleSideKeyInput(KeyCode code, double screenWidth, double elapsedTime){
        if (code == KeyCode.RIGHT && getBoundsInLocal().getMaxX() < screenWidth) {
            setXVelocity(Math.abs(getXVelocity()));
            move(elapsedTime);
        }
        else if (code == KeyCode.LEFT && getBoundsInLocal().getMinX() > 0) {
            setXVelocity(Math.abs(getXVelocity()) * -1);
            move(elapsedTime);
        }
    }

    public boolean isExtraWide() {
        return isExtraWide;
    }

}
