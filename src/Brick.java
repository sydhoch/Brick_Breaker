import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.animation.Timeline;

/**
 * Creates a simulated brick for ball to hit, deteriorate, and eventually destroy
 *
 * @author leahschwartz
 */
public abstract class Brick extends Item{

    private int myHealth;
    private boolean isDestroyed;
    private boolean hasPowerUp;

    public Brick(int health){
        myHealth = health;
        setBrickImage();
        setCanSee(true);
        isDestroyed = false;
        if (health < 1) {
            destroyBrick();
        }
    }

    protected abstract void setBrickImage();

    public void setHealth(int newHealth){
        myHealth = newHealth;
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
            destroyBrick();
            if (tester != null){
                tester.setFirstEvent("Destroy Block");
                animation.stop();
                tester.callTest();
            }
        }
    }

    public void setHasPowerUp(boolean containsPowerUp){
        hasPowerUp = containsPowerUp;
    }

    public boolean hasPowerUp(){
        return hasPowerUp;
    }

    public void destroyBrick(){
        myHealth = 0;
        isDestroyed = true;
        setCanSee(false);
    }
}


