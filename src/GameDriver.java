import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;





public class GameDriver extends Application{

    public static final Paint BACKGROUND = Color.WHITESMOKE;
    public static final String TITLE = "Hello JavaFX";
    public static final int FRAMES_PER_SECOND = 60;
    static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;



    public void start (Stage stage) {
        // attach scene to the stage and display it

        GamePlay gamePlayer = new GamePlay(SECOND_DELAY);
        Scene myScene = gamePlayer.getScene();

        // myScene.setOnKeyPressed(key -> gamePlayer.handleCheatKeys(key.getCode(), SECOND_DELAY));
        myScene.setOnKeyPressed(key -> gamePlayer.handleAllKeys(key.getCode(), SECOND_DELAY));
        //make a start screen
        StartScreen startSetUp = new StartScreen(myScene.getHeight());
        Scene start = startSetUp.getMyScene();

        //show start screen
        stage.setScene(start);
        //stage.setTitle("Hi");s
        //stage.show();
        start.setOnKeyPressed(key-> stage.setScene(myScene));


        //show game screen
        //stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();


    }


    public static void main(String[] args){
        launch(args);
    }
}
