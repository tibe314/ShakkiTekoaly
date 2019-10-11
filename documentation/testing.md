# Documentation for testing

## Writing your own performance tests

You can pass moves to gamestate with method `setMoves`. The moves are can be completely arbitrary, i.e. the gamestate class has no validation if they are correct chess moves.
The parameter should be a single String with the moves divided with commas, **eg.**`"a1b1,c1d1,e1f1"` is a valid String.  
You can then pass these gamestates to your bot or simply get the move list from the variable `moves`.

Game state can also be given arbitrary game times, with setters `setTimePlayer` and `setTimeOpponent`. They are to be given as `long`, in milliseconds.  
*Example of creating a new gameState*
```java
GameState gs = new GameState();

gs.setMoves("a2a3, b8c6, e2e3");
gs.setTimePlayer(299880);
gs.setTimeOpponent(299830);
```


## PerformanceTest class
The api contains a class that can be used for testing.
The class is `PerformanceTest.java` and it can be found in the `datastructureproject` packet.  

You can execute the class with a gradle task ´performanceTest´.  
**eg.** `./gradlew performanceTest`  
You can also pass parameters to the task and handle them in ´main´ method with ´args´ parameter.    
**eg.** `./gradlew performanceTest args="argument1 argument2 ... argumentn"`  

**What you test and how you test it is entirely up to you.**
