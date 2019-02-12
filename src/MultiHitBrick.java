import javafx.animation.Timeline;
import javafx.scene.Group;
import java.util.List;

public class MultiHitBrick extends Brick{

    private final static String BRICK_FILE_START = "brick";
    private final static String BRICK_FILE_END = ".gif";
    private static final int MAX_MULTIBRICK_HEALTH = 4;

    public MultiHitBrick(int health) {
        super(health);


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

    @Override
    public void decreaseHealth(Tests tester, Timeline animation){
        super.decreaseHealth(tester, animation);
        setBrickImage();
    }

    @Override
    public void activateBrickAbility(Ball ball, Group root, List<PowerUp> powerUps,
                                     int screenSize) {

    }




}
