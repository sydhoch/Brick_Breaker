import java.util.Timer;
import java.util.TimerTask;
/**
 * @author leahschwartz
 * Abstract class to represent a powerup, which causes some special effect if caught by the paddle
 * Powerup stays in effect for TOTAL_TIME and then is reversed
 */
public abstract class PowerUp extends Item{

    private static final int FALLING_Y_VELOCITY = 100;
    private static int TOTAL_TIME = 10000;
    private boolean isActive;

    /**
     * creates powerup which can fall down screen, be caught, and activate to help player in someway
     * deactivates automatically when TOTAL_TIME is up
     */
    public PowerUp(){
        setPowerUpImage();
        setCanSee(false);
        setIsActive(false);
    }

    /**
     * sets Powerup's ImageView to appropriate image
     */
    protected abstract void setPowerUpImage();

    /**
     * Causes powerup to fall down toward bottom of screen and become visible
     */
    public void startFalling() {
        setYVelocity(FALLING_Y_VELOCITY);
        setCanSee(true);
    }

    /**
     * Activates powerup by initiating some kind of special effect based on powerup type and sets timer after which
     * powerup will be deactivated and the special effect will be reversed back to normal
     *
     * @param ball player's ball
     * @param paddle in-game paddle for hitting ball
     * @param player object representing and maintaining player-related information
     */
    public void activate(Paddle paddle, Ball ball, Player player){
        setCanSee(false);
        setIsActive(true);
        startTimer(paddle, ball, player);
    }

    private void startTimer(Paddle paddle, Ball ball, Player player){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                deactivate(paddle, ball, player);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, TOTAL_TIME);
    }

    /**
     * Deactivates powerup by doing reverse special effect based on powerup type
     *
     * @param ball player's ball
     * @param paddle in-game paddle for hitting ball
     * @param player object representing and maintaining player-related information
     */
    public abstract void deactivate(Paddle paddle, Ball ball, Player player);

    protected void setIsActive(boolean active){
        isActive = active;
    }

    public boolean isActive(){
        return isActive;
    }

    /**
     * takes powerup out of game by deactivating it and making image invisible
     * @param ball player's ball
     * @param paddle in-game paddle for hitting ball
     * @param player object representing and maintaining player-related information
     */
    public void destroy(Paddle paddle, Ball ball, Player player) {
        setCanSee(false);
        deactivate(paddle, ball, player);
    }
}
