import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract public class Item {
    ImageView myImage;
    int myYVelocity;
    int myXVelocity;

    abstract void placeForStart(int screenSize);

    abstract ImageView getImage();

    abstract void move(double elapsedTime);




}
