import javafx.animation.Timeline;
public class Player {

    private int myLivesLeft;
    private int myScore;
    private int myLevelNum;
    private int myLastLevel;
    private int myStartingLives;

    public Player(int startingLives, int maxLevel, Tests tester){
        myStartingLives = startingLives;
        myLastLevel = maxLevel;
        reset(tester);
    }

    public int getLives(){
        return myLivesLeft;
    }

    public int getScore() {
        return myScore;
    }

    public int getLevel() {
        return myLevelNum;
    }

    public int getLastLevel(){
        return myLastLevel;
    }

    public void loseLife(Tests tester, Timeline animation){
        if (myLivesLeft > 0) {
            myLivesLeft--;
        }
        if(tester!=null) {
            tester.setFirstEvent("Lose Life");
            animation.stop();
            tester.callTest();
        }
    }

    public void gainLife(){
        myLivesLeft++;
    }

    public void increaseScore(int numPoints){
        myScore += numPoints;
    }

    public void increaseLevel(){
        myLevelNum++;
    }

    public void reset(Tests tester){
        myLivesLeft = myStartingLives;
        myScore = 0;
        if(tester!=null){
            myLevelNum = tester.getLevel();
        }
        else {
            myLevelNum = 1;
        }
    }

    public void setLevel(int level){
        if (level <= myLastLevel) {
            myLevelNum = level;
        }
    }





}
