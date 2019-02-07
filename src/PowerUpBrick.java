public class PowerUpBrick extends Brick{

    private static final String POWERUP_BRICK_IMAGE = "powerBrick.gif";
    private static final int STARTING_HEALTH = 1;
    private static final int VALUE = 1;


    public PowerUpBrick(){
        super(STARTING_HEALTH, VALUE);
        setHasPowerUp(true);
    }

    @Override
    public void setBrickImage(){
        setImage(POWERUP_BRICK_IMAGE);
    }

}
