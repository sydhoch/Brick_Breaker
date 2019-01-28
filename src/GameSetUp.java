import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.text.*;



public class GameSetUp {

    private int size;
    private Group root;
    private Scene myScene;
    private int numBrickCols;
    private int numBrickRows;
    private int BricksRemaining;
    private int lives=1; //chose to have 3 lives
    private int i=0;


    private ArrayList<ArrayList<Brick>> myBricks;
    private Ball myBall;
    private Paddle myPaddle;
    private Player myPlayer;
   // private Label statusLabel;


    /**
     * Sets up a scene with paddle, ball, and blocks configured by a text file
     * @param fileName name of block configuring file
     * @param background color of screen background
     */
    public GameSetUp(String fileName, Paint background){
        fillBrickList(readBrickFile(fileName), numBrickCols, numBrickRows);
        root = new Group();
        myScene = setGameStage(background);
        PlayerSetUp();
        bricks();
        // create one top level collection to organize the things in the scene

    }

    /**
     * Gets Scene created by GameSetUp
     * @return game Scene
     */
    public Scene getScene(){
        return myScene;
    }

    /**
     * Changes properties of objects on screen to make them seem animated
     * @param elapsedTime how often method is run
     */
    public void step (double elapsedTime) {
        myBall.move(elapsedTime);
        myBall.bounce(myScene.getWidth(),myScene.getHeight());
        checkBallHitsPaddle();
        checkBallBrickCollision();
        if(myBall.ballFell(myScene.getHeight())){
             myPlayer.loseLife();
             if(myPlayer.getLives()>0){
                 ballInit();
                 centerPaddle();
             }
             else{
                 GameOver();
             }
        }
    }
    private void bricks(){
        for (ArrayList<Brick> brickRow : myBricks){
            for (Brick myBrick : brickRow) {
                BricksRemaining++;
            }
        }
    }
    private void GameOver(){
        Text t = new Text();
        t.setFont(new Font(20));
        String gameover="";
        if(i==0){
            i=1;
            for (ArrayList<Brick> brickRow : myBricks){
                for (Brick myBrick : brickRow) {
                    if(myBrick.getHealth()==0){
                        BricksRemaining--;
                    }
                }
            }
            if(BricksRemaining>0){
                gameover = "You Lost :(";
                System.out.println(BricksRemaining);
            }
            else{
                gameover = "You Won!";
                System.out.println(BricksRemaining);
            }
        }
        t.setText(gameover);
        root.getChildren().add(t);

    }

    private void PlayerSetUp(){
        myPlayer = new Player(lives);
    }

    /**
     * Reads information from block configuration text file, including size of screen, number of block rows, number of
     * block columns, and configuration of blocks
     * @param fileName name of block configuring file
     * @return configuration of blocks onscreen represented by ints
     */
    private int[][] readBrickFile(String fileName){
        Scanner scannie = new Scanner(Main.class.getClassLoader().getResourceAsStream(fileName));
        size = scannie.nextInt();
        numBrickCols = scannie.nextInt();
        numBrickRows = scannie.nextInt();
        int[][] brickConfigs = new int[numBrickCols][numBrickRows];
        for (int i = 0; i < numBrickRows; i ++){
            for (int j = 0; j < numBrickCols; j ++){
                brickConfigs[i][j] = scannie.nextInt();
            }
        }
        return brickConfigs;
    }

    /**
     * Fills myBricks with Brick objects with health and placement as specified by brickConfigs
     * @param brickConfigs represents configuration of blocks onscreen
     * @param numBrickCols number of columns of bricks
     * @param numBrickRows number of rows of bricks
     */
    private void fillBrickList(int[][] brickConfigs, int numBrickCols, int numBrickRows) {
        myBricks = new ArrayList<>();
        for (int i = 0; i < numBrickRows; i++) {
            ArrayList<Brick> brickRow = new ArrayList<>();
            for (int j = 0; j < numBrickCols; j++) {
                if (brickConfigs[i][j] > 0) {
                    Brick myBrick = new Brick(calcBrickWidth(numBrickCols), calcBrickHeight(numBrickRows), brickConfigs[i][j]);
                    myBrick.getView().setX(i * calcBrickWidth(numBrickCols));
                    myBrick.getView().setY(j * calcBrickHeight(numBrickRows));
                    brickRow.add(myBrick);
                }
            }
            myBricks.add(brickRow);
        }
    }

    private int calcBrickHeight(int numBrickRows){
        return size / numBrickRows / 2;
    }

    private int calcBrickWidth(int numBrickCols){
        return size / numBrickCols;
    }

    //////////////////////////////////////////////////////////////////////////
    private Scene setGameStage (Paint background) {
        // create a place to see the shapes
        var scene = new Scene(root, size, size, background);

        ballInit();

        myPaddle = new Paddle();
        centerPaddle();
        root.getChildren().add(myPaddle.getView());

        for (ArrayList<Brick> brickRow : myBricks){
            for (Brick myBrick : brickRow){
                root.getChildren().add(myBrick.getView());
            }
        }

        scene.setOnKeyPressed(key -> myPaddle.handleSideKeyInput(key.getCode()));

        return scene;
    }

    private void centerPaddle(){
        myPaddle.getView().setX(size / 2 - myPaddle.getView().getBoundsInLocal().getWidth() / 2);
        myPaddle.getView().setY(size - myPaddle.getView().getBoundsInLocal().getHeight());
    }

    private void ballInit(){
        myBall = new Ball();
        myBall.getView().setX(size / 2 - myBall.getView().getBoundsInLocal().getWidth() / 2);
        myBall.getView().setY(size / 2 - myBall.getView().getBoundsInLocal().getHeight() / 2);
        root.getChildren().add(myBall.getView());
    }

    private void setStatusDisplay(){
        Text t = new Text();
        t.setFont(new Font(20));
        String livesleft = "Lives Left: "+myPlayer.getLives()+"\n";
        String score= "Score: \n";
        String level = "Level: 1";
        t.setText(livesleft+score+level);
    }
    /////////////////////////////////////////////////////////////////////////////


    private void checkBallBrickCollision(){
        for (ArrayList<Brick> brickRow : myBricks){
            for (Brick myBrick : brickRow){
                if (myBrick.getHealth()>0 && myBall.getView().getBoundsInParent().intersects(myBrick.getView().getBoundsInParent())) {
                    myBrick.decreaseHealth();
                    myBall.BounceOff();
                }

            }
        }
    }

    private void checkBallHitsPaddle(){
        if(myBall.getView().getBoundsInLocal().intersects(myPaddle.getView().getBoundsInLocal())){
            myBall.BounceOffPad();
        }
    }




}
