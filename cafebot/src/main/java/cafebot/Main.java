package cafebot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    static String prefix = "$";
    public static void main(String[] args) throws IOException{

        if (args.length != 1) {
            System.err.println("This bot requires exactly one argument, "
                    + "either a file with the token as content or the token directly.\n"
                    + "If the argument is a relative file path, it is relative to the working directory");
            System.exit(1);
        }

        String version = "0.1.0";

        System.out.println("CafeBot v"+version);
        System.out.println("Created by lillie#1072");

        DiscordApiBuilder apiBuilder = new DiscordApiBuilder();

        // Token
        Path tokenFile = Paths.get(args[0]);
        if (Files.isRegularFile(tokenFile)) {
            try (BufferedReader tokenFileReader = Files.newBufferedReader(tokenFile)) {
                apiBuilder.setToken(tokenFileReader.readLine());
            }
        } else {
            apiBuilder.setToken(args[0]);
        }

        // Login
        DiscordApi api = apiBuilder
                .setWaitForServersOnStartup(false)
                .login().join();
        System.out.println("Bot has connected to Discord.");

        // Listeners (Cogs)
        api.addListener(new Ping());
        /*
        api.addListener(new NSFW());
        api.addListener(new Administration());
        api.addListener(new Music());
        */ 

    }

}
