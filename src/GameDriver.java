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
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;



    public void start (Stage stage) {
        // attach scene to the stage and display it
       // BrickConfigurar gameMaker = new BrickConfigurar("example.txt", BACKGROUND, SECOND_DELAY);
        GamePlay gamePlayer = new GamePlay(SECOND_DELAY);
        //Scene myScene = gameMaker.getScene();
        Scene myScene = gamePlayer.getScene();


        myScene.setOnKeyPressed(key -> gamePlayer.handleCheatKeys(key.getCode(), SECOND_DELAY));

        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();

        // attach "game loop" to timeline to play it
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> gamePlayer.step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }


    public static void main(String[] args){
        launch(args);
    }
}
