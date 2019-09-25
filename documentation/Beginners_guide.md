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

6. Now you can try the Tiralabra chess bot. The first step is to replace "INSERT TOKEN HERE" in the App.java file with your personal API access token.

7. One possible way to use the bot: after login, select “PLAY WITH THE COMPUTER” at https<span></span>://lichess.org/. Then choose which pieces you want to play. Finally, at the command line, type ./gradlew build, then ./gradlew run.

8. Your chess bot will start playig with random moves, that is, very poorly. Your task is now to create a real A.I. bot!

**Note:** By default, program keeps running as it waits for new challenges from Lichess, to close the program use CTRL+C

