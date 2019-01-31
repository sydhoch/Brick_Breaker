import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class PowerUp extends Item{

    private static final int FALLING_Y_VELOCITY = 100;
    private int typeIndex;
    //private static final String[] typeImages = new String[]{"pointsPower.gif", "sizePower.gif"};
    private static final String[] types = new String[]{"pointsPower", "sizePower"};
    private String myType;
    private static final String POWERUP_IMAGE_NAME_ENDING = ".gif";
    private ImageView[] myImageViews;

    public PowerUp(){
       // createAllImageViews();
        chooseType();
    }

//    public void createAllImageViews() {
//        myImageViews = new ImageView[types.length];
//        for (int i = 0; i < types.length; i++) {
//            myType = types[i];
//            myImageViews[i] = createImageView(makeImageName());
//        }
//    }

    public ImageView[] getAllImageViews(){
        return myImageViews;
    }

    public void chooseType(){
        Random rand = new Random();
        typeIndex = rand.nextInt(types.length);
        myType = types[typeIndex];
        setImage(createImageView(makeImageName()));
        setVisible(false);
    }

    private String makeImageName(){
        //System.out.println(type + POWERUP_IMAGE_NAME_ENDING);
        return myType + POWERUP_IMAGE_NAME_ENDING;
    }

    public void startFalling() {
        //chooseType();
        setYVelocity(FALLING_Y_VELOCITY);
        setVisible(true);
    }




}
