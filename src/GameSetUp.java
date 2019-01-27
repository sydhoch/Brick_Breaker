import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;

public class GameSetUp {

    private Ball myBall;
    private Paddle myPaddle;
    private Brick[][] myBricks;

    public GameSetUp(){
    }


    public Scene setGameStage (int width, int height, Paint background) {
        // create one top level collection to organize the things in the scene
        var root = new Group();
        // create a place to see the shapes
        var scene = new Scene(root, width, height, background);

        myBall = new Ball();
        myBall.getView().setX(width / 2 - myBall.getView().getBoundsInLocal().getWidth() / 2);
        myBall.getView().setY(height / 2 - myBall.getView().getBoundsInLocal().getHeight() / 2);
        root.getChildren().add(myBall.getView());

        myPaddle = new Paddle();
        myPaddle.getView().setX(width / 2 - myPaddle.getView().getBoundsInLocal().getWidth() / 2);
        myPaddle.getView().setY(height - myPaddle.getView().getBoundsInLocal().getHeight());
        root.getChildren().add(myPaddle.getView());

        myBricks = createBrickArray();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                root.getChildren().add(myBricks[i][j].getView());
            }
        }

        scene.setOnKeyPressed(key -> myPaddle.handleSideKeyInput(key.getCode()));

        return scene;
    }

    public void step (double elapsedTime) {
        myBall.move(elapsedTime);
        checkBallBrickCollision();
    }


    private Brick[][] createBrickArray(){
        myBricks = new Brick[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                Brick myBrick = new Brick(1);
                //myBrick.placeBrick(width - myBrick.getView().getBoundsInLocal().getWidth() / 2, height / 2 - myBrick.getView().getBoundsInLocal().getHeight() / 2);
                myBrick.placeBrick(i, j);
                myBricks[i][j] = myBrick;
            }
        }
        return myBricks;
    }

    private void checkBallBrickCollision(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (myBall.getView().getBoundsInParent().intersects(myBricks[i][j].getView().getBoundsInParent())) {
                    myBricks[i][j].decreaseHealth();
                }
            }
        }
    }


}
