## User guideline

The following minimum actions are necessary to run the Tiralabra chess bot app with the Lichess.org application program interface (API). (See https://lichess.org/api for the full API Reference.)

1. Download, clone or pull the app from git@github.com:TiraLabra/chess.git into your /chess folder or other suitable location.
2. Register to Lichess at https://lichess.org/signup. Here, you need to agree to the four given points.
3. Click “Generate a personal API token” at https://lichess.org/api#section/Authentication

Now you have two options. You can play as a bot, as you will do with the Tiralabra chess bot app, or you can play as yourself. You cannot do the two with the same registered account.

4. You may first want to try Lichess chess as yourself. (If you want to get your chess bot playing, go directly to step 5.)

You can, for example, select all but the last options at https://lichess.org/account/oauth/token/create.

You will receive a confirmation email. After your confirmation, you will receive your token and find it at https://lichess.org/account/oauth/token.

Note: Never put your personal token on github or other public files.

Note: if you copy your access token to a Word document (or another similar text processing document), and from there to your chess bot program, a hidden character may be added in the end of your token. If your token does not work, check with backspace if a hidden character was added.

Now you can play chess games in different ways. You can also try direct communication with the Lichess API, either through a computer program or directly from the command line. For example

$ curl https://lichess.org/api/account -H "Authorization: Bearer BQPxDrjq1vDqilaR"

should display your account information in JSON format, if you use your personal access token.

5. If you have played even a single game as yourself and now want to try playing as a bot, as you will do with the chess bot app, you will have to DELETE your token at https://lichess.org/account/oauth/token and also close your account. Then you need to repeat steps 2 and 3. You need to register with a new, different username. Then create a new access token like in step 4, but this time select (at least) the ‘Play as a bot’ option. You will receive a confirmation email. After your confirmation, you will receive your token and find it at https://lichess.org/account/oauth/token.

6. Now you can try the Tiralabra chess bot. The first step is to replace <INSERT TOKEN HERE> in the App.java file with your personal API access token.

7. One possible way to try the bot: after login, select “PLAY WITH THE COMPUTER” at https://lichess.org/. At command line, type ./gradlew build, then ./gradlew run.

8. Your chess bot will start playig with random moves, that is, very poorly. Your task is now to create a better bot, a real A.I. bot!

