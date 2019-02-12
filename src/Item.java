import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract public class Item {
    private double myYVelocity;
    private double myXVelocity;
    private boolean canSee;
    private ImageView myImage;

    public Item(){
        myImage = new ImageView();
    }

    public void placeItem(double x, double y){
        myImage.setX(x);
        myImage.setY(y);
    }

    /**
     * changes the Item's x- and y-coordinate by its speed dampened by the given elapsedTime
     * @param elapsedTime
     */
    public void move(double elapsedTime){
        myImage.setX(myImage.getX() + getXVelocity() * elapsedTime);
        myImage.setY(myImage.getY() + getYVelocity() * elapsedTime);

    }

    public void setSize(double width, double height){
        myImage.setFitWidth(width);
        myImage.setFitHeight(height);
    }

    public double getWidth(){
        return myImage.getBoundsInLocal().getWidth();
    }

    public double getHeight(){
        return myImage.getBoundsInLocal().getHeight();
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

    public boolean collidesWith(Item other){
        return this.canSee() && other.canSee() &&
                this.getParentBounds().intersects(other.getParentBounds());
    }

    public double getXCoordinate(){
        return myImage.getX();
    }

    public double getYCoordinate(){
        return myImage.getY();
    }

    public void setCanSee(boolean visible){
        canSee = visible;
        myImage.setVisible(visible);
    }

    public boolean canSee(){
        return canSee;
    }

    private Bounds getParentBounds(){
        return myImage.getBoundsInParent();
    }

    public ImageView getImage(){
        return myImage;
    }

    public void setImage(String imageFile){
        myImage.setImage(new Image(this.getClass().getClassLoader().getResourceAsStream((imageFile))));
    }

    public double getMaxX(){
       return myImage.getBoundsInLocal().getMaxX();
    }

    public double getMinX(){
        return myImage.getBoundsInLocal().getMinX();
    }

}
