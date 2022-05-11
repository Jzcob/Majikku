package majikku.majikku;

import majikku.majikku.Chats.*;
import majikku.majikku.Discord.DiscordBot;
import majikku.majikku.Events.*;
import majikku.majikku.Files.DiscordConfig;
import majikku.majikku.Files.HomeConfig;
import majikku.majikku.Files.SpawnConfig;
import majikku.majikku.Files.WarpConfig;
import majikku.majikku.GameRule.*;
import majikku.majikku.HelpOP.*;
import majikku.majikku.Commands.*;
import majikku.majikku.Important.Reload;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.events.ReadyEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Logger;

public final class Majikku extends JavaPlugin implements EventListener{
    @Override
    public void onEnable() {

    Logger log = Bukkit.getLogger();
    log.info("[Majikku] Enabled.");
    //Chats
    getCommand("adminchat").setExecutor(new AdminChat(this));
    getCommand("builderchat").setExecutor(new BuilderChat(this));
    getCommand("moderatorchat").setExecutor(new ModeratorChat(this));
    getCommand("ownerchat").setExecutor(new OwnerChat(this));
    getCommand("staffchat").setExecutor(new StaffChat(this));
    //HelpOP
    getCommand("ask").setExecutor(new Ask(this));
    getCommand("answer").setExecutor(new Response(this));
    //Commands
    getCommand("creative").setExecutor(new Creative(this));
    getCommand("adventure").setExecutor(new Adventure(this));
    getCommand("spectator").setExecutor(new Spectator(this));
    getCommand("survival").setExecutor(new Survival(this));
    getCommand("teleport").setExecutor(new Teleport(this));
    getCommand("tphere").setExecutor(new TPHere(this));
    getCommand("tpa").setExecutor(new TPA(this));
    getCommand("tpaccept").setExecutor(new TPA(this));
    getCommand("tpdeny").setExecutor(new TPA(this));
    getCommand("kill").setExecutor(new Murder(this));
    getCommand("enderchest").setExecutor(new EnderChest(this));
    getCommand("clear").setExecutor(new Clear(this));
    getCommand("day").setExecutor(new Day(this));
    getCommand("night").setExecutor(new Night(this));
    getCommand("rain").setExecutor(new Rain(this));
    getCommand("thunder").setExecutor(new Thunder(this));
    getCommand("godmode").setExecutor(new God(this));
    getCommand("flymode").setExecutor(new Fly(this));
    getCommand("heal").setExecutor(new Heal(this));
    getCommand("feed").setExecutor(new Feed(this));
    getCommand("gamemode").setExecutor(new Gamemode(this));
    getCommand("setspawn").setExecutor(new SetSpawn(this));
    getCommand("spawn").setExecutor(new Spawn(this));
    getCommand("home").setExecutor(new Home(this));
    getCommand("sethome").setExecutor(new SetHome(this));
    getCommand("invsee").setExecutor(new InvSee(this));
    getCommand("speed").setExecutor(new Speed(this));
    getCommand("clearinventory").setExecutor(new ClearInventory(this));
    getCommand("craft").setExecutor(new Craft(this));
    getCommand("enchant").setExecutor(new EnchantingTable(this));
    getCommand("discord").setExecutor(new Discord(this));
    getCommand("setwarp").setExecutor(new SetWarp(this));
    getCommand("warp").setExecutor(new Warp(this));

    //Events
    if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
        getServer().getPluginManager().registerEvents(new Join(this),  this);
        getServer().getPluginManager().registerEvents(new Leave(this), this);
    }
    getServer().getPluginManager().registerEvents(new OnDeath(this), this);
    getServer().getPluginManager().registerEvents(new OnePlayerSleep(this), this);

    //Config
    getConfig().options().copyDefaults(true);
    saveDefaultConfig();
    if (!getDataFolder().exists()) {
        getDataFolder().mkdirs();
    }

    //Custom Config
    try {
        SpawnConfig.setup();
        SpawnConfig.save();

        HomeConfig.setup();
        HomeConfig.save();

        WarpConfig.setup();
        WarpConfig.save();

        DiscordConfig.setup();
        DiscordConfig.save();
    } catch (IOException e) {
        e.printStackTrace();
    }

    SpawnConfig.get().addDefault("Spawn.x", "");
    SpawnConfig.get().addDefault("Spawn.y", "");
    SpawnConfig.get().addDefault("Spawn.z", "");
    SpawnConfig.get().addDefault("Spawn.yaw", "");
    SpawnConfig.get().addDefault("Spawn.pitch", "");
    SpawnConfig.get().addDefault("SpawnSet", false);
    SpawnConfig.get().options().copyDefaults(true);
    SpawnConfig.save();

    HomeConfig.get().options().copyDefaults(true);
    HomeConfig.save();

    WarpConfig.get().options().copyDefaults(true);
    WarpConfig.save();

    //DiscordConfig.get().addDefault("Discord.DiscordMessage", "&7Our discord server is: https://Example.com");
    //DiscordConfig.get().addDefault("Discord.token", "");
    //DiscordConfig.get().options().copyDefaults(true);
    //DiscordConfig.save();

    //GameRule
    getCommand("announceadvancements").setExecutor(new AnnounceAdvancements(this));
    getCommand("commandblockoutput").setExecutor(new CommandBlockOutput(this));
    getCommand("disableelytramovementcheck").setExecutor(new DisableElytraMovementCheck(this));
    getCommand("disableraids").setExecutor(new DisableRaids(this));
    getCommand("dodaylightcycle").setExecutor(new DoDaylightCycle(this));
    getCommand("doentitydrops").setExecutor(new DoEntityDrops(this));
    getCommand("dofiretick").setExecutor(new DoFireTick(this));
    getCommand("doimmediaterespawn").setExecutor(new DoImmediateRespawn(this));
    getCommand("doinsomnia").setExecutor(new DoInsomnia(this));
    getCommand("dolimitedcrafting").setExecutor(new DoLimitedCrafting(this));
    getCommand("domobloot").setExecutor(new DoMobLoot(this));
    getCommand("domobspawning").setExecutor(new DoMobSpawning(this));
    getCommand("dopatrolspawning").setExecutor(new DoPatrolSpawning(this));
    getCommand("dotiledrops").setExecutor(new DoTileDrops(this));
    getCommand("dotraderspawning").setExecutor(new DoTraderSpawning(this));
    getCommand("doweathercycle").setExecutor(new DoWeatherCycle(this));
    getCommand("drowningdamage").setExecutor(new DrowningDamage(this));
    getCommand("falldamage").setExecutor(new FallDamage(this));
    getCommand("firedamage").setExecutor(new FireDamage(this));
    getCommand("forgivedeadplayers").setExecutor(new ForgiveDeadPlayers(this));
    getCommand("freezedamage").setExecutor(new FreezeDamage(this));
    getCommand("keepinventory").setExecutor(new KeepInventory(this));
    getCommand("logadmincommands").setExecutor(new LogAdminCommands(this));
    getCommand("maxcommandchainlength").setExecutor(new MaxCommandChainLength(this));
    getCommand("maxentitycramming").setExecutor(new MaxEntityCramming(this));
    getCommand("mobgriefing").setExecutor(new MobGriefing(this));
    getCommand("naturalregeneration").setExecutor(new NaturalRegeneration(this));
    getCommand("playerssleepingpercentage").setExecutor(new PlayersSleepingPercentage(this));
    getCommand("randomtickspeed").setExecutor(new RandomTickSpeed(this));
    getCommand("reducedebuginfo").setExecutor(new ReduceDebugInfo(this));
    getCommand("sendcommandfeedback").setExecutor(new SendCommandFeedback(this));
    getCommand("showdeathmessages").setExecutor(new ShowDeathMessages(this));
    getCommand("spawnradius").setExecutor(new SpawnRadius(this));
    getCommand("spectatorsgeneratechunks").setExecutor(new SpectatorsGenerateChunks(this));
    getCommand("universalanger").setExecutor(new UniversalAnger(this));
    //Important
    getCommand("reload").setExecutor(new Reload(this));


    //Discord
    //try {
    //    new DiscordBot();
    //} catch (InterruptedException e) {
    //    e.printStackTrace();
    //}


    } //On enable bracket don't delete

    @Override
    public void onEvent(GenericEvent e) {
        if (e instanceof ReadyEvent) {
            Logger log = Bukkit.getLogger();
            log.info("Discord bot has turned on.");
        }
    }

    @Override
    public void onDisable() {

    }
}
