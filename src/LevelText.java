import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LevelText extends GameText {

    private static final int LEVEL_FONT_SIZE = 20;
    private static final String loseMessage = "You Lose :(";
    private static final String winMessage = "You Win!";
    private static final Color loseColor = Color.RED;
    private static final Color winColor = Color.GREEN;
    private static final Color newLevelColor = Color.BLACK;

    public LevelText(double screenSize, Player player){
        super(screenSize, player);
        getText().setFill(newLevelColor);
        getText().setFont(new Font(LEVEL_FONT_SIZE));
        updateText();
        placeText();
    }

    @Override
    protected void placeText() {
        getText().setX(getSize() / 2 - (getText().getBoundsInLocal().getWidth() / 2));
        getText().setY(getSize() - getSize() / 4);
    }

    @Override
    public void updateText() {
        getText().setVisible(true);
        if (getPlayer().getLives() == 0){
            changeMessage(loseColor, loseMessage);
        }
        else if (getPlayer().getLevel() == getPlayer().getLastLevel()){
            changeMessage(winColor, winMessage);
        }
        else {
            changeMessage(newLevelColor, "Level " + getPlayer().getLevel() + "! Press Space To Start");
        }
        placeText();
    }

    private void changeMessage(Color color, String message){
        getText().setText(message);
        getText().setFill(color);
    }

    public void disappear(){
        getText().setVisible(false);
    }

}
