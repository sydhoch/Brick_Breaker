import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
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

    private int bricksRemaining;
    private int lives = 3; //chose to have 3 lives
    private int i = 0;
    private Text gameOverText;
    private int levelNum = 1;


    private ArrayList<ArrayList<Brick>> myBricks;
    private Ball myBall;
    private Paddle myPaddle;
    private Player myPlayer;
    private Status myStatus;


    /**
     * Sets up a scene with paddle, ball, and blocks configured by a text file
     * @param fileName name of block configuring file
     * @param background color of screen background
     */
    public GameSetUp(String fileName, Paint background){
        fillBrickList(readBrickFile(fileName), numBrickCols, numBrickRows);
        root = new Group();
        PlayerSetUp();
        myScene = setGameStage(background);
        countBricks();
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
        myStatus.updateStatusText(myPlayer.getLives(), levelNum, 0);
        myBall.move(elapsedTime);
        myBall.bounce(myScene.getWidth(), myScene.getHeight());
        checkBallHitsPaddle();
        checkBallBrickCollision();
        if(myBall.ballFell(myScene.getHeight())){
             myPlayer.loseLife();
             if(myPlayer.getLives()>0){
                 myBall.placeForStart(size);
                 myPaddle.centerPaddle(size);
             }
             else{
                 GameOver();
             }
        }
    }

    private void countBricks(){
        for (ArrayList<Brick> brickRow : myBricks){
            for (Brick myBrick : brickRow) {
                bricksRemaining++;
            }
        }
    }


    private void GameOver(){
        if(i==0){
            i=1;
            for (ArrayList<Brick> brickRow : myBricks){
                for (Brick myBrick : brickRow) {
                    if(myBrick.getHealth()==0){
                        System.out.println(bricksRemaining);
                        bricksRemaining--;
                    }
                }
            }
            if(bricksRemaining >0){
                gameOverText.setText("You Lost :(");
                System.out.println(bricksRemaining);
            }
            else{
                gameOverText.setText("You Won!");
                System.out.println(bricksRemaining);
            }
        }
        gameOverText.setFont(new Font(20));
        gameOverText.setFill(Color.WHITE);
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
     * @param numBrickCols number of columns of countBricks
     * @param numBrickRows number of rows of countBricks
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

    private void createGameOverText(){
        gameOverText = new Text();
        gameOverText = new Text();
        gameOverText.setX(150);
        gameOverText.setY(150);
    }

//    private void createStatusText(){
//        statusText = new Text();
//        statusText.setFill(Color.WHITE);
//        statusText.setX(size - (size / 4));
//        statusText.setY(size - (size / 6));
//        statusText.setFont(new Font(20));
//    }

//    private void updateStatusText(){
//        String livesleft = "Lives Left: "+myPlayer.getLives()+"\n";
//        String score= "Score: \n";
//        String level = "Level: " + levelNum;
//        statusText.setText(livesleft+score+level);
//    }

    //////////////////////////////////////////////////////////////////////////
    private Scene setGameStage (Paint background) {
        // create a place to see the shapes
        var scene = new Scene(root, size, size, background);

        createGameOverText();
        root.getChildren().add(gameOverText);

        //createStatusText();
        myStatus = new Status(size);
        root.getChildren().add(myStatus.getStatusText());


        myBall = new Ball();
        myBall.placeForStart(size);
        root.getChildren().add(myBall.getMyImage());

        myPaddle = new Paddle();
        myPaddle.centerPaddle(size);
        root.getChildren().add(myPaddle.getView());

        for (ArrayList<Brick> brickRow : myBricks){
            for (Brick myBrick : brickRow){
                root.getChildren().add(myBrick.getView());
            }
        }

        scene.setOnKeyPressed(key -> myPaddle.handleSideKeyInput(key.getCode(), myScene.getWidth()));

        return scene;
    }


    /////////////////////////////////////////////////////////////////////////////


    private void checkBallBrickCollision(){
        for (ArrayList<Brick> brickRow : myBricks){
            for (Brick myBrick : brickRow){
                if (myBrick.getHealth()>0 && myBall.getMyImage().getBoundsInParent().intersects(myBrick.getView().getBoundsInParent())) {
                    myBrick.decreaseHealth();
                    myBall.BounceOff();
                }

            }
        }
    }

    private void checkBallHitsPaddle(){
        if(myBall.getMyImage().getBoundsInLocal().intersects(myPaddle.getView().getBoundsInLocal())){
            myBall.BounceOffPad();
        }
    }

}
