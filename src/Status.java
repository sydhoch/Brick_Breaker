import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Status {
    private Text statusText;
    private static final int FONT_SIZE = 20;


    public Status(double screenSize){
        statusText = new Text();
        statusText.setFill(Color.BLUEVIOLET);
       // statusText.setFont(new Font(screenSize / 25));
        statusText.setFont(new Font(FONT_SIZE));
        updateStatusText(0, 0, 0);
        placeStatusText(screenSize);
    }

    public Text getStatusText(){
        return statusText;
    }

    private void placeStatusText(double screenSize){
        statusText.setX(statusText.getBoundsInLocal().getWidth() / 10);
        statusText.setY(screenSize - statusText.getBoundsInLocal().getHeight());
    }

    public void updateStatusText(int currentLives, int currentLevel, int currentScore){
        String livesleft = "Lives Left: "+ currentLives +"\n";
        String score= "Score: " + currentScore + "\n";
        String level = "Level: " + currentLevel;
        statusText.setText(livesleft+score+level);
    }




}
