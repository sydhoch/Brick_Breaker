/**
 * @author leahschwartz
 *
 * Abstract class to represent a power-up factory in order to support the random creation of power-ups
 */
abstract public class PowerUpFactory {

    /**
     * Creates a new power-up
     *
     * @return PowerUp
     */
    public abstract PowerUp create();
}
