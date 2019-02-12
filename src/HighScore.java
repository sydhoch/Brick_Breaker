import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * @author leahschwartz and sydneyhochberg
 *
 * Represents a highscore which can be written to and read from the "scores.txt" text file
 */

public class HighScore {
    private final String READ_FILE_NAME = "scores.txt";
    private final String WRITE_FILE_PATH = "resources/"+READ_FILE_NAME;
    private int myHighScoreNum;

    public HighScore(){
        myHighScoreNum = readHighScore();
    }

    private int readHighScore() {
        Scanner scanner = new Scanner(GamePlay.class.getClassLoader().getResourceAsStream(READ_FILE_NAME));
        if (scanner.hasNextInt()) {
            int savedScore = scanner.nextInt();
            if (savedScore > myHighScoreNum) {
                return savedScore;
            }
        }
        return myHighScoreNum;
    }

    public void saveScore(int score){
        if(score > myHighScoreNum){
            writeHighScoreToFile(score);
            myHighScoreNum = score;
        }
    }

    private void writeHighScoreToFile(int score){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(WRITE_FILE_PATH));
           // BufferedWriter writer = new BufferedWriter(new FileWriter(READ_FILE_NAME));
            writer.write(Integer.toString(score));
            writer.close();
        }
        catch (IOException e){
            System.out.println("couldn't write");
        }
    }

    public int getHighScoreNum(){
        return myHighScoreNum;
    }


    public void reset(){
        myHighScoreNum = 0;
        writeHighScoreToFile(0);
    }
}
