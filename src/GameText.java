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

    protected double getSize() {
        return size;
    }

    public void disappear(){
        getText().setVisible(false);
    }

    abstract protected void placeText();

    abstract public void updateText();

    }
