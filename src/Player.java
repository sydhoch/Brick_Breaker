public class Player {

    int livesLeft;

    public Player(int numLives){
        livesLeft = numLives;
    }

    public int getLives(){
        return livesLeft;
    }

    public void loseLife(){
        livesLeft--;
    }

    public  void gainLife(){
        livesLeft++;
    }




}