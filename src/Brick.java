import javafx.scene.Group;
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
    public void decreaseHealth(Tests tester) {
        if (myHealth > 1) {
            myHealth--;
        } else {
            myHealth = 0;
            destroy();
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

    public abstract void activateBrickAbility(Ball ball, Group root, List<PowerUp> powerUps, int screenSize, Tests tester);
}


