import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

public class GameDriver extends Application{

    //public static final Paint BACKGROUND = Color.WHITESMOKE;
    public static final String TITLE = "Hello JavaFX";
  //  public static final int FRAMES_PER_SECOND = 60;
    //static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    //public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private String test = null;
    private int testLevel = 2;


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
        String testbeginning = "test";
        String testnum="";
        String level ="_level";
        String levelnum= Integer.toString(testLevel);
        boolean testExists=false;
        if(code.equals(code.COMMA)){
            testnum = Integer.toString(1);
            testExists=true;
            //test = "test1_level1.txt";
        }
        if(code.equals(code.PERIOD)){
            testnum = Integer.toString(2);
            testExists=true;
            //test = "test2_level1.txt";

        }
        if(code.equals(code.SLASH)){
            testnum = Integer.toString(3);
            testExists=true;
            //test = "test3_level1.txt";
        }
        if(testExists){
            test = testbeginning + testnum + level + levelnum + ".txt";
            //System.out.println(test);
        }
        GamePlay gamePlayer = new GamePlay(test);
        Scene myScene = gamePlayer.getScene();
        stage.setScene(myScene);
    }

    public static void main(String[] args){
        launch(args);
    }
}
