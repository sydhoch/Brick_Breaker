import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;





public class GameDriver extends Application{

    public static final int SIZE = 400;
    public static final Paint BACKGROUND = Color.CYAN;
      public static final String TITLE = "Hello JavaFX";
      public static final int FRAMES_PER_SECOND = 60;
      public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
      public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
      GameSetUp makeGame;

//    private Ball myBall;
//    private Paddle myPaddle;
//    private Brick[][] myBricks;

    public void start (Stage stage) {
        // attach scene to the stage and display it
        makeGame = new GameSetUp("example.txt", BACKGROUND);
        stage.setScene(makeGame.getScene());
        stage.setTitle(TITLE);
        stage.show();
        // attach "game loop" to timeline to play it
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> makeGame.step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

//    private Scene setupGame (int width, int height, Paint background) {
//        // create one top level collection to organize the things in the scene
//        var root = new Group();
//        // create a place to see the shapes
//        var scene = new Scene(root, width, height, background);
//
//        myBall = new Ball();
//        myBall.getView().setX(width / 2 - myBall.getView().getBoundsInLocal().getWidth() / 2);
//        myBall.getView().setY(height / 2 - myBall.getView().getBoundsInLocal().getHeight() / 2);
//        root.getChildren().add(myBall.getView());
//
//        myPaddle = new Paddle();
//        myPaddle.getView().setX(width / 2 - myPaddle.getView().getBoundsInLocal().getWidth() / 2);
//        myPaddle.getView().setY(height - myPaddle.getView().getBoundsInLocal().getHeight());
//        root.getChildren().add(myPaddle.getView());
//
//        myBricks = createBrickArray();
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                root.getChildren().add(myBricks[i][j].getView());
//            }
//        }
//
//        scene.setOnKeyPressed(key -> myPaddle.handleSideKeyInput(key.getCode()));
//
//        return scene;
//    }
//
//    private Brick[][] createBrickArray(){
//        myBricks = new Brick[10][10];
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//
//                Brick myBrick = new Brick(1);
//                //myBrick.placeBrick(width - myBrick.getView().getBoundsInLocal().getWidth() / 2, height / 2 - myBrick.getView().getBoundsInLocal().getHeight() / 2);
//                myBrick.placeBrick(i, j);
//                myBricks[i][j] = myBrick;
//            }
//        }
//        return myBricks;
//    }
//
//    private void checkBallBrickCollision(){
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                if (myBall.getView().getBoundsInParent().intersects(myBricks[i][j].getView().getBoundsInParent())) {
//                    myBricks[i][j].decreaseHealth();
//                }
//            }
//        }
//    }
//
//
//    private void step (double elapsedTime) {
//        myBall.move(elapsedTime);
//        checkBallBrickCollision();
//    }

    public static void main(String[] args){
        launch(args);
    }
}
