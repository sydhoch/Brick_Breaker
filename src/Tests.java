import javafx.animation.Animation;
import javafx.animation.Timeline;
import java.util.Scanner;

public class Tests {
    private int ballInitX;
    private int ballInitY;
    private int startingXVelocity;
    private int startingYVelocity;
    private String myFirstEvent="";
    private Timeline myAnimation;
    private String fileEvent;
    public Tests(String fileName, Timeline animation){
        readFile(fileName);
        myAnimation = animation;
    }

    private void readFile(String fileName){
        Scanner scanner = new Scanner(GamePlay.class.getClassLoader().getResourceAsStream(fileName));
        ballInitX = scanner.nextInt();
        ballInitY = scanner.nextInt();
        startingXVelocity = scanner.nextInt();
        startingYVelocity = scanner.nextInt();
        scanner.nextLine();
        fileEvent = scanner.nextLine();
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