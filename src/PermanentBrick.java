import javafx.scene.Group;
import java.util.List;
/**
 * @author leahschwartz
 *
 * Simulates a brick for ball to bounce off but does not get destroyed
 * Inherits from abstract Brick class
 * Dependent on Ball, Brick, Item, PowerUp, and Tests classes
 */
public class PermanentBrick extends Brick{

    private static final String PERMANENT_BRICK_IMAGE = "permanentBrick.gif";
    private static final int STARTING_HEALTH = 1;

    /**
     * Creates a brick with a black striped image which has an original health of 0, but will not be destroyed
     * and cannot have health decreased in order to remain a permanent obstacle
     */
    public PermanentBrick(){
        super(STARTING_HEALTH);
        setGivesPoints(false);
        setHealth(0);
    }

    /**
     * Does not do anything since permanent brick never loses health or gets destroyed by ball
     * @param tester object to run tests in testing mode
     */
    @Override
    public void decreaseHealth(Tests tester) {
    }

    @Override
    protected void setBrickImage() {
        setImage(PERMANENT_BRICK_IMAGE);
    }

    @Override
    public void activateBrickAbility(Ball ball, Group root, List<PowerUp> powerUps, int screenSize, Tests tester) {

    }
}
