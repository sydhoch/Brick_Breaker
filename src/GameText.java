import javafx.scene.text.Text;
/**
 * @author leahschwartz
 *
 * Abstract class for any in-game text
 * Dependent on Player class
 *
 */
abstract public class GameText {

    private Text myText;
    private Player myPlayer;
    private double size;

    /**
     * creates a text display that can be placed, updated, and altered
     * @param screenSize size of screen
     * @param player object representing and maintaining player-related information
     */
    public GameText(double screenSize, Player player) {
        myPlayer = player;
        myText = new Text();
        size = screenSize;
    }

    protected Text getText() {
        return myText;
    }

    protected Player getPlayer() {
        return myPlayer;
    }

    protected double getSize() {
        return size;
    }

    /**
     * makes text invisible
     */
    public void disappear(){
        getText().setVisible(false);
    }

    abstract protected void placeText();

    /**
     * updates text to reflect current events in the game
     */
    abstract public void updateText();

    }
