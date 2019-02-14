CompSci 307: Game Project Analysis
===================

> This is the link to the [assignment](http://www.cs.duke.edu/courses/compsci307/current/assign/02_game/):

Design Review
=======

## Status

#### Readability 
I believe our code is, overall, pretty readable. I felt that very few inline comments were necessary because methods
were named simply but accurately. One strategy that helped increase code readability were consistent naming conventions.
We made sure to use camelCase for all methods and to start methods with a verb, as well as to use "my" at the start of
instance variables. Short classes were a priority, so we separated our code into a new class whenever we felt it was 
necessary and tried to make use of inheritance to simplify classes. We also focused on breaking large chunks of 
potentially unreadable code into smaller methods with descriptive names. A good example of this can be seen by the 
refactoring of the step method, which is a particularly long and important method. This was the method in my commit 5 
days ago:
```java
public class GamePlay{
 private void step (){
      
        if (myPlayer.getLives() > 0 && countRemainingBricks() == 0 && myPlayer.getLastLevel() != myPlayer.getLevel()){
           // System.out.println("new level needed");
            myPlayer.increaseLevel();
            levelSetter.createNewLevel(myPlayer.getLevel());
        }
        else if (myPlayer.getLives() == 0 || (countRemainingBricks() == 0 && myPlayer.getLastLevel() == myPlayer.getLevel())) {
            endGame();
        }
        myStatus.updateText();
        if (!gameOver) {
            myBall.move(SECOND_DELAY);
            myBall.bounce(myScene.getWidth(), myScene.getHeight(), tester, animation);
            interacter.checkBallHitsPaddle();
            interacter.checkBallBricksCollision(tester, animation);
            interacter.checkPowerUpPaddleCollision();
            if (myBall.ballFell(myScene.getHeight())) {
                myPlayer.loseLife(tester,animation);
                if (myPlayer.getLives() > 0) {
                    levelSetter.placeItemsForStart();
                }
            }
            for (PowerUp powerUp : myPowerUps) {
                powerUp.move(SECOND_DELAY);
            }
        }
    }
}

````
But this is the class after refactoring specifically to increase simplicity and readability:

```java
public class GamePlay{
    private void step (){
            if (isLevelOver()){
                myPlayer.increaseLevel();
                levelSetter.createNewLevel(myPlayer.getLevel());
            }
            else if (isGameOver() || (testMode && tester.isTestFinished())) {
                endGame();
            }
            if(testKeyHit){
                updateBallSettings();
                testKeyHit= false;
            }
            myStatus.updateText();
            if (!gameOver) {
                moveOnScreenItems();
                checkAllCollisions();
                if (myBall.ballFell(myScene.getHeight())) {
                    handleBallFall();
                }
            }
        }
}
```
It is much simpler and easier to understand. I believe most of our code reflects this standard.


#### Flexibility of Code

Flexibility of our project is increased by the emphasis on small classes and use of inheritance. These classes indicate
where to go to extend or change code more easily than a long class would. Inheritance also makes code much more flexible
by allowing for easier additions to the game. For example, making new brick and power-up types is very easy since
most of the necessary code and code structure comes from the superclasses. Brick and power-up types need only a couple
abstract methods defined and then are fully functional thanks to this structure.  

However, the flexibility of our code is decreased by the specificity of some of it in some cases. 
Theoretically, the type or amount of some items might change or someone might want to add another one of some 
object within the game and this would be somewhat difficult to do. For example, adding another object that the ball 
bounces off but is not a brick or paddle would be challenging. This is because the whole game is written on the 
assumption that the ball interacts specifically with bricks and paddles. One place that demonstrates why this could 
be a challenge is the GameInteractions class. This method demonstrates too much code specificity:
```java
public class GameInteractions{
public void checkBallHitsPaddle(Tests tester){
        if(myBall.collidesWith(myPaddle)){
            if(myBall.getXCoordinate() < myPaddle.getCenter()){
                myBall.bounceOffPad(LEFT, tester);
            }
            if(myBall.getXCoordinate() > myPaddle.getCenter()) {
                myBall.bounceOffPad(RIGHT, tester);
            }
        }
    }
}
````
There is no reason why this bouncing off the paddle needs to be specific to just a Paddle object. If I added a new Item
and wanted the ball to bounce off it, I would not be able to use any of this code. Any Item should be able to have its
collision checked with the ball. 

#### Dependencies

INCOMPLETE


## Overall Design

#### Creating New Levels

The simplicity of creating a new level is one part of the project in which I feel a lot of pride. There are only two
steps to create a new level and have it fully integrated into the game:

1. Make a new level configuration text file name "level" + level number + ".txt" with the top line of the file 
in the format: numberOfColumns numberOfRows and then an array of numbers (0-4) and symbols (-, #, *). 
0 indicates no brick, 1-4 indicates a brick of corresponding strength, # indicates a permanent brick, - indicates 
a ball transporting brick, and * indicates a power-up dropping brick
2. Change the MAX_LEVEL variable at the top of the Player class to reflect the number of the new last level

After making these changes, the level should automatically be integrated into the game and the cheat keys that let you
switch level should become functional for as many added levels as well (as long as the MAX_LEVEL does not exceed 9)

#### Project Design and Justification 

The general design of our project is that GamePlay oversees and integrates all the function of our game by bringing 
our other classes together and making instances of the objects needed to run the game. We broke the main functionality 
of the game into smaller classes used by GamePlay in order to simplify our code and allow for more flexibility. These
classes are GameInteractions, which controls the way in which on-screen objects interact with one another within the 
game and LevelConfiguration, which generates a new brick pattern and resets any items that need to be reset to start 
a new level. This structure took quite a bit of time to finalize. 

 When we originally made levels, we broke 
LevelConfiguration into two separate classes, BrickConfiguration and SetUp, however this resulted in classes 
that really did not do much more than a method could and they were not very active objects. 
Combining them into a LevelConfiguration class made a class that actually produced something - a new level - rather
than just configured and moved items around. 

These main game classes were in charge of integrating all the other objects into the game. One of our major goals in 
the creation of our other classes was to create as many tangible objects as possible. For Brickbreaker, this was not
too difficult of a task since the game is not very abstract. Having many tangible objects means that code is easy to
find and edit, objects are easier to change, and code is more understandable and object-oriented. Another design goal
for these objects was to make use of an inheritance structure whenever possible. I wanted to keep these objects closely
related so that we could reuse methods and make extending the game simpler. This led to the creation of many abstract 
classes. We had 5 abstract classes in total: Brick, GameText, Item, PowerUp, and PowerUpFactory. We struggled with 
deciding whether to create some of these. For example, GameText is based on a Text object, so we were not sure
if it was necessary to make or if we should just integrate Text objects throughout the game. I think the GameText hierarchy 
wound up being quite useful though because it made our text much smarter than just a text object would be and reduced
the work to make text functional within the main game classes. The further into the game we got, the smarter these
smaller objects became. Originally, we did not want them to interact because we believed they should all be independent of
one another. This resulted in very inactive, not useful items, so we changed our design to allow some objects to call 
methods on others in order to directly affect their state. Despite this design, we tried to keep objects at least mostly
independent so that they could be used in another game or context.

## Your Design

#### Abstraction
One abstraction I made was the Brick abstraction. This abstraction is based around an abstract Brick class, which
inherits from the abstract Item class I made and has several classes which inherit from it. These classes are the
PowerUpBrick, MultiHitBrick, PermanentBrick, and BallTransportingBrick. I think this class very much helped overall
design.

First, this abstraction increased standardization, which I found to be very important when working with a partner.
The methods a brick is expected to have are clearly defined and the names are consistent. This makes it easy and
straightforward to add new types of bricks to the game. The PermanentBrick was the last brick class I made and it only
took 20 minutes to code and integrate into the game because the process of creating a new type of brick is so uniform.
One thing I struggled with concerning this standardization was that all bricks are not, in fact, the same. For example,
some of them have special methods that can be activated, such as making a powerup, and others do not. To handle this,
I created an abstract method in the Brick class:


```java
public class Brick{
    public abstract void activateBrickAbility(Ball ball, Group root, List<PowerUp> powerUps, int screenSize, Tests tester);
}
```
For some bricks, this method really made sense. For example, in BallTransportBrick, the method allows the brick to 
“teleport" the ball. However, in other Bricks, such as PermanentBrick, there were no abilities to activate, so the 
method was left empty, which could be potentially confusing. Furthermore, the method requires all the parameters that 
any brick type might need, making some of them unnecessary depending on the brick. Nonetheless, I believe the clarity 
of the standardization outweighs this possible drawback.

A second reason why this abstraction helped our design was that it allowed for a large amount of code reuse. Since the 
bricks are fairly similar in their general form and function, I was able to put several basic methods into the Brick 
class that were used by all the bricks with little to no additional code. For example, the constructor of the Brick 
class was fairly simple:
```java
public class Brick{
    public Brick(int health){
        myHealth = health;
        setBrickImage();
        setCanSee(true);
        isDestroyed = false;
        givesPoints = true;
        if (health < 1) {
            destroy();
        }
    }
}
```
But it would have needed to be repeated over and over again had this abstraction not been present.  

Another reason this abstraction helped was because it allowed us to be more general in the rest of our code. 
The only place in which we refer to bricks by a specific type is when they are being created. Other than that, 
bricks are always referred to by the abstract Brick class. This allowed all the bricks to be kept in a list together 
and to be iterated through and collided with or destroyed without needing to know anything about them except for that 
they were a type of brick.

#### Checklist Issues

##### Don’t Use Magic Values
This is one guideline where I find I made some errors. Although I tried to refactor away any “magic” values, I see that 
there are a few I missed. For example in GamePlay:
```java
public class GamePlay{
    private void handleCheatKeys(KeyCode code){
            if (code.getChar().equals("L")) {
                myPlayer.gainLife();
            }
            if (code.getChar().equals("R")) {
                levelSetter.placeItemsForStart();
                }
            }
        }
```
I can see why these values are problematic, since which key runs which cheat is something that could very likely be 
changed in the future. I should have created private static final Strings with names such as GAIN_LIFE_CHEATKEY and 
RESET_ITEM_PLACEMENT_CHEATKEY to hold each of these values.

##### Declare Variables as Close to Use as Possible
This is an issue in my code. In most of my code, I declared many variables close together rather than near the 
first time they were being used because it seemed more understandable at the time to group declarations together. 
This could be resolved by not declaring items until they’re actually necessary, rather than just declaring them because
I know I’ll need them in the future. 

##### Use Interfaces
Although I tried to keep code general, I sometimes became bogged down in what I was trying to achieve rather than what 
someone else might want to use my code to achieve. For example, many of the objects within the game extended Item, but 
instead of writing methods using Items as return values or parameters, I always referred to the objects by their 
subclass, such as Ball or Paddle. I did not think abstractly enough and often viewed these subclasses as independent 
from one another rather than thinking about how I could work with the Item class instead. 


#### Implemented Feature

One feature I implemented was the power-up feature. I implemented all parts of it, from the abstract PowerUp class, to 
the three subclass, which each represent a different “type” of powerup, to the creation and integration of powerups 
into the game. The first important aspect of how this feature was implemented is the abstract PowerUp class, which 
defines the creation of the power-up in its constructor by setting some image, making it invisible, and specifying that 
it is not active. There are methods to destroy the  power-up, as well as to make the  power-up start falling. There is 
also a method to “activate" the  power-up, which sets a timer that will then call the deactivate method. Neither the 
activate nor deactivate method in the abstract class cause any changes to the game, but rather provide a template to 
show where in the code a subclass  power-up should alter the game. It also ensures the gameplay of the  power-ups is 
consistent in that they are active for a consistent amount of time and are able to deactivate themselves. The other 
important aspect of the implementation of  power-up was how they got added to the game. I decided to use a factory 
model. The addition of this feature can be seen in [this commit.](https://coursework.cs.duke.edu/compsci307_2019spring/game_team21/commit/a72cd693975632eeb1c81d0118a97cb56ec4a91b)

This commit demonstrates the code I added to my three PowerUpFactory classes, each of which has a create() method that
 can only make one type of PowerUp. I then integrated these factories into the game, as shown by the code added to the 
 GameInteractions class where I created this method:

````java
public class GameInteractions{
private PowerUp createRandomPowerUp(){
            Collections.shuffle(myPowerUpFactories);
            return myPowerUpFactories.get(0).create();
        }
    }
````
This method ensured that a random PowerUpFactory would be chosen to create the new powerup in the game, resulting in a 
random type of powerup. This commit primarily features a new development, since prior to this the power-ups were not 
random. Although this method was eventually moved to the PowerUpBrick class in order to make the PowerUpBrick more active,
the method itself and the implementation of how to create the power-ups stayed the same.

One issue I struggled with in the development of this feature was the scope of the PowerUp object. Originally, I did 
not think the power-up itself should be the one to call methods like paddle.lengthen() when it was activated, because 
I was not sure if it would be bad to create a dependency on the object it was meant to affect. I thought instead that 
the GameInteractions class should call those types of methods. However, I also recalled talking about making smarter, 
more active objects with Professor Duvall and felt that if the power-up was to be active, it would have to actually do 
that game alteration itself. Otherwise, it would just be a pointless falling ball. After deciding this, I made the 
power-up itself call the methods that altered the objects it was meant to affect. 

This decision led to another issue. To support the power-up being able to alter game objects, I had to allow the 
power-up to access the paddle, ball, or whichever other object it might need. I considered passing these objects in 
as parameters to the constructor and making them member variables because this would allow me to only pass the specific 
parameter each power-up type needed to that power-up. I also thought about passing them to the activate() method, 
however my issue with that was that since the method was defined by an abstract class, all power-up types would have 
to take all those parameters, even though only some would use them. In the end, I decided it was still better for these 
objects to be passed to the activate() method because I did not think it made logical sense for the power-ups to hold 
onto these objects as instance variables and because I wanted to avoid adding unnecessary state to my object. 
    
Another design issue was the integration and creation of power-ups in the game. I thought about creating a power-up of 
each type and selecting one randomly from a list, but I worried that this would limit gameplay, since it meant that 
there could only be one of each type onscreen at a time. If a player somehow hit four power-up blocks in quick 
succession, there would be some type of error or graphical flaw, since I would not have enough power-ups in the game 
to meet demand. This concern led me to look into reflection in order to create a random instance of one of the PowerUp 
subclasses, but my research led me to believe that this strategy was considered unstable and not best practice. Finally, 
I settled on a factory design after talking through my problem with Professor Quan. This method of design allowed me 
to create a power-up whenever one was needed and have it be a random type by putting the power-up factories in a list, 
randomly selecting one, and using the create() method that they all inherited from their abstract PowerUpFactory class. 
Although this choice resulted in several additional small classes, I think it was very readable, flexible, and extremely 
scalable.

As far as dependencies go, the PowerUp class is dependent on the abstract Item class, which it inherits from. This 
dependency allows all power-ups to use Item methods, such as setting an image, moving, and being placed. The PowerUp 
class is also dependent on the Ball, Player, and Paddle class since these are the objects it requires to be activated.
 One assumption that the PowerUp class makes is that there is only one of each of the objects it effects. If, 
 for example, there was another Ball object in the game, the power-up would not also make the second ball slow down 
 since it only expects its power to be applied to a single ball. 


## Alternate Designs

#### Design Choice One

##### Choice: 
To have an abstract Item class from which on-screen items (power-ups, bricks, ball, paddle) inherit and which 
contains an ImageView object

##### Alternate Designs: 
- Only have items inheriting from their own separate hierarchies (bricks from Brick class, ball from nothing, etc)
    - Pros: More independence for each class, do not have to deal with consistent signatures for methods meaning that
    getting necessary parameters to objects is easier, objects can handle more of their own instance variables without
    having to bother with getters and setters
    - Cons: More repeated code across objects that have similar and basic item attributes such as coordinates, movement, 
    visability, and images, less generality in code without a superclass to refer to 
- Have all items inherit from ImageView class
    - Pros: Less complicated inheritance structure, built-in methods of ImageView class, appearance of simplicity 
    - Cons: Far reaching changes if implementation is changed and ImageView is no longer inherited from, lack of 
    control since we are not in charge of ImageView class, lack of ability to create common methods between similar items
- Have Item class directly inherit from ImageView  
    - Pros: Do not need to bother creating an ImageView instance, built-in methods of ImageView class, logical 
    inheritance hierarchy 
    - Cons: Change in Item class implementation could cause huge amount of dysfunction if ImageView methods are used in
    other classes
    
##### Ultimate Preference: 
Ultimately, I think the decision to create an ImageView within the Item class and have classes inherit from Item was 
the best design. This design allows for the most flexibility in altering the code shared among these objects and 
promoting code standardization and reuse. Originally, I leaned more toward the third alternate design, but this changed
due to what we learned during the frogger lab. During that lab, we saw the issues that could be caused by changing the
implementation of a class that inherits from a rectangle. This made me realize the same catastrophic effects on our
code if the ImageView implementation were changed. One example of where this could have infiltrated far into our 
other classes is this method from LevelConfiguration:
```java
 public class LevelConfiguration{
    private void fillBrickList(String[][] brickConfigs) {
           for (int i = 0; i < numBrickRows; i++) {
               List<Brick> brickRow = new ArrayList<>();
               for (int j = 0; j < numBrickCols; j++) {
                   Brick brick = makeBrick(brickConfigs[i][j]);
                   brick.setSize(calcBrickWidth(), calcBrickHeight());
                   brick.placeItem(j * brick.getWidth(), i * brick.getHeight());
                   brickRow.add(brick);
                   myRoot.getChildren().add(brick.getImage());
               }
               myBricks.add(brickRow);
           }
       }
}
```
This method is currently implemented using methods defined in Item, however if Item simply extended ImageView, there 
would be nothing to stop my partner or anyone else from coming in and rewriting this method so that getting the brick's
width, height, or placing it was implemented using those ImageView methods rather than specific Item methods as 
it uses now. 
I believe using an ImageView object in the Item class is better design
because it ensures other coders can't start making Item subclasses that rely on specific ImageView methods and 
can't easily access ImageView methods from objects extending Item. Our way, a
change in the implementation of Item should not affect its subclasses or the game dramatically.

#### Design Choice Two

##### Choice: 
Splitting the main flow of the game into GameInteractions, GamePlay, and LevelConfiguration classes. 

##### Alternate Designs: 
- Keeping all the functionality in the main GamePlay class
    Pros: Simplifies game, decreases parameters being passed back and forth, keeps all relevant methods in one place
    Cons: Creates a very long class, makes it harder to find specific code, decreases possible future code use, 
    creates difficulties working with multiple people
- Separating the game functionality into specific play and setup classes that only interact in the driver
    Pros: Makes logical sense as a division of labor, shortens classes and splits more evenly, increases possible 
    code reuse with ability to make game with same setup but totally different play
    Cons: Really limits gameplay since all setup has to be done before (cannot add to the root during the game since the
    scene is already setup), some methods of setup needed again throughout for game to function (ex. placing items in
    starting spots)

##### Ultimate Preference: 
I believe our ultimate division into the three classes LevelConfiguration, GamePlay, and GameInteractions was the best
choice out of these designs. These three classes did pass many parameters back and forth and had many member variables 
which was my biggest concern, but in this case I think that was necessary for a functional game. I do wonder if there
was a better we could have organized these objects or if we perhaps should have used some getters and setters in between
these classes instead of the long list of parameters. 
Initially, I was resistant to making the interactions class, because I thought it should just be the job 
of the GamePlay class, but I eventually came to recognize that being able to separate our interactions from the rest
of the game extended the possibilities of altering our game and creating varients by using the same setup but 
just changing the way objects interact with one another. 