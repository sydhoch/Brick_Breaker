import javafx.scene.Group;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class PowerUp extends Item{

    private static final int FALLING_Y_VELOCITY = 100;
    private static int TOTAL_TIME = 10000;

    public PowerUp(){
        setPowerUpImage();
        setCanSee(false);
    }

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
     */
    public void activate(){
        setCanSee(false);
        doPower();
        startTimer();
    }

    private void startTimer(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                deactivate();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, TOTAL_TIME);
    }

    protected abstract void doPower();

    protected abstract void deactivate();
}
