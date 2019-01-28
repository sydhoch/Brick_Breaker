import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract public class Item {
    ImageView myImage;
    String myImageName;
    int myYVelocity;
    int myXVelocity;

    abstract void placeForStart(int screenSize);

    abstract ImageView getMyImage();

    abstract void move(double elapsedTime);




}
