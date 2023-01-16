package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class JDKBot {

    private final Dotenv config;
    private final ShardManager shardManager;

    public JDKBot() throws LoginException {
        config = Dotenv.configure().load();
        String login = config.get("TOKEN");

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(login);
        builder.setStatus(OnlineStatus.IDLE);
        builder.setActivity(Activity.listening("to my owner"));
        shardManager = builder.build();
    }

    public Dotenv getConfig() {
        return config;
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args) {
        try {
            JDKBot bot = new JDKBot();
        } catch (LoginException e) {
            System.out.println("Oops! Your token is invalid!");
        }
    }
}
