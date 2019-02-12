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
- Unknown error which has appeared several times but does not interfere with gameplay 

Extra credit:
- Created fourth block type (ball teleporting block) in addition to the required three


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

