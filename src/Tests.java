import java.util.Scanner;

public class Tests {
    private int ballInitX;
    private int ballInitY;
    private double startingXVelocity;
    private double startingYVelocity;
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

    private void callTest(String event){
        if(event.equals("Lose Life")){
            testLoseLifeAtBottom();
        }
    }

    private void testLoseLifeAtBottom(){

    }
}
