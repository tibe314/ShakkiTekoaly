/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package chess;

import chess.bot.TestBot;
import chess.connection.LichessAPI;
import chess.model.Profile;
import chess.connection.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Map<String, String> env = System.getenv();
        
        String token = "PLEASE DON'T INSERT TOKEN HERE";
        
        if (args.length > 0) {
            token = args[0];
        } else if (env.containsKey("LICHESS_TOKEN")) {
            token = env.get("LICHESS_TOKEN");
        } else {
            try (Scanner reader = new Scanner(new File("src/main/resources/token.txt"))) {
                if (reader.hasNextLine()) {
                    token = reader.nextLine();
                }
            } catch (Exception e) {
                // System.out.println("No token.txt found.");
            }
        }

        TestBot bot = new TestBot(token);
        
        Long initialTime = System.currentTimeMillis();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (System.currentTimeMillis() - initialTime < 2500 && !in.ready()) {
                Thread.sleep(25);
            }

            if (in.ready()) {
                String input = in.readLine();
                if (input.equalsIgnoreCase("xboard")) {
                    XBoardHandler xb = new XBoardHandler(bot, in);
                    xb.run();
                }

            } else {
                LichessAPI api = new LichessAPI(bot);

                Profile myProfile = api.getAccount();

                System.out.println("Profile ID: " + myProfile.id);

                api.beginEventLoop();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
