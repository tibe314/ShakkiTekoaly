## What does the template handle?

The template handles communication between your chess engine and either Lichess or XBoard, instructions for 
setting up this functionality can be found
[here](https://github.com/TiraLabra/chess/blob/master/documentation/Beginners_guide.md).

## What does the template not handle?

The template does not provide the rules of chess, those are left for you to write yourself. The TestBot class uses a library
that provides these, but **these are not intended to be used by a student for the project.**

## What does your engine need to do?

Your bot should be an implementation of the ChessBot interface. This means it needs to have an implementation of the method 
```String nextMove(GameState gamestate)```which requires your bot to output UCI formatted String representations of moves, 
when prompted. In this format a move is simply the starting square followed by the destination square. For example ```e2e4```.


A notable exception to this are promotion moves,which have an additional 5th character representing which piece a pawn is 
promoted to, for example ```e7e8Q```. An example of how to handle this can be found in the TestBot class.

Additionally, your bot should be able to keep track of the boardstate so it can make valid moves. The GameState class keeps
track of all the moves made in the game so far with an ArrayList. You can get the latest move with the method 
***INSERT NAME HERE*** and use it to update your own Board, however you choose to implement it. This is all your bot really
needs to do to be able to use the template. 

### What else can your engine do?

The GameState class also keeps track of time (in milliseconds) remaining for each player, as provided by either Lichess or 
XBoard. This can be accessed with the methods ```getRemainingTime()``` and ```getRemainingTimeOpponent()```. This is not 
strictly required for writing a bot, but might be useful. 
