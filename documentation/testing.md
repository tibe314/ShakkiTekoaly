# Documentation for testing

## Writing your own tests

You can pass moves to `GameState` class with method `setMoves`. These moves can be completely arbitrary, i.e. GameState has no validation whether they are valid chess moves or not.
The parameter `moves` should be a single String with the moves separated by commas, **eg.**`"a1b1,c1d1,e1f1"` is a valid String.  
You can then pass these gamestates to your bot or simply get the move list from the variable `moves`.  

GameState can also be given arbitrary game times, with setters `setTimePlayer` and `setTimeOpponent`. They are to be given as type `long`, in milliseconds.  

You can set the side your bot is playing through variables `turn` and `playing` with the Enums from `model.Side`.
`turn` represents the side that is currently playing, and `playing` is the side of the bot that gets these particular GameStates.
(I.e. `playing` should stay the same with every GameState and `turn` should change with every other GameState that the bot gets)



*Example of creating a new gameState*
```java
GameState gs = new GameState();

gs.setMoves("a2a3, b8c6, e2e3");
gs.setTimePlayer(299880);   //Optional if your bot doesn't use these
gs.setTimeOpponent(299830); //Optional if your bot doesn't use these
```

Since `GameState` is a static representation of a chess game, you need to create multiple instances of them, to simulate a set of moves.  

## PerformanceTest class
The api contains a class that can be used for testing.
The class is `PerformanceTest.java` and it can be found in the `datastructureproject` package.  

You can execute the class with a gradle task `performanceTest`.  
**eg.** `./gradlew performanceTest`  
You can also pass parameters to the task and handle them in the ´main´ method with ´args´ parameter.    
**eg.** `./gradlew performanceTest args="argument1 argument2 ... argument"`  

**What you test and how you test it is entirely up to you.**
