import javafx.animation.Animation;
import javafx.animation.Timeline;
import java.util.Scanner;

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

    public int getPosX() {
        return ballInitX;
    }

    public int getPosY() {
        return ballInitY;
    }

    public int getXVel() {
        return startingXVelocity;
    }

    public int getYVel() {
        return startingYVelocity;
    }

    public void setFirstEvent(String firstEvent) {
        myFirstEvent = firstEvent;
    }

    public void callTest() {
        if (myFirstEvent.equals(fileEvent)) {
            System.out.println(fileEvent + " SUCCESS");
        } else {
            System.out.println(fileEvent + " FAILED");
            System.out.println(myFirstEvent);
        }
        testFinished = true;
    }

    public void setOriginalBallLocation(double x, double y){
            originalBallLocationX = x;
            originalBallLocationY = y;
        }
    public boolean isTestFinished() {
            return testFinished;
        }
    public void setNewBallLocation(double x, double y) {
        newBallLocationX = x;
        newBallLocationY = y;
    }

    public void testBallTransport() {
        if (originalBallLocationX != newBallLocationX || originalBallLocationY != newBallLocationY) {
            setFirstEvent(BALL_TRANSPORT);
        }
        callTest();
    }

    public void testBallSpeedup(double startX, double startY, double x, double y) {
        if ((Math.pow(startX, 2) + Math.pow(startY, 2)) < (Math.pow(x, 2) + Math.pow(y, 2))) {
            setFirstEvent(BALL_VELOCITY);
        }
        System.out.println((Math.pow(startX, 2) + Math.pow(startY, 2)));
        System.out.println((Math.pow(x, 2) + Math.pow(y, 2)));
        callTest();
    }
}