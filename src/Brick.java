import javafx.scene.Group;
import java.util.List;

/**
 * @author leahschwartz
 *
 * Abstract class to simulate a brick for ball to bounce off and possibly destory
 *
 */

public abstract class Brick extends Item{

    private int myHealth;
    private boolean isDestroyed;
    private boolean givesPoints;

    /**
     * creates Brick object with some health that is visible, undestroyed, meant to give points when hit
     * if Brick is created with health lower than 1, it is immediately "destroyed"
     * @param health number of hits needed to "destroy" brick
     */
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

    /**
     * sets Brick's ImageView to appropriate image
     */
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
     * decreases brick's health and "destroys" it once health is too low
     */
    public void decreaseHealth(Tests tester) {
        if (myHealth > 1) {
            myHealth--;
        } else {
            myHealth = 0;
            destroy();
        }
    }

    /**
     * makes block "invisible" to the game by taking away health, setting to destroyed, and making image invisible
     */
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

    /**
     * does any special effect brick may have on game
     * should be used upon destruction of brick by ball
     *
     * @param ball player's ball
     * @param root holds all onscreen images
     * @param powerUps list of powerups in game
     * @param screenSize size of game screen
     * @param tester object to run tests in testing mode
     */
    public abstract void activateBrickAbility(Ball ball, Group root, List<PowerUp> powerUps, int screenSize, Tests tester);
}


