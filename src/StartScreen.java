import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @author sydneyhochberg
 * Makes the splash screen that pops up before the game and explains the instructions.
 *
 */
public class StartScreen {

    private Scene myScene;
    private Group root;

    private static final String BREAKOUT_TITLE = "Breakout!";

    public StartScreen(double screenSize){
        myScene = setUpScreen(screenSize);
    }

    /**
     * Sets up the splash screen scene with all needed text
     * @param screenSize size of screen
     * @return scene representing start screen
     */
    public Scene setUpScreen(double screenSize){
        root = new Group();
        Text title = getTitle(screenSize);
        Text body = getBody(screenSize, title, "Objective: Destroy as many bricks as possible." +
                "\nUse arrow keys to move paddle to prevent ball from falling." +
                "\n\nPress any key to start a new game." +
                "\n\nPress R to reset the ball's position." +
                "\nPress L to add lives." +
                "\nPress number keys to skip to level numbers." +
                "\n\nUse , . and / during a level to start a test. " +
                "\nSee ReadMe for more info on testing.");
        body.setX(body.getBoundsInLocal().getWidth() / 10);
        body.setY(body.getBoundsInLocal().getHeight()/2);
        root.getChildren().addAll(title, body);
        var scene = new Scene(root, screenSize, screenSize, Color.THISTLE);
        return scene;
    }
    /*
    public Scene setUpScreen(double screenSize){

        //Text title = getTitle(screenSize);
        //Text body1 = getBody(screenSize, title, "Objective: Destroy as many bricks as possible.");
        //Text body2 = getBody2(screenSize, body1, "Press , . or / to run tests. Press any other key to start a game.");
        //box.getChildren().addAll(title, body1, body2);


        //Label label1 = new Label("Enter Your Name:");
        //TextField text= new TextField();
        //HBox box = new HBox();
        //box.getChildren().add();
        //box.setSpacing(10);
        //root.getChildren().add(box);


        TextField myTextField = new TextField();
        HBox hbox = new HBox();
        hbox.getChildren().add(myTextField);
        HBox.setHgrow(myTextField, Priority.ALWAYS);

        Scene scene = new Scene(hbox, screenSize, screenSize, Color.WHITE);
        return scene;
    }
    */

    /**
     *
     * @returns the scene for the splash screen
     */
    public Scene getMyScene(){
        return myScene;
    }

    private Text getTitle(double screenSize) {
        Text title = new Text();
        title.setFill(Color.BLACK);
        title.setFont(new Font(screenSize / 25));
        title.setText(BREAKOUT_TITLE);
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
    private void blah(Group root){
        Label label = new Label("Enter Your Name:");
        TextField textField = new TextField();
        HBox box = new HBox();
        box.getChildren().addAll(label,textField);
        box.setSpacing(10);
        root.getChildren().add(box);
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
            System.out.println(test);
        }
        GamePlay gamePlayer = new GamePlay(test);
        Scene myScene = gamePlayer.getScene();
        stage.setScene(myScene);
    }
    */

}

