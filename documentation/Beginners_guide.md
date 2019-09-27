# Beginner's guide for chess bot setup

The following minimum actions are necessary to run the Tiralabra chess bot app with the Lichess.org application program interface (API). See [Lichess API](https://lichess.org/api) for the full API Reference. 

By no means, this guideline will not replace the API Reference. We hope that this guideline will save your valuable time when getting started.

1. Download, clone or pull the app from *git@<span></span>github.com:TiraLabra/chess.git* into your /chess folder or other suitable location.

2. Register to [Lichess](https://lichess.org/signup). Here, you need to agree to the four given points.

**Note:** If you have played even a single game as a human player and now want to try playing as a bot, you will have to DELETE your [token](https://lichess.org/account/oauth/token) and also close your account to register it as a bot.

3. Create [New personal API access token](https://lichess.org/account/oauth/token/create) and choose all the scopes.

**Note:** Never put your personal token on github or other public files.

**Note:** if you copy your access token to a Word document (or another similar text processing document), and from there to your chess bot program, a hidden character may be added in the end of your token. If your token does not work, check with backspace if a hidden character was added.

4. Upgrade your account to a bot:

*curl -d '' https<span></span>://lichess.org/api/bot/account/upgrade -H "Authorization: Bearer INSERT YOUR TOKEN HERE"*

6. Now you can try the Tiralabra chess bot. 

    a) You can replace "PLEASE DON'T INSERT TOKEN HERE" in the App.java file with your personal API access token and risk that you accidently push it to GitHub or
    
    b) you can pass token as a parameter or
    
    c) you can create "token.txt" file to resources-directory (it's in src/main). It won't go to GitHub because it's in *.gitignore*.

**Note** Token given as a parameter overrides any token in token.txt file. 

7. One possible way to use the bot: after login, select “PLAY WITH THE COMPUTER” at https<span></span>://lichess.org/. 
Then choose which pieces you want to play. Finally, at the command line, type *./gradlew build*, 
then if you inserted your token App.java *./gradlew run* or if you didn't *./gradlew run --args=INSERT YOUR TOKEN HERE* .

8. Your chess bot will start playig with random moves, that is, very poorly. Your task is now to create a real A.I. bot!

**Note:** By default, program keeps running as it waits for new challenges from Lichess, to close the program use CTRL+C


## XBoard Setup

XBoard is a graphical user interface chessboard for chess engines. Xboard can be connected to your tira chess engine.

1. Download the tar.gz file of the latest stable version of [XBoard](https://www.gnu.org/software/xboard/#download)

Uncompress it, for example, under the same directory where the chess directory is.

$ tar xvzf xboard-4.9.0.tar.gz

2. Start XBoard

$ xboard

3. Make sure that you have a jar file for your engine

$ ./gradlew build

4. Under Engine tab, select Edit Engine List..

5. Add the path of the chess engine's jar file in the list

"tira-chess" -fcp "java -jar /home/local/..your path../chess/build/libs/chess-all.jar"

You can replace "tira-chess" with any unique name.
Then click 'commit changes' and 'OK'.

6. Under Engine tab, select Load New 1st Engine..

Just select your engine from the list and click 'OK'.

7. Make your first move (as white) and your engine should respond by its move.

You can also select 'Machine White' under the Mode tab. In this case, your engine will play white pieces and make its move first.

**Note:** When developing your AI engine, you may want to modify the way how XBoard communicates with your chess engine. The [Chess Engine Communication Protocol](https://www.gnu.org/software/xboard/engine-intf.html) has the details.




