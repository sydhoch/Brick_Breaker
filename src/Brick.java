import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.animation.Timeline;
import java.util.List;

/**
 * Creates a simulated brick for ball to hit, deteriorate, and eventually destroy
 *
 * @author leahschwartz
 */
public abstract class Brick extends Item{

    private int myHealth;
    private boolean isDestroyed;
    private boolean givesPoints;
    private final String DESTROY_BLOCK_EVENT = "Destroy Block";

    public Brick(int health){
        myHealth = health;
        setBrickImage();
        setCanSee(true);
        isDestroyed = false;
        givesPoints = true;
        if (health < 1) {
            destroy();
        }
    }

    protected abstract void setBrickImage();

    protected void setHealth(int newHealth){
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
            destroy();
            if (tester != null){
                tester.setFirstEvent(DESTROY_BLOCK_EVENT);
                animation.stop();
                tester.callTest();
            }
        }
    }

    public void destroy(){
        myHealth = 0;
        isDestroyed = true;
        setCanSee(false);
    }

    protected void setGivesPoints(boolean pointGiving){
        givesPoints = pointGiving;
    }

    public boolean givesPoints(){
        return givesPoints;
    }

    public abstract void activateBrickAbility(Ball ball, Group root, List<PowerUp> powerUps, int screenSize);
}


