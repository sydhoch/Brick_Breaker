import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract public class Item {
    ImageView myImage;
    int myYVelocity;
    int myXVelocity;

    public void placeItem(double x, double y){
        myImage.setX(x);
        myImage.setY(y);
    }

    public ImageView getImage(){
        return myImage;
    }

    public void move(double elapsedTime){
        myImage.setX(myImage.getX() + myXVelocity * elapsedTime);
        myImage.setY(myImage.getY() + myYVelocity * elapsedTime);
    }

    public void setSize(int width, int height){
        myImage.setFitWidth(width);
        myImage.setFitHeight(height);
    }



}
