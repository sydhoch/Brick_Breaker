import java.util.Scanner;

public class HighScore {
    private String fileName = "resources/scores.txt";
    private int myHighScore;

    public HighScore(){
        myHighScore = readHighScore();
    }

    public int readHighScore(){
        Scanner scanner = new Scanner(GamePlay.class.getClassLoader().getResourceAsStream("scores.txt"));
        if(scanner.hasNextInt()){
            return scanner.nextInt();
        }
        else{
            return 0;
        }
    }

    public int writeHighScore(){

    }
}
