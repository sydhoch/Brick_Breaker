import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * @author leahschwartz and sydneyhochberg
 *
 * Main driver for running complete game
 * Dependent on GamePlay and StartScreen classes
 */

public class GameDriver extends Application{

    private static final String TITLE = "BRICK BREAKER!";

    /**
     * Sets up and makes game run
     * @param stage stage object to display game scenes on
     */
    public void start (Stage stage) {
        StartScreen startSetUp = new StartScreen(500);
        Scene start = startSetUp.getMyScene();
        stage.setScene(start);
        start.setOnKeyPressed(key-> switchToGameScreen(stage));
        stage.setTitle(TITLE);
        stage.show();
    }

    private void switchToGameScreen(Stage stage){
        GamePlay gamePlayer = new GamePlay();
        Scene myScene = gamePlayer.getScene();
        stage.setScene(myScene);
    }


    public static void main(String[] args){
        launch(args);
    }
}
