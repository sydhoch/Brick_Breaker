import javafx.animation.Animation;
import javafx.animation.Timeline;
import java.util.Scanner;

public class Tests {
    private int myLevelNum;
    private int ballInitX;
    private int ballInitY;
    private int startingXVelocity;
    private int startingYVelocity;
    private String myFirstEvent="";
    private String fileEvent;
    public Tests(String fileName){
        readFile(fileName);
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

    private int findLevelNum(String fileName){
        return Integer.parseInt(fileName.substring(fileName.indexOf("level")+5,fileName.indexOf("level")+6));
    }

    public int getPosX(){
        return ballInitX;
    }

    public int getPosY(){
        return ballInitY;
    }

    public int getXVel(){
        return startingXVelocity;
    }
    public int getYVel(){
        return startingYVelocity;
    }

    public void setFirstEvent(String firstEvent){
        myFirstEvent = firstEvent;
    }

    public void callTest(){
        if(myFirstEvent.equals(fileEvent)){
            System.out.println(fileEvent+ " Success");
        }
    }


    /*private void testLoseLifeAtBottom(){

    }
    */
}
/* Things left to do:
    - "press space to start"
    - add space ass pause button on start screen
    - start screen stuff
    - Tests

 */