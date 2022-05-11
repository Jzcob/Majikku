package majikku.majikku.Discord;

import majikku.majikku.Files.DiscordConfig;
import majikku.majikku.Majikku;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class DiscordBot {

    private final ScheduledExecutorService executorService;
    private static JDA jda;

    public DiscordBot() throws InterruptedException {
        executorService = Executors.newScheduledThreadPool(10);
        jda = createUser();
        if (jda == null) {
            return;
        }
    }

    public static JDA getJda() {
        return jda;
    }

    JDA createUser() {
        JDABuilder jda = JDABuilder.createDefault(DiscordConfig.get().getString("Discord.token"));
        jda.addEventListeners(
                new Majikku());
        jda.setStatus(OnlineStatus.ONLINE);
        jda.setMemberCachePolicy(MemberCachePolicy.ONLINE);
        jda.setChunkingFilter(ChunkingFilter.ALL);
        jda.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES);
        jda.enableCache(CacheFlag.ACTIVITY);
        try {
            return jda.build();
        } catch (LoginException e) {
            e.printStackTrace();
            return this.jda;
        }
    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    //Next update
}
