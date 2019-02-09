import javafx.animation.Timeline;
public class Player {

    private int myLivesLeft;
    private int myScore;
    private int myLevelNum;
//    private int myLastLevel;
//    private int myStartingLives;
    private int myScoreIncrement;
    private static final int STARTING_SCORE_INCREMENT = 1;
    private static final int STARTING_LIVES = 3;
    private static final int MAX_LEVEL = 2;

    public Player(Tests tester){
        //myStartingLives = startingLives;
        //myLastLevel = maxLevel;
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
        return MAX_LEVEL;
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
        myLivesLeft = STARTING_LIVES;
        myScore = 0;
        myScoreIncrement = STARTING_SCORE_INCREMENT;
            if(tester!=null){
                myLevelNum = tester.getLevel();
            }
            else {
                myLevelNum = 1;
            }
    }

    public void setLevel(int level){
        if (level <= MAX_LEVEL) {
            myLevelNum = level;
        }
    }

    public void setScoreIncrement(int scoreIncrement){
        myScoreIncrement = scoreIncrement;
    }

    public int getScoreIncrement(){
        return myScoreIncrement;
    }





}
