import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

abstract public class GameText {

    private Text myText;
    private Player myPlayer;
    private double size;

    public GameText(double screenSize, Player player) {
        myPlayer = player;
        myText = new Text();
        size = screenSize;
    }

    public Text getText() {
        return myText;
    }

    public Player getPlayer() {
        return myPlayer;
    }

    public double getSize() {
        return size;
    }

        abstract protected void placeText();

        abstract public void updateText();

    }
