import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.Group;
import java.util.Arrays;
import java.util.List;

public class MultiHitBrick extends Brick{

    private final static String BRICK_FILE_START = "brick";
    private final static String BRICK_FILE_END = ".gif";
    private static final int MAX_MULTIBRICK_HEALTH = 4;
    private final String DESTROY_BLOCK_EVENT = "Destroy Brick";
    private final String MULTI_DESTROY_BLOCK_EVENT = "Destroy Multiple Hit Brick";
    private int myStartHealth;


    public MultiHitBrick(int health) {
        super(health);
        myStartHealth = health;
        System.out.println(health);
        System.out.println(getHealth());

    }

    @Override
    protected void setBrickImage(){
        System.out.println(getHealth());
        System.out.println(BRICK_FILE_START + getHealth() + BRICK_FILE_END);
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
        if (tester != null && isDestroyed()){
            if(myStartHealth==1){
                tester.setFirstEvent(DESTROY_BLOCK_EVENT);
            }
            else if(myStartHealth>1){
                tester.setFirstEvent(MULTI_DESTROY_BLOCK_EVENT);
            }
            animation.stop();
            tester.callTest();
        }
        setBrickImage();
    }

    @Override
    public void activateBrickAbility(Ball ball, Group root, List<PowerUp> powerUps,
                                     int screenSize, Tests tester, Timeline animation) {

    }




}
