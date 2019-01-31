import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract public class Item {
    private ImageView myImage;
    private double myYVelocity;
    private double myXVelocity;
    private boolean isVisible;

    public void placeItem(double x, double y){
        myImage.setX(x);
        myImage.setY(y);
    }

    public double getXCoordinate(){
        return myImage.getX();
    }

    public  double getYCoordinate(){
        return myImage.getY();
    }

    public ImageView getImage(){
        return myImage;
    }

    public void setImage(Image image){
        myImage = new ImageView(image);
    }

    public void move(double elapsedTime){
        myImage.setX(myImage.getX() + getXVelocity() * elapsedTime);
        myImage.setY(myImage.getY() + getYVelocity() * elapsedTime);
    }

    public void setSize(double width, double height){
        myImage.setFitWidth(width);
        myImage.setFitHeight(height);
    }

    public void setVisible(boolean setVisible){
        isVisible = setVisible;
        myImage.setVisible(setVisible);

    }

    public boolean isVisible(){
        return isVisible;
    }

    public double getWidth(){
        return myImage.getBoundsInLocal().getWidth();
    }

    public double getHeight(){
        return myImage.getBoundsInLocal().getHeight();
    }

    public Bounds getParentBounds(){
        return myImage.getBoundsInParent();
    }

    public double getYVelocity() {
        return myYVelocity;
    }

    public void setYVelocity(double myYVelocity) {
        this.myYVelocity = myYVelocity;
    }

    public double getXVelocity() {
        return myXVelocity;
    }

    public void setXVelocity(double myXVelocity) {
        this.myXVelocity = myXVelocity;
    }
}
