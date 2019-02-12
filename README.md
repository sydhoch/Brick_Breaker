game
====

This project implements the game of Breakout.

Name: Leah Schwartz and Sydney Hochburg

### Timeline

Start Date: January 27th

Finish Date: February 12th

Hours Spent: Combined 100 hours

### Resources Used 

-Intersection between brick and ball
https://stackoverflow.com/questions/35073654/how-to-know-collision-between-2-images-javafx

-Changing size of Imageview 
https://stackoverflow.com/questions/27894945/how-do-i-resize-an-imageview-image-in-javafx

-Matching digits using regular expressions
https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java

-Writing to files
https://howtodoinjava.com/java/io/how-to-write-to-file-in-java-bufferedwriter-example/

-Tips from Ryan Bloom on how to bounce ball off paddle depending on where on paddle it hits

### Running the Program

GamePlay class: GameDriver

Data files needed: 
- Image files for ball, bricks, powerups, and paddle
- level1.txt, level2.txt, level3.txt
- test1_level1.txt, test2_level1.txt, test3_level1.txt
- test1_level2.txt, test2_level2.txt, test3_level2.txt
- test1_level3.txt, test2_level3.txt, test3_level3.txt
- scores.txt for high score

Key/Mouse inputs:
- Press any key to go from start screen into game

Cheat keys:
- "R": resets ball to starting position
- "L": gives extra lives
- Number keys: switches to corresponding level (in this case, only 1-3)
- Space bar: pauses ball but not paddle

Known Bugs:
- Sometimes when the ball hits directly between two blocks, they both are destroyed and the ball continues 
in its current direction
- Every now and then the ball bounces down a wall and does not move away from the wall until colliding with the
paddle 
- Unknown error which has appeared several times sporadically(goes away with no changes made) but does not interfere with gameplay 
- If you complete a test on the third level, the game displays the "you won" message instead of the neutral game
over message

Extra credit:
- Created fourth block type (ball teleporting block) in addition to the required three
- Ball goes off paddles in different directions based on where it came from/where it hits

Testing:
To test, press any key on splash screen. Then press desired level number. Then press desired test key.
Then press space to launch the test. The test can also be launched by pressing key for test after pressing
space, however I recommend following the way we suggested so that objects are not altered by regular game play.
- Level 1:
    , = test for if when the ball destroys a brick, a powerup is released
    . = test for when ball his brick, it is destroyed
    / = test for when ball goes into corner it bounces back in same direction(reverses x and y velocities)
- Level 2:
    , = test for destruction of multiple hit blocks
    . = test for losing life when ball falls off bottom of screen
    / = tests that a ball hitting the paddle on the left side will bounce back towards the left
- Level 3:
    , = checks that when ball destroys ball transport brick, ball moves location
    . = checks that permanent brick when hit is not destroyed even after being hit 5 times (one more than most powerful brick besides permanent brick)
    / = tests that a ball hitting the paddle on the right side will bounce back towards the right


### Notes
Testing must be run after the start screen. At the start of the level (before pressing space) the user should
press the testing key they want to run the test for. The test will still run if the key is pressed some time
during the level, however destroying blocks prior to pressing a testing key could ruin the set up and cause the
test to fail.

### Impressions
Both of us feel that we learned a huge amount throughout this project. Actually planning and designing something
as expansive as a game was a really interesting experience. More than that, working so closely with another 
person was something neither of us had done before and really valued. We still feel that we have some confusion
on whether or not our design/implementations were of good quality, but we are confident that our general understanding,
appreciation for, and knowledge of coding has increased more than in any other project we've worked on. 

