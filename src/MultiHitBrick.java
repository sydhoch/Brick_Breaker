import javafx.scene.Group;
import java.util.List;
/**
 * @author leahschwartz and sydneyhochberg
 *
 * Simulates a brick for ball to bounce off multiple times (1-4) and eventually destory
 * Strength is represented by color and color changes on each hit to demonstrate decrease of health
 */

public class MultiHitBrick extends Brick{

    private final static String BRICK_FILE_START = "brick";
    private final static String BRICK_FILE_END = ".gif";
    private static final int MAX_MULTIBRICK_HEALTH = 4;
    private final String DESTROY_BLOCK_EVENT = "Destroy Brick";
    private final String MULTI_DESTROY_BLOCK_EVENT = "Destroy Multiple Hit Brick";
    private int myStartHealth;

    /**
     * Creates a brick which has more health than a normal brick with an image based on starting health,
     * but which will change as health decreases
     * @param health number of hits before destruction
     */
    public MultiHitBrick(int health) {
        super(health);
        myStartHealth = health;
    }

    @Override
    protected void setBrickImage(){

        if (getHealth() > MAX_MULTIBRICK_HEALTH){
            setHealth(MAX_MULTIBRICK_HEALTH);
        }
        if (getHealth() > 0) {
            setImage(BRICK_FILE_START + getHealth() + BRICK_FILE_END);
        }
    }

    /**
     * Decreases brick's health and "destroys" it once health is too low
     * Additional capability of changing image on based new health after a decrease
     *
     * @param tester object to run tests in testing mode
     */
    @Override
    public void decreaseHealth(Tests tester){
        super.decreaseHealth(tester);
        if (tester != null && isDestroyed()){
            if(myStartHealth==1){
                tester.setFirstEvent(DESTROY_BLOCK_EVENT);
            }
            else if(myStartHealth>1){
                tester.setFirstEvent(MULTI_DESTROY_BLOCK_EVENT);
            }
            tester.callTest();
        }
        setBrickImage();
    }

    /**
     * Does not currently have any special ability besides longevity
     *
     * @param ball player's ball
     * @param root holds all onscreen images
     * @param powerUps list of powerups in game
     * @param screenSize size of game screen
     * @param tester object to run tests in testing mode
     */
    @Override
    public void activateBrickAbility(Ball ball, Group root, List<PowerUp> powerUps,
                                     int screenSize, Tests tester) {

    }




}
