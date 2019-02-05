import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

public class GameDriver extends Application{

    //public static final Paint BACKGROUND = Color.WHITESMOKE;
    public static final String TITLE = "Hello JavaFX";
    public static final int FRAMES_PER_SECOND = 60;
    //static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private String test = null;


    public void start (Stage stage) {
        // myScene.setOnKeyPressed(key -> gamePlayer.handleCheatKeys(key.getCode(), SECOND_DELAY));
       // myScene.setOnKeyPressed(key -> gamePlayer.handleAllKeys(key.getCode(), SECOND_DELAY));

        //make a start screen
        StartScreen startSetUp = new StartScreen(500);
        Scene start = startSetUp.getMyScene();

        //show start screen
        stage.setScene(start);

        //stage.show();
        start.setOnKeyPressed(key-> handleStartScreenKeys(key.getCode(),stage));


        stage.setTitle(TITLE);
        stage.show();

    }

    public void handleStartScreenKeys(KeyCode code, Stage stage){
        if(code.equals(code.COMMA)){
            test = "test1.txt";
        }
        if(code.equals(code.PERIOD)){
            test = "test2.txt";

        }
        if(code.equals(code.SLASH)){
            test = "test3.txt";
        }
        GamePlay gamePlayer = new GamePlay(SECOND_DELAY, test);
        Scene myScene = gamePlayer.getScene();
        stage.setScene(myScene);
    }

    public static void main(String[] args){
        launch(args);
    }
}
