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

    public void loseLife(){
        if (livesLeft > 0) {
            livesLeft--;
        }
    }

    public void gainLife(){
        livesLeft++;
    }

    public void increaseScore(int numPoints){
        score += numPoints;
    }




}
