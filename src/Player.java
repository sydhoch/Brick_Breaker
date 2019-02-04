import javafx.animation.Timeline;
public class Player {

    int livesLeft;
    int score;

    public Player(int numLives){
        livesLeft = numLives;
        score = 0;
    }

    public int getLives(){
        return livesLeft;
    }


    public int getScore() {
        return score;
    }

    public void loseLife(Tests tester, Timeline animation){
        if (livesLeft > 0) {
            livesLeft--;
        }
        if(tester!=null) {
            tester.setFirstEvent("Lose Life");
            animation.stop();
            tester.callTest();
        }
    }

    public void gainLife(){
        livesLeft++;
    }

    public void increaseScore(int numPoints){
        score += numPoints;
    }




}
