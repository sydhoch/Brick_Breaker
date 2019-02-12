
import java.util.Random;

public class Ball extends Item {

    private static final String BOUNCER_IMAGE = "ball.gif";
    private static final int STARTING_X_VELOCITY = -180;
    private static final int STARTING_Y_VELOCITY = 60;
    private final String LEFT = "left";
    private final String RIGHT = "right";
    private final int BOUNCE_TEST_NUMBER=5; //one more than the amount of hits it would take to destroy our strongest brick

    private static final String PERMANENT_BRICK = "Permanent Brick";
    private int bounceNumber = 0;

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
         * makes the ball bounce off of the left right and top walls by switching the direction of X & Y velocities
         * @param screenWidth width of screen
         */
        public void bounce (double screenWidth, Tests tester){
            if (tester != null) {
                if(getXCoordinate()<0 && getYVelocity()<0){
                    tester.setFirstEvent("Corner Bounce Back");
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
         * reverse direction of ball
         */
        public void bounceOffPad (String area, Tests tester){
            if(area.equals(RIGHT) && getXVelocity() < 0){
                setXVelocity(getXVelocity() * -1);
                if(tester!=null){
                    tester.setFirstEvent("Bounce Right Off Right Side of Paddle");
                    tester.callTest();
                }
            }
            if(area.equals(LEFT) && getXVelocity() > 0){
                setXVelocity(getXVelocity() * -1);
                if(tester!=null){
                    tester.setFirstEvent("Bounce Left Off Left Side of Paddle");
                    tester.callTest();
                }
            }
            setYVelocity(getYVelocity() * -1);
        }

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
         * @return if the ball is offscreen
         */
        public boolean ballFell ( double screenHeight){
            return getYCoordinate() > (screenHeight) && canSee();
        }

        private void setStartingVelocity () {
            setXVelocity(getStartingXVelocity());
            setYVelocity(getStartingYVelocity());
        }

        public void teleport ( int screenSize){
            Random rand = new Random();
            placeItem(rand.nextInt(screenSize), rand.nextInt(screenSize));
        }


}
