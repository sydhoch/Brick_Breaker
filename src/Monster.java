/**
 * @author leahschwartz
 *
 * Throughly experimental class that may be implemented in the future
 * Meant to simulate a little monster that floats across the screen and
 * steals your points if you hit it (unfinished due to time constraints)
 * Dependent on Item class
 */
public class Monster extends Item{

    private static final String MONSTER_IMAGE = "monster-2.gif";
    private static final int STARTING_X_VELOCITY = 100;

    /**
     * Creates monster object which is intended to float across the game screen at some interval of time
     */
    public Monster(){
        setImage(MONSTER_IMAGE);
        setCanSee(true);
        setSize(getWidth() /10, getHeight() / 12);
        setXVelocity(STARTING_X_VELOCITY);
    }

    /**
     * Places monster on far left for maximum attack time before going offscreen
     */
    public void attackScreen(){
        placeItem(0, 250);
    }

}
