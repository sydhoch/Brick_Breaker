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
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;



    public void start (Stage stage) {
        // attach scene to the stage and display it

        GamePlay gamePlayer = new GamePlay(SECOND_DELAY);
        Scene myScene = gamePlayer.getScene();

        myScene.setOnKeyPressed(key -> gamePlayer.handleCheatKeys(key.getCode(), SECOND_DELAY));

        //make a start screen
        StartScreen startsetup = new StartScreen(myScene.getHeight());
        Scene start = startsetup.getMyScene();

        //show start screen
        stage.setScene(start);
        //stage.setTitle("Hi");
        //stage.show();
        start.setOnKeyPressed(key-> stage.setScene(myScene));


        //show game screen
        //stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();

        // attach "game loop" to timeline to play it
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> gamePlayer.step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
        if (animation.getStatus() == Animation.Status.PAUSED){


        }
        myScene.setOnKeyPressed(key -> animation.play());

    }


    public static void main(String[] args){
        launch(args);
    }
}
