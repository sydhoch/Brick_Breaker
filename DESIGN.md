### High-Level Design Goals

One of our main design goals was to make use of inheritance to simplify classes and allow for easy extension whenever
possible. We aimed to use inheritance from the beginning, rather than having to refactor code to fit a mold later on.
This meant planning out the structures and hierarchy of our project more carefully. This goal led us to create
5 abstract classes in total: Brick, GameText, Item, PowerUp, and PowerUpFactory. We aimed to put as much common code 
into these classes as possible.

Another major design goal was to have smart objects. We did not want all the code for objects interacting and 
doing things to be coming from the GamePlay or LevelConfiguration classes, because this would make objects only useful
for their image rather than what they actually could do. We wanted to make sure objects were responsible for 
having their own abilities and did not become inactive.

These two design choices led to our program having many small classes(over 20). We wanted to keep our classes small
for readability and to separate out all of the tangible objects. This helped us when we were adding new features because
we were able to find what we were looking for very quickly and also enabled us to be less repetitive.


### Adding New Features 

##### Adding New Levels
1. Create a level configuration text file in the resources folder labeled "levelx.txt" where x is any integer.
    - The first line of the file contains the number of rows and the number of columns in the rest of the file.
    - The rest of the lines have integers or symbols to indicate which type of brick to configure in which spots.
    - Multi-hit bricks are represented by an integer that correlates to its health(the amount of times it needs to be hit to be destroyed).
    - PowerUp bricks are denoted by an *, and have the ability to create a random powerup subclass instance by randomly selecting a powerup factory and calling its create method.
    - Permanent bricks use the # symbol and cannot be destroyed. They act as an obstacle for the ball.
    - Ball Transport bricks use the - symbol and when destroyed it teleports the ball to new location.
2. Change the static variable for the MAX_LEVEL to be the new maximum level number.

##### Creating New "Types" (Bricks and Power-ups)
Bricks and power-ups are a particularly easily extendable feature. In order to add a new Brick type:
1. Create a new class for your brick which extends the abstract Brick class
2. Implement setBrickImage() and activateBrickAbility() methods as required by the abstract 
class (If the brick does not have a special ability, this method would be left void)
3. Add code including the symbol that represents your brick to the method makeBrick(String brickSymbol) in 
LevelConfiguration so the new brick is placed in appropriate places
4. Add some of those symbols to the level files to see your brick
In order to add a new PowerUp type: 
1. Create a new class for your power-up which extends the abstract PowerUp class
2. Implement the methods required by the abstract PowerUp class (ex. the deactivate() method)
3. Create a new class for a new power-up factory, which will extend the abstract class PowerUpFactory
4. Implement the create() method so the factory can make a new PowerUp
5. Add an instance of the new PowerUp Factory to the list of PowerUpFactories in PowerUpBrick

##### Adding New Items to the Game
Adding new types of objects into the game would be a little trickier. In order to allow this new object to have an 
image, velocity, coordinates, etc, it would be easiest if it inherited from Item, the abstract class that 
all our on-screen objects inherit from. Once the class for the new object is complete, an instance of it should be made
in GamePlay and its image should be added to the root in setUpNewScene (). If it interacts or moves in anyway, that
method calls should be added to step(). To influence its functionality as a function of the level, code should be added
to the LevelConfiguration class. If the object collides or interacts with other objects, code should be added to 
GameInteractions to define these interaction rules.

##### Creating New Tests
1. Create a test file, labeled "test" + test number + "_level" + level number, in which:
    - the first line has the x and y initial positions of the ball for the test separated by a space i.e. 50 100
    - the second line has the x and y initial velocities of the ball for the test separated by a space i.e. -30 90
    - the third line has the first expected event i.e. Brick Destroyed
2. Find the method where the anticipated event happens in the code and add the tester object as a parameter to that method.
3. Add an if statement to check if tester is not null.
4. The code within the if statement varies based on the specifications of the test, however somewhere inside the if statement
    the tester calls setFirstEvent(String event) and sets the first event to the event that occurs during that method.
5. Also within the tester is not null if statement, after the first event is set, the tester should call callTest() which compares
    if the first event that occurs is equal to the event that was expected by the tester. The tester will then print the expected event and 
    success if the test was successful.

### Major Design Choices and Trade-offs

##### Choices in Testing

Originally, we decided to make a test file that was a parameter to our GamePlay class, and if the GameDriver instance 
variable test file string was not null, then the constructor would read the file into a new Tests object. The object reads the file 
and sets its instance variables. These variables include the initial x and y positions and velocities of the ball at the beginning 
of the test. The file also contains the expected first event based on the initial specifications. If the player hit , . or / on the 
splash screen and then pressed another key to start the game, the test would run. This was not possible anymore for the complete
version of the project because those three keys needed to be used for every level to run different tests.

We thought about sending the player back to the start screen in between every level to have the opportunity to run tests, but
decided this would be disruptive to regular game play so we ultimately made it so the tests can be run either before the level starts
on that level, or after the level starts during game play. To run the test before the level starts, the user presses the , . or / key
and then presses space to start the test. if the user presses those keys after already starting the game and the game isn't paused, 
the test will just start immediately. The trade-off of this decision is that the player can cause a test to fail by tampering with 
the level's configuration i.e. destroying a brick that was meant to be destroyed during the test or moving the paddle so the ball 
does not fall off the bottom of the screen.

##### Choices in Game Flow

One of our main design choices was separating the game flow into three main classes: GamePlay, GameInteractions, and 
LevelConfiguration. GameInteractions controlled interactions and responses (such as collisions) between on-screen 
items, LevelConfiguration does all prep for a new level, and GamePlay brings the whole project together, creates 
instances of GameInteractions and LevelConfiguration and uses the step() method to run the constant game actions. 
We saw some cons with this approach. First of all, we had to pass a huge number of parameters 
into GameInteractions and LevelConfiguration, which meant they were wholly dependent on most of the same classes as 
GamePlay. It also meant choosing a place for some of the methods that were relevant to more than one class, such 
as PlaceItemsForStart(), meaning we needed more public methods than if they had just been in GamePlay. We also were
unsure for some time if having GamePlay and GameInteractions was repetitive, since all these actions could be taken
care of by one class. However, the pros were more numerous and included shorter classes, greater readability, more
ease in finding certain code, and possibility for future code reuse. This last one was especially important for 
GameInteractions, since being able to separate our interactions from the rest of the game extends the possibilities of
 altering our game and creating variations by using the same setup but just changing the way objects interact 
 with one another. 


##### PowerUp Choices

One feature that involved a number of design choices was the power-up feature. One trade-off that came up was the scope 
of the PowerUp object. Originally, we did not think the power-up itself should be the one to call methods 
like paddle.lengthen() when it was activated, because we saw that it would create dependencies within the code. This was
a major con. However, going back to what we mentioned in our design goals, we also recalled talking about making 
smarter, more active objects felt that if the power-up was to be active, it would have to actually do that game 
alteration itself. Otherwise, it would just be a pointless falling ball. After deciding this, we designed the power-up 
itself to be the one responsible for making changes to objects in the game. 

Another design issue was designing the integration and creation of power-ups in the game. Initially, we thought about 
creating a power-up of each type and selecting one randomly from a list, but worried that this would limit gameplay, 
since it meant that 
there could only be one of each type onscreen at a time. This concern led me to look into using a reflection based design 
in order to create a random instance of one of the PowerUp 
subclasses, but research suggested this strategy was not best practice. In the end, we settled on a factory design.
This method of design allowed us 
to create a power-up whenever one was needed and have it be a random type by putting the power-up factories in a list, 
randomly selecting one, and using the create() method that they all inherited from their abstract PowerUpFactory class.
The pros of this were that the code was very readable, flexible, and extremely scalable. There are few steps to add
a new power-up type to the game and there are no self-imposed limits on how many power-ups can be on the screen. The
biggest con of this design is probably the large number of classes it created, since these classes do nothing but make
one power-up of a specific type.


### Assumptions and Decisions
One assumption we made was that the game screen would always be 500 x 500. Although this value could be changed in the 
GamePlay class, we did not make our objects dynamic enough to accomodate that change. If the game screen was a different
size, all the objects on screen (with the exception of the bricks which are dynamically sized) would remain the same
size. This would result in on-screen text, paddle, power-ups, and ball that are either too small or too big and no 
longer roughly proportional to the brick size. This assumption was made in order to simplify the sizing and creation
of these objects. 

Another assumption we made was that if the player has not lost all their lives and the gameOver boolean is set to true
for any reason during the last level of the game, the player has won. We assumed this originally because we did not 
think there was any other scenario. However, with the addition of testing this presented a problem since the game
ends after a test. Even though the player does not "win" after a test, the game treats it as if they have and displays
the "you won" message. This assumption made implementation of ending the game easier but ended up being logistically 
flawed.

We also assumed that both the level brick configuration text file and the scores text would always be present and 
accurate. Originally, we accounted for the scores file not being present, but in refactoring, took this piece out. 
If any of these files were missing, there would be errors when running our code since we did not build in error handling
for such situations. 
