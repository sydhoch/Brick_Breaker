/**
 * @author leahschwartz
 *
 * Throughly experimental class that may be implemented in the future
 * Meant to simulate a little monster that floats across the screen and
 * steals your points if you hit him
 *
 */
public class Monster extends Item{

    private static final String MONSTER_IMAGE = "monster-2.gif";
    private static final int STARTING_X_VELOCITY = 100;

    public Monster(){
        setImage(MONSTER_IMAGE);
        setCanSee(true);
        setSize(getWidth() /10, getHeight() / 12);
        setXVelocity(STARTING_X_VELOCITY);
    }

    public void attackScreen(){
        placeItem(0, 250);
    }

}
