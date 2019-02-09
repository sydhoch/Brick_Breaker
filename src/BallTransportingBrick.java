import java.util.Random;

public class BallTransportingBrick extends Brick{

    private static final String BALL_TRANSPORTING_BRICK_IMAGE = "ballTransportBrick.gif";
    private static final int STARTING_HEALTH = 1;
    private int myScreenSize;
    Ball myBall;

    public BallTransportingBrick(Ball ball, int screenSize){
        super(STARTING_HEALTH);
        myScreenSize = screenSize;
        myBall = ball;
    }

    @Override
    protected void setBrickImage(){
        setImage(BALL_TRANSPORTING_BRICK_IMAGE);
    }

    @Override
    public void destroyBrick() {
        super.destroyBrick();
        Random rand = new Random();
        myBall.placeItem(rand.nextInt(myScreenSize), rand.nextInt(myScreenSize));
    }
}
