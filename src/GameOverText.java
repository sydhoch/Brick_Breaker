import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * @author leahschwartz
 *
 * Handles text display for end of a game
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
