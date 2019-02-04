import javafx.scene.Group;
import java.util.ArrayList;
import java.util.Scanner;


public class BrickConfigurar {

    private int mySize;
    private int numBrickCols;
    private int numBrickRows;
    private Group myRoot;
    private ArrayList<ArrayList<Brick>> myBricks;

    private static final double FRACTION_OF_SCREEN_WIDTH_FOR_BRICKS = 1.0/2.0;

    public ArrayList<ArrayList<Brick>> getBricks() {
        return myBricks;
    }

    /**
     * Adds an array of bricks to the scene root based on the configuration in a text file
     * @param fileName name of block configuring file
     */
    public BrickConfigurar(String fileName, Group root, double elapsedTime, int size){
        myBricks = new ArrayList<>();
        mySize = size;
        fillBrickList(readBrickFile(fileName), numBrickCols, numBrickRows);
        myRoot = root;
        addBricksToRoot();

    }

    /**
     * Reads information from block configuration text file, including mySize of screen, number of block rows, number of
     * block columns, and configuration of blocks
     * @param fileName name of block configuring file
     * @return configuration of blocks onscreen represented by ints
     */
    private int[][] readBrickFile(String fileName){
        Scanner scannie = new Scanner(GamePlay.class.getClassLoader().getResourceAsStream(fileName));
        numBrickCols = scannie.nextInt();
        numBrickRows = scannie.nextInt();
        int[][] brickConfigs = new int[numBrickRows][numBrickCols];
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
        for (int i = 0; i < numBrickRows; i++) {
            ArrayList<Brick> brickRow = new ArrayList<>();
            for (int j = 0; j < numBrickCols; j++) {
                if (brickConfigs[i][j] > 0) {
                    Brick brick = new Brick(brickConfigs[i][j], brickConfigs[i][j]);
                    brick.setSize(calcBrickWidth(numBrickCols), calcBrickHeight(numBrickRows));
                    brick.placeItem(j * brick.getWidth(),i * brick.getHeight());
                    brickRow.add(brick);
                }
            }
            myBricks.add(brickRow);
        }
    }

    private double calcBrickHeight(int numBrickRows){
        return mySize / numBrickRows * FRACTION_OF_SCREEN_WIDTH_FOR_BRICKS;
    }

    private double calcBrickWidth(int numBrickCols){
        return mySize / numBrickCols;
    }

    private void addBricksToRoot(){
        for (ArrayList<Brick> brickRow : myBricks){
            for (Brick brick : brickRow){
                myRoot.getChildren().add(brick);
            }
        }
    }
}
