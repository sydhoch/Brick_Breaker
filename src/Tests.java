import java.util.Scanner;

/**
 * @author sydneyhochberg
 * Tests game functions specifically for each level such as Destroy Brick and Corner Bounce Back.
 */
public class Tests {
    private int myLevelNum;
    private int ballInitX;
    private int ballInitY;
    private int startingXVelocity;
    private int startingYVelocity;
    private String myFirstEvent = "";
    private String fileEvent;

    private double originalBallLocationX;
    private double originalBallLocationY;
    private double newBallLocationX;
    private double newBallLocationY;

    private final String BALL_VELOCITY = "Ball Increased Velocity";
    private final String BALL_TRANSPORT = "Ball Transport Brick";
    private boolean testFinished;

    /**
     * reads in the desired test file to set instance variables and sets testFinished to false for
     * the cases where a test was ran and completed previously
     * @param fileName contains the initial positions and velocities to set for the ball and the expected event first
     *                 based on these specifications
     */
    public Tests(String fileName){
        readFile(fileName);
        testFinished = false;
    }


    private void readFile(String fileName){
        myLevelNum = findLevelNum(fileName);
        Scanner scanner = new Scanner(GamePlay.class.getClassLoader().getResourceAsStream(fileName));
        ballInitX = scanner.nextInt();
        ballInitY = scanner.nextInt();
        startingXVelocity = scanner.nextInt();
        startingYVelocity = scanner.nextInt();
        scanner.nextLine();
        fileEvent = scanner.nextLine();
    }

    private int findLevelNum(String fileName) {
        return Integer.parseInt(fileName.substring(fileName.indexOf("level") + 5, fileName.indexOf("level") + 6));
    }

    /**
     *
     * @returns the initial x position of the ball specified by the testing file
     */
    public int getPosX() {
        return ballInitX;
    }

    /**
     *
     * @returns the initial y position of the ball specified by the testing file
     */

    public int getPosY() {
        return ballInitY;
    }

    /**
     *
     * @returns the initial x velocity of the ball specified by the testing file
     */
    public int getXVel() {
        return startingXVelocity;
    }
    /**
     *
     * @returns the initial y velocity of the ball specified by the testing file
     */
    public int getYVel() {
        return startingYVelocity;
    }

    /**
     * sets the string equal to the first event that occurs during testing
     * @param firstEvent
     */
    public void setFirstEvent(String firstEvent) {
        myFirstEvent = firstEvent;
    }

    /**
     * checks if the first event is equal to the expected first event specified by the testing file
     */
    public void callTest() {
        if (myFirstEvent.equals(fileEvent)) {
            System.out.println(fileEvent + " SUCCESS");
        } else {
            System.out.println(fileEvent + " FAILED");
            System.out.println(myFirstEvent);
        }
        testFinished = true;
    }

    /**
     * helper for the testBallTransport() method and stores the x and y positions of the ball before transport
     * in instance variables
     * @param x x position of ball before transport
     * @param y y position of ball before transport
     */
    public void setOriginalBallLocation(double x, double y){
            originalBallLocationX = x;
            originalBallLocationY = y;
    }

    /**
     *
     * @returns boolean that tells you if the test is done running or not
     */
    public boolean isTestFinished() {
        return testFinished;
    }
    /**
     * helper for the testBallTransport() method and stores the x and y positions of the ball after transport
     * in instance variables
     * @param x x position of ball after transport
     * @param y y position of ball after transport
     */
    public void setNewBallLocation(double x, double y) {
        newBallLocationX = x;
        newBallLocationY = y;
    }

    /**
     * test for seeing if after the ball destroys a transport brick if it teleports the ball successfully
     */
    public void testBallTransport() {
        if (originalBallLocationX != newBallLocationX || originalBallLocationY != newBallLocationY) {
            setFirstEvent(BALL_TRANSPORT);
        }
        callTest();
    }
    /*
    public void testBallSpeedup(double startX, double startY, double x, double y) {
        if ((Math.pow(startX, 2) + Math.pow(startY, 2)) < (Math.pow(x, 2) + Math.pow(y, 2))) {
            setFirstEvent(BALL_VELOCITY);
        }
        System.out.println((Math.pow(startX, 2) + Math.pow(startY, 2)));
        System.out.println((Math.pow(x, 2) + Math.pow(y, 2)));
        callTest();
    }
    */
}