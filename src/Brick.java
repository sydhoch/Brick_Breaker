import javafx.scene.image.Image;

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
        setImage(new Image(this.getClass().getClassLoader().getResourceAsStream((BRICK_IMAGE))));
        myHealth = health;
        myValue = value;
        setVisible(true);
        isDestroyed = false;
    }


    public int getValue(){
        return myValue;
    }

    public int getHealth(){
        return myHealth;
    }

    public boolean isDestroyed(){
        return isDestroyed;
    }

    /**
     * Decreases brick's health and calls for it to be destroyed once health is too low
     */
    public void decreaseHealth() {
        if (myHealth > 1) {
            myHealth--;
        } else {
            myHealth = 0;
            setVisible(false);
            isDestroyed = true;
        }
    }

    public void beDoubleValue(){
        myValue *= 2;
    }

    public void undoDoubleValue(){
        myValue /= 2;
    }



}


