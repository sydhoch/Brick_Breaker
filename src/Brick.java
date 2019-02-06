import javafx.scene.image.Image;
import javafx.animation.Timeline;

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
    private boolean hasPowerUp;


    public Brick(int health, int value){
        setImage(new Image(this.getClass().getClassLoader().getResourceAsStream((BRICK_IMAGE))));
        myHealth = health;
        myValue = value;
        setCanSee(true);
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
     * Decreases brick's health and "destroys" it once health is too low
     */
    public void decreaseHealth(Tests tester, Timeline animation) {
        if (myHealth > 1) {
            myHealth--;
        } else {
            myHealth = 0;
            setCanSee(false);
            isDestroyed = true;
            if (tester != null){
                tester.setFirstEvent("Destroy Block");
                animation.stop();
                tester.callTest();
            }
        }
    }

    public void beDoubleValue(){
        myValue *= 2;
    }

    public void undoDoubleValue(){
        myValue /= 2;
    }

    public void setHasPowerUp(boolean containsPowerUp){
        hasPowerUp = containsPowerUp;
    }



}


