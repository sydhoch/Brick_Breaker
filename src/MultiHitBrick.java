import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Arrays;

public class MultiHitBrick extends Brick{

    private Group myRoot;
    private final static String BRICK_FILE_START = "brick";
    private final static String BRICK_FILE_END = ".gif";

    public MultiHitBrick(int health, Group root) {
        super(health);
        System.out.println(health);
        System.out.println(getHealth());
        myRoot = root;

    }

    @Override
    public void setBrickImage(){
        System.out.println(getHealth());
        System.out.println(BRICK_FILE_START + getHealth() + BRICK_FILE_END);
        setImage(BRICK_FILE_START + getHealth() + BRICK_FILE_END);
    }

    @Override
    public void decreaseHealth(Tests tester, Timeline animation){
        super.decreaseHealth(tester, animation);
        setBrickImage();
    }



}
