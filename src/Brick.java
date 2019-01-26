import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;

/**
 * Creates a simulated brick for ball to hit, deteriorate, and eventually destroy
 *
 * @author leahschwartz
 */
public class Brick extends Rectangle {

    public static final String BRICK_IMAGE = "brick1.gif";
    private ImageView myBrick;
    private int myHealth;

    public Brick(int health){
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(BRICK_IMAGE));
        myBrick = new ImageView(image);
        myHealth = health;

    }

    /**
     * Decreases brick's health and calls for it to be destroyed once health is too low
     */
    public void decreaseHealth(){
        if (myHealth > 1){
            myHealth--;
        }
        else{
            destroyBrick();
        }
    }

    /**
     * Gets rid of brick
     */
    private void destroyBrick(){
        myBrick.setVisible(false);

    }



    /**
     * Gets brick image
     * @return brick image as ImageView
     */
    public ImageView getView(){
        return myBrick;
    }




}


