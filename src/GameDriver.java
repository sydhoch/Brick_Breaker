import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameDriver extends Application{

    private static final String TITLE = "BRICK BREAKER!";


    public void start (Stage stage) {
        StartScreen startSetUp = new StartScreen(500);
        Scene start = startSetUp.getMyScene();
        stage.setScene(start);
        start.setOnKeyPressed(key-> handleStartScreenKeys(stage));
        stage.setTitle(TITLE);
        stage.show();
    }

    private void handleStartScreenKeys(Stage stage){
        GamePlay gamePlayer = new GamePlay();
        Scene myScene = gamePlayer.getScene();
        stage.setScene(myScene);
    }

    public static void main(String[] args){
        launch(args);
    }
}
