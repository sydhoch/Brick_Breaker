import javafx.animation.Timeline;
import javafx.scene.Group;
import java.util.List;

public class PermanentBrick extends Brick{

    private static final String PERMANENT_BRICK_IMAGE = "permanentBrick.gif";
    private static final int STARTING_HEALTH = 1;

    public PermanentBrick(){
        super(STARTING_HEALTH);
        setGivesPoints(false);
        setHealth(0);
    }

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
