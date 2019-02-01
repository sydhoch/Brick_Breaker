import java.util.Scanner;

public class Tests {
    private int ballInitX;
    private int ballInitY;
    private int startingXVelocity;
    private int startingYVelocity;
    public Tests(String fileName){
        readFile(fileName);
    }

    private void readFile(String fileName){
        Scanner scanner = new Scanner(GamePlay.class.getClassLoader().getResourceAsStream(fileName));
        ballInitX = scanner.nextInt();
        ballInitY = scanner.nextInt();
        startingXVelocity = scanner.nextInt();
        startingYVelocity = scanner.nextInt();
        String event = scanner.next();

        callTest(event);
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

    private void callTest(String event){
        if(event.equals("Lose Life")){
            testLoseLifeAtBottom();
        }
    }

    private void testLoseLifeAtBottom(){

    }
}
