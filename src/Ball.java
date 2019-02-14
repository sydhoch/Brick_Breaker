import java.util.Random;
/**
 * @author leahschwartz and sydneyhochberg
 *
 * Simulates a ball to bounce off walls, objects, and fall off the bottom of the screen
 * Inherits from abstract Item class
 * Dependent on Item and Tests class
 */
public class Ball extends Item {

    private static final String BOUNCER_IMAGE = "ball.gif";
    private static final int STARTING_X_VELOCITY = -180;
    private static final int STARTING_Y_VELOCITY = 60;
    private final String LEFT = "left";
    private final String RIGHT = "right";
    private final int BOUNCE_TEST_NUMBER = 5; //one more than the amount of hits it would take to destroy our strongest brick

    private static final String PERMANENT_BRICK = "Permanent Brick";
    private int bounceNumber = 0;
    private static final String BOUNCE_RIGHT= "Bounce Right Off Right Side of Paddle";
    private static final String BOUNCE_LEFT =  "Bounce Left Off Left Side of Paddle";
    private static final String CORNER_BOUNCE = "Corner Bounce Back";

    /**
     * Creates a ball with an image of a ball and a set x and y velocity
     */
    public Ball() {
            setImage(BOUNCER_IMAGE);
            setStartingVelocity();
            setCanSee(true);
        }

        public static int getStartingXVelocity () {
            return STARTING_X_VELOCITY;
        }

        public static int getStartingYVelocity () {
            return STARTING_Y_VELOCITY;
        }

        /**
         * Makes the ball bounce off of the left right and top walls by switching the direction of X & Y velocities
         * @param screenWidth width of screen
         */
        public void bounce (double screenWidth, Tests tester){
            if (tester != null) {
                if(getXCoordinate()<0 && getYVelocity()<0){
                    tester.setFirstEvent(CORNER_BOUNCE);
                    tester.callTest();
                }
            }
            if (getXCoordinate() < 0 || getXCoordinate() > (screenWidth - getWidth())) {
                setXVelocity(getXVelocity() * -1);

            }
            if (getYCoordinate() < 0) {
                setYVelocity(getYVelocity() * -1);
            }

        }

        /**
         * Makes the ball bounce in different directions depending on which area of paddle it hits
         * @param area part of item that ball hit
         * @param tester object meant to ensure method is working correctly by setting an expected first event and
         *               calling a test
         */
        public void bounceOffPad (String area, Tests tester){
            if(area.equals(RIGHT) && getXVelocity() < 0){
                setXVelocity(getXVelocity() * -1);
                if(tester!=null){
                    tester.setFirstEvent(BOUNCE_RIGHT);
                    tester.callTest();
                }
            }
            if(area.equals(LEFT) && getXVelocity() > 0){
                setXVelocity(getXVelocity() * -1);
                if(tester!=null){
                    tester.setFirstEvent(BOUNCE_LEFT);
                    tester.callTest();
                }
            }
            setYVelocity(getYVelocity() * -1);
        }

    /**
     * Makes ball bounce off items by simply reserving X and Y velocities
     * @param tester object meant to ensure method is working correctly by setting an expected first event and
     *          calling a test
     */
    public void bounceOff (Tests tester) {
            if(tester!=null){
                if(bounceNumber<BOUNCE_TEST_NUMBER){
                    setYVelocity(getYVelocity() * -1);
                    setXVelocity(getXVelocity() * -1);
                    bounceNumber++;
                }
                if(bounceNumber==BOUNCE_TEST_NUMBER){
                    tester.setFirstEvent(PERMANENT_BRICK);
                    tester.callTest();
                    bounceNumber=0;
                }
            }
            else{
                setYVelocity(getYVelocity() * -1);
                setXVelocity(getXVelocity() * -1);
            }
        }

        /**
         * Checks if ball is off of screen, indicating that it "fell"
         * @param screenHeight height of scene
         * @return boolean of if the ball is offscreen
         */
        public boolean ballFell ( double screenHeight){
            return getYCoordinate() > (screenHeight) && canSee();
        }

        private void setStartingVelocity () {
            setXVelocity(getStartingXVelocity());
            setYVelocity(getStartingYVelocity());
        }

    /**
     * Moves ball to random x,y coordinates onscreen
     * @param screenSize size of screen
     */
    public void teleport ( int screenSize){
            Random rand = new Random();
            placeItem(rand.nextInt(screenSize), rand.nextInt(screenSize));
        }


}
