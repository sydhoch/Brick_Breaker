import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Status {
    private Text statusText;

    public Status(int screenSize){
        statusText = new Text();
        statusText.setFill(Color.WHITE);
        statusText.setFont(new Font(20));
        placeStatusText(screenSize);
    }

    public Text getStatusText(){
        return statusText;
    }

    private void placeStatusText(int screenSize){
        statusText.setX(screenSize - (screenSize / 4));
        statusText.setY(screenSize - (screenSize / 6));
    }

    public void updateStatusText(int currentLives, int currentLevel, int currentScore){
        String livesleft = "Lives Left: "+ currentLives +"\n";
        String score= "Score: \n";
        String level = "Level: " + currentLevel;
        statusText.setText(livesleft+score+level);
    }




}
