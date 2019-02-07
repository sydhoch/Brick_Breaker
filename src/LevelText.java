import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LevelText extends GameText {

    private static final int LEVEL_FONT_SIZE = 20;
    private static final Color newLevelColor = Color.BLACK;

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


    public void updateText() {
        getText().setVisible(true);
        getText().setText("Level " + getPlayer().getLevel() + "! Press Space To Start");
        placeText();
    }




}
