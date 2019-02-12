import javafx.animation.Timeline;
import javafx.scene.Group;
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
<<<<<<< HEAD
        myStartHealth = health;
        System.out.println(health);
        System.out.println(getHealth());
=======

>>>>>>> 1e0fb330023ce4d85f63ef519c4cf79030f71306

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
