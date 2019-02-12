import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StatusText extends GameText {

    private static final int STATUS_FONT_SIZE = 20;
    private int highScore;

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
        String highScore = "High Score: " + currentHighScore + "\n";
        String livesLeft = "Lives Left: "+ getPlayer().getLives() +"\n";
        String score = "Score: " + getPlayer().getScore() + "\n";
        String level = "Level: " + getPlayer().getLevel();
        getText().setText(highScore+livesLeft+score+level);
    }




}
