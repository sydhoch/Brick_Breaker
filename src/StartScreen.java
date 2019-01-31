import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class StartScreen {

    private Scene myScene;
    private Group root;

    public StartScreen(double screenSize){
        myScene = setUpScreen(screenSize);
    }


    public Scene setUpScreen(double screenSize){
        root = new Group();
        Text title = getTitle(screenSize);
        Text body1 = getBody(screenSize, title, "Objective: Destroy as many bricks as possible.");
        root.getChildren().addAll(title, body1);
        var scene = new Scene(root, screenSize, screenSize, Color.WHITE);

        return scene;

    }

    public Scene getMyScene(){
        return myScene;
    }

    private Text getTitle(double screenSize) {
        Text title = new Text();
        title.setFill(Color.BLACK);
        title.setFont(new Font(screenSize / 25));
        title.setText("Breakout!");
        title.setX((screenSize - title.getBoundsInLocal().getWidth()) / 2);
        title.setY(0+ title.getBoundsInLocal().getHeight());
        return title;
    }

    private Text getBody(double screenSize, Text before, String words) {
        Text title = new Text();
        title.setFill(Color.BLACK);
        title.setFont(new Font(screenSize / 30));
        title.setText(words);
        title.setX((screenSize - title.getBoundsInLocal().getWidth()) / 2);
        title.setY(0+ title.getBoundsInLocal().getHeight() + before.getBoundsInLocal().getHeight());
        return title;
    }


    /*
    public void handleCheatKeys(KeyCode code, double elapsedTime){
            if (code== KeyCode.SPACE){
                myPlayer.gainLife();
            }
            if (code.isArrowKey()) {
                myPaddle.handleSideKeyInput(code, myScene.getWidth(), elapsedTime);
            }
    }
    */




}
