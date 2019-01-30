import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Creates a simulated brick for ball to hit, deteriorate, and eventually destroy
 *
 * @author leahschwartz
 */
public class Brick extends Item{

    private static final String BRICK_IMAGE = "brick1.gif";
    private int myHealth;
    private int myValue;
    private boolean isDestroyed;


    public Brick(int health, int value){
        var image = new Image(this.getClass().getClassLoader().getResourceAsStream(BRICK_IMAGE));
        myImage = new ImageView(image);
        myHealth = health;
        myValue = value;
        isDestroyed = false;
    }


    public int getValue(){
        return myValue;
    }

    public int getHealth(){
        return myHealth;
    }

    public boolean isDestroyed() {
        return isDestroyed;
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

    /**
     * Gets rid of brick
     */
    private void destroyBrick(){
        myImage.setVisible(false);
        isDestroyed = true;
    }





}


