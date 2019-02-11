import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HighScore {
    private String fileName = "resources/scores.txt";
    private int myHighScoreNum;

    public HighScore(){
        myHighScoreNum = readHighScore();
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

    public int updateHighScore(int score){
        if(score>myHighScoreNum){
            System.out.println("hi");
            writeHighScoreToFile(score);
        }
    }

    private void writeHighScoreToFile(int score){
        try{
            //File file = new File("scores.txt");
            //String path = file.getAbsolutePath();
            BufferedWriter writer = new BufferedWriter(new FileWriter("resources/scores.txt"));
            writer.write(Integer.toString(score));
            writer.close();
            System.out.println("hey");
        }
        catch (IOException e){
            System.out.println("couldn't write");
        }
    }
}
