Design Review
=====

### Status
####What makes the code well-written and readable?
* We were very thoughtful when thinking of names for instance variables and methods so the reader can guess what to 
    expect from the code. 
* Consistency to follow convention and keep coding practices the same across authors: 
    - We made our methods start with verbs to indicate the action they perform. 
    - We for the most part made our instance variables start with "my."
    - We made all of our names in camel case format.
* We separated our code out into over 20 classes to keep the code within each class short and easy to read.


Example of good code:

```
private void handleAllKeys(KeyCode code){
        handleRestartKey(code);
        checkForTest(code);
        if(testKeyHit){
            tester = new Tests(test);
        }
        if (!gameOver) {
            handleRunKeys(code, animation);
            handleCheatKeys(code);
        }
    }
```

This is an example of well-written and readable code. All methods and variables are in camel case format and
the names are specific enough that the reader can tell what this code does. This method handles when any
key is hit. It calls other methods that handle when specific keys are hit which enables this code to be
short enough to understand what is going on. Any time a key is hit, handleRestartKey checks to see
if the key was one that restarts the game, checkForTest checks the key to see if it was a test key and 
if a test key was hit, a new test object is made. If the game is not over yet, handleRunKeys checks
to see if the player pauses or starts the game with the space key, and handleCheatKeys checks if 
any of the cheat keys were hit and handles them. For better consistency, checkForTest should maybe have
instead started with "handle" like the rest of the key handlers.

Example of not as good code:
```
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
```
This code could be improved because it is a bit repetitive and callTest() isn't a very clear name.
Test could have a method in it that handles all of this and just takes the tester and the event name
in as parameters. Also callTest could be renamed to something more descriptive like compareFirstAndExpectedEvent().



####What makes this project's code flexible or not?
* Our classes our separated out in a way that makes it easy to find desired objects with ease. This simplifies
    adding new features by cutting down the time to find where to start implementing.
    - Easy to implement:
        * making new levels: only steps are creating new level text file and changes the max level number in the player class
        * making new bricks: has abstract class
        * making new powerups
    - Difficult to implement:
        * new ball or moving object
        * monster


Example of Good Code:
```
public abstract void activateBrickAbility(Ball ball, Group root, List<PowerUp> powerUps, int screenSize, Tests tester);

```

I really like the idea of this abstract method even though we did not end up using it that often. If we wanted to 
make bricks with different abilities, this class makes it easy and shows flexibility.

Example of Bad Code:
```
private int findLevelNum(String fileName) {
           return Integer.parseInt(fileName.substring(fileName.indexOf("level") + 5, fileName.indexOf("level") + 6));
       }
```

In this code there is a string, which really should have been put in a static instance global variable in the Tests
class, but I missed it when I was going through looking for strings. This makes it more difficult to find and change
if the file names for tests happened to change and not include the word level anymore.

####What dependencies between the code are clear and what are through "back channels"? 
Note, you can use IntelliJ to help you find the dependencies within your project.

* Clear:
    * When we instantiate a new object of another class.
    * When we have the object as a parameter in that class.

    It is clear that our GamePlay class has dependencies on almost all of the objects that are used in during the 
game(Ball, Player, GameInteractions, etc.)

Clear Dependency Code
```
public GamePlay(){
        myRoot = new Group();
        myBall = new Ball();
        myPaddle = new Paddle();
        myPlayer = new Player();
        myBricks = new ArrayList<>();
        myLevelText = new LevelText(SCREEN_SIZE, myPlayer);
        myGameOverText = new GameOverText(SCREEN_SIZE, myPlayer);
        myScene = new Scene(myRoot, SCREEN_SIZE, SCREEN_SIZE, BACKGROUND);
        myStatus = new StatusText(SCREEN_SIZE, myPlayer);
        gameOver = false;
        myPowerUps = new ArrayList<>();
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        levelSetter = new LevelConfiguration(myBall, myPaddle, myBricks,myRoot, myPlayer, myPowerUps, myLevelText,
                SCREEN_SIZE, animation, myScene);
        setUpNewScene();
        resetForNewGame();
        interacter = new GameInteractions(myRoot, myBall, myBricks, myPaddle, myPlayer, myPowerUps, SCREEN_SIZE);
    }

```

* Less Clear:
    * Abstract classes

    It is less clear to see how GamePlay depends on for example the Item class because many of our GamePlay objects are
subclasses of Item, but the word Item itself is not actually seen anywhere in the GamePlay class.

Less Clear Dependency code:

```
public class Ball extends Item {}
public class Paddle extends Item{}

```

When we instantiate the new objects in the GamePlay class it is clear GamePlay is dependent on all of the object
classes after the word "new." However, it is not clear that GamePlay depends on Item because the Item extension is
hidden in the object classes. Item also includes other objects but I just showed ball and paddle for examples.



### Overall Design
Reflect on how the program is currently organized.

####As briefly as possible, describe everything that needs to be done to add a new level to the game.
1. Create a level configuration text file in the resources folder labeled "levelx.txt" where x is any integer.
    - The first line of the file contains the number of rows and the number of columns in the rest of the file.
    - The rest of the lines have integers or symbols to indicate which type of brick to configure in which spots.
    - Multi-hit bricks are represented by an integer that correlates to its health(the amount of times it needs to be hit to be destroyed).
    - PowerUp bricks are denoted by an *, and have the ability to create a random powerup subclass instance by randomly selecting a powerup factory and calling its create method.
    - Permanent bricks use the # symbol and cannot be destroyed. They act as an obstacle for the ball.
    - Ball Transport bricks use the - symbol and when destroyed it teleports the ball to new location.
2. Change the static variable for the MAX_LEVEL to be the new maximum level number.

####Describe the overall design of the program.
Our GameDriver is where we deal with the stage and switching scenes from the splash screen to the game. Our
GamePlay class is basically where most of our other classes come together. GamePlay calls the levelConfiguration
class which sets up the level, and instantiates all of the objects that are in the game. GamePlay also calls
GameInteractions which is where all of the interactions between different objects are handled. From GameInteractions,
the objects handle whatever methods are specific to their own object. The testing bleeds throughout the whole program
in order to see when events are happening.

####Justify Design

We also tried to utilize abstract classes wherever we could to eliminate redundant code and make implementing
new features easier. We wanted to try to bring testing out of the code more, but couldn't figure out how to 
separate testing when it needs to actually stop the animation after a certain event happens. I really 
stood up for the idea of having an interactions class because it made GamePlay much more readable and made
finding the interactions easier to find for testing. 

At first we struggled with setting up our main classes and originally we had a class called GameSetUp which
basically set up the level configuration and instantiated our objects for the game. We then realized that
GameSetUp was really more focused around the level configuration than it was around setting up the game. 
It also was not really a specific tangible object. We changed GameSetUp to LevelConfiguration and instead
instantiated our objects in the GamePlay class. Later we also made the interactions class to, as I said previously,
make GamePlay more readable. 

### Your Design
Reflect on the coding details of your part of the project.
####Describe an abstraction you created and how it helped (or hurt) the design.
I originally had the idea to make the level interactions class, although my partner and I worked on it 
together. I believe on a high level this class helped the design by having a class that had a lot of dependencies
so that other classes could have less dependencies. For example, the ball class doesn't need to take in the
paddle as a parameter to see if the ball hit the paddle because GameInteractions handles this.

I also made the Tests class which I honestly am not sure if my design is correct because I have never had to 
really deal with a testing situation in which the tests have to bleed into the code. I would be really 
curious to see how more experienced coders would tackle the same problem. As I talked about earlier, I definitely
think there were some design decisions within my test class or the way it is used in other classes that could 
be improved.

####Discuss any Design Checklist issues within your code.
As I mentioned before with my code example, my testing code sometimes has the flexibility issue because I have duplicate code
and wished I made a method that sets the first event and calls callTest within the Tests class.
I also have a communication issue because of the name callTest() which really should have been called
compareEvents() or something more specific along those lines. 

####Describe one feature that you implemented in detail:
Provide links to one or more GIT commits that contributed to implementing this feature, identifying whether the commit represented primarily new development, debugging, testing, refactoring, or something else.

I implemented the feature bounceOffPad() and originally the x and y velocities of the ball were just multiplied
by -1 to reverse the directions when the ball hit the pad. Then I made it so that when the ball hits the left side of 
the pad, the ball goes left and vice versa. This commit represents when I finished this feature. This feature was not 
too difficult to implement so I did not need debugging or testing commits for it. Also I was definitely more specific in 
my commits than I ever have been before, but for my next project I really still have so much room to improve.
I need to be more descriptive about what stage I am at in implementing a feature and also need to commit in between
every feature and not commit multiple at once.

At first I had the strings "right" and "left" going into the method to see what side of the pad the ball
bounced off of. I then realized that it is not good design practice to have strings in my code so I made static instance
variables globally for the ball class so that I could easily find them and change them if I needed to, which I ended
up messing around with quite a bit. I tried to make this method actually have 4 sections: far right, middle right,
middle left, and far left. I wanted the physics to seem more natural. However, I ran into a problem because I tried
to multiple the middle directions by -.75 but because this was a multiplication to the velocities, the velocity
not only changed direction, but also decreased speed. I figured out a way to get around this problem with adding,
but never had the time to go back and fix it so I kept this method to only two areas: right and left.

This method assumes that the calculations that happen in the GameInteractions
class to find out what side of the paddle the ball hits are correct. 

[My Commit:"Bounce off pad changes"](https://coursework.cs.duke.edu/compsci307_2019spring/game_team21/commit/8efde9f342f6bea131681fb38edae514889ab575)

### Alternate Designs
Reflect on alternate designs for the project based on your analysis of the current design.
####Describe two design decisions, designs considered, trade-offs, and which I prefer.
1. We were having trouble implementing the high score feature because for an unknown reason, our scores.txt
file was updating but our program was not reading in the new value when we used the restart key "n" in the game.
We decided to get around this by saying if the current high score is higher than the high score read from the 
file, keep the current high score. This is tricky because if we wanted to reset our high score, it would be more confusing
and worse design to get around that placeholder feature. We ideally wanted to have the high score read from the file
because of this trade-off but we just could not figure out why our code was not working correctly. If we had more
time I would have wanted to get to the bottom of this problem and have our code handle it the correct way without
any temporary fixes. Another design we considered to fix this problem was to get rid of our high score class and
have high score handled in the player class, however we decided that the design was more important than the 
functionality for this class so we kept it separate.
2. When we were setting up the classes in the beginning we had a GamePlay class which was responsible for setting
up the game and running it and handling all interactions between objects. The class was extremely long and
difficult to read and understand what was happening. Then we decided that we should split this class into set up
and play. Our GameSetUp class was born. This class set up the bricks, the ball and the paddle on the screen. We then 
realized that GameSetUp was not good for expanding our game to add new levels and that there barely was anything
else to the GameSetUp besides configuring the level. We changed GameSetUp to Level Configuration. We still 
had a really long and confusing GamePlay class so we decided that we could have an interactions class that
handled the objects interacting with one another during the game. This enabled the actual object classes
to have fewer dependencies as well. The trade-off of our original design is long and confusing code. The trade-off
of our current design is many dependencies. I prefer the way we have our classes now because I think readability
is more important than dependency.