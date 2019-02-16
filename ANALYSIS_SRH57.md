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
This code could be improved because callTest is not a very clear name for what is happening. The code
is also repetitive which could be solved by moving checking if the tester is null, setting the first event,
and calling the test to the actual test class. We could have made a method in the test class that



What makes this project's code flexible or not (i.e., what new features do you feel are easy or hard to add)?
* Our classes our separated out in a way that makes it easy to find desired objects with ease. This simplifies
    adding new features by cutting down the time to find where to start implementing.
    - Easy to implement:
        * making new levels: only steps are creating new level text file and changes the max level number in the player class
        * making new bricks: has abstract class
        * making new powerups
    - Difficult to implement:
        * new ball or moving object
        * monster

What dependencies between the code are clear (e.g., public methods and parameters) and what are through "back channels" (e.g., static calls, order of method call, requirements for specific subclass types)? 
Note, you can use IntelliJ to help you find the dependencies within your project.

* Clear:
    * When we instantiate a new object of another class.
    * When we have the object as a parameter in that class.

    It is clear that our GamePlay class has dependencies on almost all of the objects that are used in during the 
game(Ball, Player, GameInteractions, etc.)

* Less Clear:
    * Abstract classes

    It is less clear to see how GamePlay depends on for example the Item class because many of our GamePlay objects are
subclasses of Item, but the word Item itself is not actually seen anywhere in the GamePlay class.



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
This code could be improved because 



### Overall Design
Reflect on how the program is currently organized.

As briefly as possible, describe everything that needs to be done to add a new level to the game.
* Create a level configuration text file in the resources folder labeled "levelx.txt" where x is any integer.
    - The first line of the file contains the number of rows and the number of columns in the rest of the file.
    - The rest of the lines have integers or symbols to indicate which type of brick to configure in which spots.
    - Multi-hit bricks are represented by an integer that correlates to its health(the amount of times it needs to be hit to be destroyed).
    - PowerUp bricks are denoted by an *, and have the ability to create a random powerup subclass instance by randomly selecting a powerup factory and calling its create method.
    - Permanent bricks use the # symbol and cannot be destroyed. They act as an obstacle for the ball.
    - Ball Transport bricks use the - symbol and when destroyed it teleports the ball to new location.
* Change the static variable for the MAX_LEVEL to be the new maximum level number.

Describe the overall design of the program, without referring to specific data structures or actual code (focus on how the classes relate to each other through behavior (methods) rather than their state (instance variables)).
* 

Justify why your overall code is designed the way it is and any issues the team wrestled with when coming up with the final design.

### Your Design
Reflect on the coding details of your part of the project.
Describe an abstraction (either a class or class hierarchy) you created and how it helped (or hurt) the design.
Discuss any Design Checklist issues within your code (justify why they do not need to be fixed or describe how they could be fixed if you had more time).
Describe one feature that you implemented in detail:
Provide links to one or more GIT commits that contributed to implementing this feature, identifying whether the commit represented primarily new development, debugging, testing, refactoring, or something else.
Justify why the code is designed the way it is or what issues you wrestled with that made the design challenging.
Are there any assumptions or dependencies from this code that impact the overall design of the program? If not, how did you hide or remove them?


### Alternate Designs
Reflect on alternate designs for the project based on your analysis of the current design.
Describe two design decisions made during the project in detail:
What alternate designs were considered?
What are the trade-offs of the design choice (describe the pros and cons of the different designs)?
Which would you prefer and why (it does not have to be the one that is currently implemented)?