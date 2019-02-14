import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * @author leahschwartz
 *
 * Handles text display for each level within a game
 * Inherits from abstract GameText class
 *
 */
public class LevelText extends GameText {

    private static final int LEVEL_FONT_SIZE = 20;
    private static final Color newLevelColor = Color.BLACK;
    private static final String LEVEL_TEXT_START = "Level ";
    private static final String LEVEL_TEXT_END = "! Press Space To Start ";


    /**
     * Creates text display item which displays the level of the player and instructs them to start
     * the level by pressing space bar
     *
     * @param screenSize size of screen
     * @param player object representing and maintaining player-related information
     */
    public LevelText(double screenSize, Player player){
        super(screenSize, player);
        getText().setFill(newLevelColor);
        getText().setFont(new Font(LEVEL_FONT_SIZE));
        updateText();
        placeText();
    }


    protected void placeText() {
        getText().setX(getSize() / 2 - (getText().getBoundsInLocal().getWidth() / 2));
        getText().setY(getSize() - getSize() / 4);
    }

    /**
     * Updates text message by setting it to reflect current level as represented by Player object
     */
    public void updateText() {
        getText().setVisible(true);
        getText().setText(LEVEL_TEXT_START + getPlayer().getLevel() + LEVEL_TEXT_END);
        placeText();
    }




}
