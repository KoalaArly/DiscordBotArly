package me.koolaarly.arlybot;


import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;
import kotlin.random.Random;
import me.koolaarly.arlybot.listeners.EventListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import java.util.function.IntFunction;


/**
 * @author KoolaArly
 */
public class ArlyBot extends Throwable{

    private final Dotenv config;

    private final ShardManager shardManager;

    /**
     * Loads environment variables and builds the bot shard mananger.
     * @throws InvalidTokenException occurs when bot token is invalid.
     */
    public ArlyBot() throws InvalidTokenException, DotenvException {
        config  = Dotenv.configure().load();
        String token = config.get("TOKEN");

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("dir beim Schlafen zu."));
        builder.enableIntents(GatewayIntent.GUILD_MESSAGE_TYPING, GatewayIntent.DIRECT_MESSAGE_TYPING);

        shardManager = builder.build();

        // Register listeners
        shardManager.addEventListener(new EventListener());
    }

    /**
     * Retrieves the bots config.
     * @return the config instance.
     */
    public Dotenv getConfig() {
        return config;
    }

    /**
     * Retrieves the bot shard manager.
     * @return the ShardManager instance for the bot.
     */
    public ShardManager getShardManager() {
        return shardManager;
    }

    /**
     * R
     * @param args
     */
    public static void main(String[] args) {
        try {
            ArlyBot bot = new ArlyBot();
        } catch (InvalidTokenException e) {
            System.out.println("Error: Falscher Bot Token: Invalid.");
        }
    }
}
