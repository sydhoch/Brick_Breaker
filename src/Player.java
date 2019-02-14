/**
 * @author leahschwartz and sydneyhochberg
 *
 * Represents the player of the game, holding all the information the person who plays would be concerned with, such
 * as lives, score, highscore, and level
 * Dependent on HighScore class
 */
public class Player {

    private int myLivesLeft;
    private int myScore;
    private int myLevelNum;
    private HighScore myHighScore;
    private int myScoreIncrement;
    private static final int STARTING_SCORE_INCREMENT = 1;
    private static final int STARTING_LIVES = 3;
    private static final int MAX_LEVEL = 3; // After adding new level config file, change to make game include next level

    /**
     * Creates a new Player, ready to start a new game
     * Contains a HighScore object in order to keep track of and update high score
     */
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

    /**
     * Takes away life from player if there are lives to lose
     * Saves score if player is losing last life
     *
     * @param tester object to run tests in testing mode
     */
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

    /**
     * Add one life
     */
    public void gainLife(){
        myLivesLeft++;
    }

    /**
     * Increase player's score
     * @param numPoints amount to increase score by
     */
    public void increaseScore(int numPoints){
        myScore += numPoints;
    }

    /**
     * Add one to whichever level player is on to represent next level
     */
    public void increaseLevel(){
        myLevelNum++;
    }

    /**
     * Set player's attributes to defined starting values
     */
    public void reset(){
        myLivesLeft = STARTING_LIVES;
        myScore = 0;
        myScoreIncrement = STARTING_SCORE_INCREMENT;
        myLevelNum = 1;
    }

    /**
     * Sets level to passed in level as long as it does not exceed defined last level
     *
     * @param level number to set level to
     */
    public void setLevel(int level){
        if (level <= MAX_LEVEL) {
            myLevelNum = level;
        }
    }

    /**
     * Set amount score goes up/down by
     *
     * @param scoreIncrement amount to add to score
     */
    public void setScoreIncrement(int scoreIncrement){
        myScoreIncrement = scoreIncrement;
    }

    public int getScoreIncrement(){
        return myScoreIncrement;
    }

    /**
     * Determine if current score is higher than high score from start of level
     *
     * @return higher int
     */
    public int getCurrentHighScore(){
        return Math.max(myHighScore.getHighScoreNum(), myScore);
    }

}
