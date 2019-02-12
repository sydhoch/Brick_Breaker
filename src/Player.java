/**
 * @author leahschwartz and sydneyhochberg
 *
 * Represents the player of the game, holding all the information the person who plays would be concerned with, such
 * as lives, score, highscore, and level
 */
public class Player {

    private int myLivesLeft;
    private int myScore;
    private int myLevelNum;
    private HighScore myHighScore;
    private int myScoreIncrement;
    private static final int STARTING_SCORE_INCREMENT = 1;
    private static final int STARTING_LIVES = 3;
    private static final int MAX_LEVEL = 3;

    public Player(){
        myHighScore = new HighScore();
        reset();
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

    public void loseLife(Tests tester){
        if (myLivesLeft == 1){
            myHighScore.saveScore(myScore);
        }
        if (myLivesLeft > 0) {
            myLivesLeft--;
        }
        if(tester!=null) {
            tester.setFirstEvent("Lose Life");
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

    public void reset(){
        myLivesLeft = STARTING_LIVES;
        myScore = 0;
        myScoreIncrement = STARTING_SCORE_INCREMENT;
        myLevelNum = 1;
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

    public int getCurrentHighScore(){
        return Math.max(myHighScore.getHighScoreNum(), myScore);
    }

}
