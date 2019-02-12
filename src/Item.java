import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * @author leahschwartz
 *
 * Abstract class to represent items in game, enabling them to share common qualities such as an Imageview,
 * movement, placement, speeds, and sizing
 */

abstract public class Item {
    private double myYVelocity;
    private double myXVelocity;
    private boolean canSee;
    private ImageView myImage;

    /**
     * creates an item with an ImageView
     */
    public Item(){
        myImage = new ImageView();
    }

    public void placeItem(double x, double y){
        myImage.setX(x);
        myImage.setY(y);
    }

    /**
     * changes the Item's x- and y-coordinate by its speed dampened by the given elapsedTime
     * @param elapsedTime amount of time by which to dampen speed
     */
    public void move(double elapsedTime){
        myImage.setX(myImage.getX() + getXVelocity() * elapsedTime);
        myImage.setY(myImage.getY() + getYVelocity() * elapsedTime);

    }

    /**
     * sets the size of ImageView
     * @param width desired width of ImageView
     * @param height desired height of ImageView
     */
    public void setSize(double width, double height){
        myImage.setFitWidth(width);
        myImage.setFitHeight(height);
    }

    /**
     * gets width of ImageView
     * @return width
     */
    public double getWidth(){
        return myImage.getBoundsInLocal().getWidth();
    }

    /**
     * gets height of ImageView
     * @return height
     */
    public double getHeight(){
        return myImage.getBoundsInLocal().getHeight();
    }

    /**
     * gets Y direction velocity
     * @return Y velocity
     */
    public double getYVelocity() {
        return myYVelocity;
    }

    /**
     * sets Y direction velocity
     * @param myYVelocity desired Y direction velocity
     */
    public void setYVelocity(double myYVelocity) {
        this.myYVelocity = myYVelocity;
    }

    /**
     * gets Y direction velocity
     * @return Y velocity
     */
    public double getXVelocity() {
        return myXVelocity;
    }

    /**
     * sets X direction velocity
     * @param myXVelocity desired X direction velocity
     */
    public void setXVelocity(double myXVelocity) {
        this.myXVelocity = myXVelocity;
    }

    /**
     * determines if there is a collision occuring between two Items
     * @param other Item involved in possible collision
     * @return boolean of if the two Items are colliding
     */
    public boolean collidesWith(Item other){
        return this.canSee() && other.canSee() &&
                this.getParentBounds().intersects(other.getParentBounds());
    }

    /**
     * gets x coordinate of ImageView
     * @return x coordinate
     */
    public double getXCoordinate(){
        return myImage.getX();
    }

    /**
     * gets y coordinate of ImageView
     * @return y coordinate
     */
    public double getYCoordinate(){
        return myImage.getY();
    }

    /**
     * sets whether or not ImageView is visible
     * @param visible boolean if ImageView is visible
     */
    public void setCanSee(boolean visible){
        canSee = visible;
        myImage.setVisible(visible);
    }

    /**
     * gets whether or not ImageView is visible
     * @return boolean if ImageView is visible
     */
    public boolean canSee(){
        return canSee;
    }

    /**
     * gets bounds
     * @return bounds
     */
    private Bounds getParentBounds(){
        return myImage.getBoundsInParent();
    }

    /**
     * get ImageView
     * @return ImageView
     */
    public ImageView getImage(){
        return myImage;
    }

    /**
     * set ImageView based on image file
     * @param imageFile name of image file for desired ImageView
     */
    public void setImage(String imageFile){
        myImage.setImage(new Image(this.getClass().getClassLoader().getResourceAsStream((imageFile))));
    }

    /**
     * gets furthest from left side of screen x coordinate of ImageView
     * @return furthest x coordinate
     */
    public double getMaxX(){
       return myImage.getBoundsInLocal().getMaxX();
    }

    /**
     * gets closest to left side of screen x coordinate of ImageView
     * @return closest x coordinate
     */
    public double getMinX(){
        return myImage.getBoundsInLocal().getMinX();
    }

}
