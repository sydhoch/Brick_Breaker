import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * @author leahschwartz
 *
 * Handles text display for end of a game
 * Inherits from GameText
 * Dependent on GameText and Player classes
 *
 */
public class GameOverText extends GameText {

    private static final int GAMEOVER_FONT_SIZE = 25;
    private static final String gameOverInstruction = "Press 'N' For New Game!";
    private static final String loseMessage = "You Lose :( " + gameOverInstruction;
    private static final String winMessage = "You Win! " + gameOverInstruction;
    private static final Color loseColor = Color.RED;
    private static final Color winColor = Color.GREEN;
    private static final Color neutralColor = Color.BLUE;

    /**
     * Creates GameOverText to display message when game ends
     *
     * @param screenSize size of screen
     * @param player object representing and maintaining player-related information
     */
    public GameOverText(double screenSize, Player player){
        super(screenSize, player);
        getText().setFont(new Font(GAMEOVER_FONT_SIZE));
        updateText();
        placeText();
    }


    protected void placeText() {
        getText().setX(getSize() / 2 - (getText().getBoundsInLocal().getWidth() / 2));
        getText().setY(getSize() - getSize() / 3);
    }


    /**
     * Updates display to give appropriate end of game message
     * assumes player lost if they ran out of lives and that they won if they did not run out of lives and are on
     * last level or displays a neutral message if neither holds true
     */
    public void updateText() {
        getText().setVisible(true);
        if (getPlayer().getLives() == 0){
            changeMessage(loseColor, loseMessage);
        }
        else if (getPlayer().getLevel() == getPlayer().getLastLevel()){
            changeMessage(winColor, winMessage);
        }
        else {
            changeMessage(neutralColor, gameOverInstruction);
        }
        placeText();
    }

    private void changeMessage(Color color, String message){
        getText().setFill(color);
        getText().setText(message);
    }
}
