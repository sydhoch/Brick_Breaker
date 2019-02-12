import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * @author leahschwartz
 *
 * Handles status text display which gives constantly updating data to player about high score, lives, score, and level
 *
 */

public class StatusText extends GameText {

    private static final int STATUS_FONT_SIZE = 20;
    private static final String HIGH_SCORE_LABEL = "High Score: ";
    private static final String LIVES_LEFT_LABEL = "Lives Left: ";
    private static final String SCORE_LABEL = "Score: ";
    private static final String LEVEL_LABEL = "Level: ";

    public StatusText(double screenSize, Player player){
        super(screenSize, player);
        getText().setFill(Color.BLUEVIOLET);
        getText().setFont(new Font(STATUS_FONT_SIZE));
        updateText();
        placeText();
    }

    @Override
    protected void placeText(){
        getText().setX(getText().getBoundsInLocal().getWidth() / 10);
        getText().setY(getSize() - getText().getBoundsInLocal().getHeight());
    }

    @Override
    public void updateText(){
        int currentHighScore = getPlayer().getCurrentHighScore();
        String highScore = HIGH_SCORE_LABEL + currentHighScore + "\n";
        String livesLeft = LIVES_LEFT_LABEL + getPlayer().getLives() +"\n";
        String score = SCORE_LABEL + getPlayer().getScore() + "\n";
        String level = LEVEL_LABEL + getPlayer().getLevel();
        getText().setText(highScore+livesLeft+score+level);
    }




}
