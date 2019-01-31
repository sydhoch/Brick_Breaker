import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class StartScreen {

    private Scene myScene;
    private int size;

    public StartScreen(){
        myScene = setUpScreen(size);
    }


    private Scene setUpScreen(int screenSize){
        Group root = new Group();
        var scene = new Scene(root, 100, 100, Color.BLUEVIOLET);

        Text title = getTitle(screenSize);


//        gameOverText.setText(message);
//        gameOverText.setFont(new Font(20));
//        gameOverText.setFill(color);
//        gameOverText.setX(size / 2 - (gameOverText.getBoundsInLocal().getWidth() / 2));
//        gameOverText.setY(size / 2);

        return scene;
    }

    private Text getTitle(int screenSize) {
        Text title = new Text();
        title.setFill(Color.BLACK);
        title.setFont(new Font(screenSize / 25));
        title.setFont(new Font(20));
        title.setX(screenSize / 2);
        title.setY(screenSize - title.getBoundsInLocal().getHeight());
        title.setText("");
        return title;
    }



}
