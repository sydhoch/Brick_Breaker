import javafx.scene.image.ImageView;

abstract public class Item extends ImageView{
    private double myYVelocity;
    private double myXVelocity;

    public void placeItem(double x, double y){
        setX(x);
        setY(y);
    }

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

    public double getWidth(){
        return getBoundsInLocal().getWidth();
    }

    public double getHeight(){
        return getBoundsInLocal().getHeight();
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
