import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract public class Item extends ImageView{
    private ImageView myImage;
    private double myYVelocity;
    private double myXVelocity;
    private boolean isVisible;

    public void placeItem(double x, double y){
        setX(x);
        setY(y);
    }

//    public double getXCoordinate(){
//        return myImage.getX();
//    }

//    public  double getYCoordinate(){
//        return myImage.getY();
//    }

//    public ImageView getImage(){
//        return myImage;
//    }

//    public void setImageView(ImageView image){
//        myImage = image;
//    }

//    public ImageView createImageView(String imageName){
//        return new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(imageName)));
//    }

    /**
     * changes the Item's x- and y-coordinate by its speed dampened by the given elapsedTime
     * @param elapsedTime
     */
    public void move(double elapsedTime){
        setX(getX() + getXVelocity() * elapsedTime);
        setY(getY() + getYVelocity() * elapsedTime);

    }

    public void setSize(double width, double height){
        setFitWidth(width);
        setFitHeight(height);
    }

//    public void setVisible(boolean setVisible){
//        isVisible = setVisible;
//        setVisible(setVisible);
//
//    }
//
//    public boolean isVisible(){
//        return isVisible;
//    }

    public double getWidth(){
        return getBoundsInLocal().getWidth();
    }

    public double getHeight(){
        return getBoundsInLocal().getHeight();
    }

//    public Bounds getParentBounds(){
//        return myImage.getBoundsInParent();
//    }

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
