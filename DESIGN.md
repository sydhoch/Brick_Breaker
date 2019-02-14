### High-Level Design Goals
- small classes & readability and tangible objects
- smarter, active objects
- one stage, one timeline
- make use of inheritance as much as possible 



### Adding New Features 
- new levels 
- adding new powerup/bricks --> leah 
- adding new item object into game --> leah
- tests



### Major Design Choices and Trade-offs
- powerup implementation --> leah
- game flow classes --> leah






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