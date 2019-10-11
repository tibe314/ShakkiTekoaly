# Documentation for testing

## Writing your own tests

You can pass moves to gamestate with method `setMoves`. The moves can be completely arbitrary, i.e. the gamestate class has no validation if they are correct chess moves.
The parameter should be a single String with the moves divided with commas, **eg.**`"a1b1,c1d1,e1f1"` is a valid String.  
You can then pass these gamestates to your bot or simply get the move list from the variable `moves`.  

GameState can also be given arbitrary game times, with setters `setTimePlayer` and `setTimeOpponent`. They are to be given as `long`, in milliseconds.  

You can set the side your bot is playing through variables `turn` and `playing` with the Enums from `model.Side`.
`turn` represents the side that currently playing, and `playing` is the side that bot that gets these particular GameStates plays.
(I.e. `playing` should stay the same with every GameState and `turn` should change with every other GameState that the bot gets)



*Example of creating a new gameState*
```java
GameState gs = new GameState();

gs.setMoves("a2a3, b8c6, e2e3");
gs.setTimePlayer(299880);   //Optional if your bot doesn't use these
gs.setTimeOpponent(299830); //Optional if your bot doesn't use these
```

Since `GameState` is a static representation of a chess game, you need to create multiple of them to simulate a set of moves.  

## PerformanceTest class
The api contains a class that can be used for testing.
The class is `PerformanceTest.java` and it can be found in the `datastructureproject` packet.  

You can execute the class with a gradle task `performanceTest`.  
**eg.** `./gradlew performanceTest`  
You can also pass parameters to the task and handle them in ´main´ method with ´args´ parameter.    
**eg.** `./gradlew performanceTest args="argument1 argument2 ... argumentn"`  

**What you test and how you test it is entirely up to you.**
