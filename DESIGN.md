###High Level Design Goals

###Adding New Features
*adding new levels
*adding new bricks/powerups
*adding new item object
*adding new tests

###Major Design Choices and Trade-offs
*Testing
Originally, we decided to make a test file that was a parameter to our GamePlay class, and if the test file string was 
not null, then the constructor would read the file into a new Tests object. The object reads the file and sets its instance
variables. These variables include the initial x and y positions and velocities of the ball at the beginning of the test.
The file also contains the expected first event based on the initial specifications. The keys 
*powerup implementation
*game interactions
*gamesetup & gameplay --> level config

###Assumptions and Decisions
* screen size is always 500
* objects always same size
