import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class StartScreen {

    private Scene myScene;

    public StartScreen(){
        myScene = setUpScreen();
    }

    /**
     * Gets Scene created by BrickConfigurar
     * @return game Scene
     */
    public Scene getScene(){
        return myScene;
    }



    private Scene setUpScreen(){
        Group root = new Group();
        var scene = new Scene(root, 100, 100, Color.BLUEVIOLET);


        return scene;
    }



}
