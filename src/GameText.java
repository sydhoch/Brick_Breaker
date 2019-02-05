import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

abstract public class GameText {

        private Text myText;
        private Player myPlayer;
        public GameText(double screenSize, Player player){
            myPlayer = player;
            myText = new Text();
            updateText();
            placeText(screenSize);
        }

        public Text getText(){
            return myText;
        }

        public Player getPlayer(){
            return myPlayer;
        }

        abstract protected void placeText(double screenSize);

        abstract public void updateText();

    }
