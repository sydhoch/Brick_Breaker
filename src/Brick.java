import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Creates a simulated brick for ball to hit, deteriorate, and eventually destroy
 *
 * @author leahschwartz
 */
public class Brick extends Item{

    public static final String BRICK_IMAGE = "brick1.gif";
    private int myHealth;

    public Brick(int health){
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(BRICK_IMAGE));
        myImage = new ImageView(image);
        myHealth = health;
       // myImage.setFitWidth(width);
       // myImage.setFitHeight(height);
    }


    /**
     * Decreases brick's health and calls for it to be destroyed once health is too low
     */
    public void decreaseHealth(){
        if (myHealth > 1){
            myHealth--;
        }
        else{
            myHealth=0;
            destroyBrick();
        }
    }
    public int getHealth(){
        return myHealth;
    }

    /**
     * Gets rid of brick
     */
    private void destroyBrick(){
        myImage.setVisible(false);
    }

    /**
     * Gets brick image
     * @return brick image as ImageView
     */
    public ImageView getImage(){
        return myImage;
    }




}


