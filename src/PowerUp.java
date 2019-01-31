import javafx.scene.image.Image;

import java.util.Random;

public class PowerUp extends Item{

    private static final int FALLING_Y_VELOCITY = 100;
    private String type;
    private static final String[] types = new String[]{"pointsPower", "sizePower"};
    private static final String POWERUP_IMAGE_NAME_ENDING = ".gif";

    public PowerUp(){
        chooseType();
        setImage(makeImageName());
        setVisible(false);
    }

    public void chooseType(){
        Random rand = new Random();
        type = types[rand.nextInt(types.length)];
        //System.out.println(type);
    }

    private String makeImageName(){
        //System.out.println(type + POWERUP_IMAGE_NAME_ENDING);
        return type + POWERUP_IMAGE_NAME_ENDING;
    }

    public void startFalling(){
        setYVelocity(FALLING_Y_VELOCITY);
        setVisible(true);
    }


}
