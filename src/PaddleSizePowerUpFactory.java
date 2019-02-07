import javafx.scene.Group;

import java.util.ArrayList;

public class PaddleSizePowerUpFactory {

    public PowerUp create(Group root, Paddle paddle, Ball ball, ArrayList<ArrayList<Brick>> bricks){
        return new PowerUp(root, paddle, ball, bricks);
    }

}
