import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HighScore {
    private final String READ_FILE_NAME = "scores.txt";
    private final String WRITE_FILE_PATH = "resources/"+READ_FILE_NAME;
    private int myHighScoreNum;

    public HighScore(){
        myHighScoreNum = readHighScore();
    }

    public int readHighScore(){
        Scanner scanner = new Scanner(GamePlay.class.getClassLoader().getResourceAsStream(READ_FILE_NAME));
        if(scanner.hasNextInt()){
            return scanner.nextInt();
        }
        else{
            return 0;
        }
    }

    public void updateHighScore(int score){
        if(score>myHighScoreNum){
            writeHighScoreToFile(score);
        }
    }

    private void writeHighScoreToFile(int score){
        try{
            //File file = new File("scores.txt");
            //String path = file.getAbsolutePath();
            BufferedWriter writer = new BufferedWriter(new FileWriter(WRITE_FILE_PATH));
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

    private void reset(){

    }
}
