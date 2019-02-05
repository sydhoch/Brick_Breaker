import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StatusText extends GameText {
    private static final int FONT_SIZE = 20;


    public StatusText(double screenSize, Player player){
        super(screenSize, player);
        getText().setFill(Color.BLUEVIOLET);
        getText().setFont(new Font(FONT_SIZE));
    }

    @Override
    protected void placeText(double screenSize){
        getText().setX(getText().getBoundsInLocal().getWidth() / 10);
        getText().setY(screenSize - getText().getBoundsInLocal().getHeight());
    }

    public void updateText(){
        String livesLeft = "Lives Left: "+ getPlayer().getLives() +"\n";
        String score = "Score: " + getPlayer().getScore() + "\n";
        String level = "Level: " + getPlayer().getLevel();
        getText().setText(livesLeft+score+level);
    }




}
