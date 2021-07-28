package me.majikku.majikku;

import me.majikku.majikku.Chats.*;
import me.majikku.majikku.Chats.HelpOP.Ask;
import me.majikku.majikku.Chats.HelpOP.Response;
import me.majikku.majikku.Commands.*;
import me.majikku.majikku.Events.Join;
import me.majikku.majikku.Events.Leave;
import me.majikku.majikku.Files.*;
import me.majikku.majikku.GameRule.*;
import me.majikku.majikku.Important.Reload;
import org.bukkit.plugin.java.JavaPlugin;

public final class Majikku extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[Majikku] Welsh dragon, BOOST!");
        //Chats
        getCommand("adminchat").setExecutor(new AdminChat(this));
        getCommand("builderchat").setExecutor(new BuilderChat(this));
        getCommand("moderatorchat").setExecutor(new ModeratorChat(this));
        getCommand("ownerchat").setExecutor(new OwnerChat(this));
        getCommand("staffchat").setExecutor(new StaffChat(this));
        getCommand("helpop").setExecutor(new Ask(this));
        getCommand("answer").setExecutor(new Response(this));
        //Commands
        getCommand("creative").setExecutor(new Creative(this));
        getCommand("adventure").setExecutor(new Adventure(this));
        getCommand("spectator").setExecutor(new Spectator(this));
        getCommand("survival").setExecutor(new Survival(this));
        getCommand("teleport").setExecutor(new Teleport(this));
        getCommand("kill").setExecutor(new Murder(this));
        getCommand("enderchest").setExecutor(new EnderChest(this));
        getCommand("sun").setExecutor(new Sun(this));
        getCommand("rain").setExecutor(new Rain(this));
        getCommand("thunder").setExecutor(new Thunder(this));
        getCommand("godmode").setExecutor(new God(this));
        getCommand("flymode").setExecutor(new Fly(this));
        getCommand("heal").setExecutor(new Heal(this));
        getCommand("feed").setExecutor(new Feed(this));
        getCommand("gamemode").setExecutor(new Gamemode(this));

        //Events
        if (getServer().getPluginManager().isPluginEnabled("PlaceHolderAPI"))
            getServer().getPluginManager().registerEvents(new Join(this), this);
            getServer().getPluginManager().registerEvents(new Leave(this), this);


        //Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Custom Files
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        //this.playerData = new PlayerData(this);
        //this.spawnfile = new SpawnFile(this);

        //GameRules
        getCommand("announceadvancements").setExecutor(new AnnounceAdvancements(this));
        getCommand("commandblockoutput").setExecutor(new CommandBlockOutput(this));
        getCommand("disableelytramovementcheck").setExecutor(new DisableElytraMovementCheck(this));
        getCommand("disableraids").setExecutor(new DisableRaids(this));
        getCommand("dodaylightcycle").setExecutor(new DoDaylightCycle(this));
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
        getCommand("keepinventory").setExecutor(new KeepInventory(this));
        getCommand("logadmincommands").setExecutor(new LogAdminCommands(this));
        getCommand("maxcommandchainlength").setExecutor(new MaxCommandChainLength(this));
        getCommand("maxentitycramming").setExecutor(new MaxEntityCramming(this));
        getCommand("mobgriefing").setExecutor(new MobGriefing(this));
        getCommand("naturalregeneration").setExecutor(new NaturalRegeneration(this));
        getCommand("randomtickspeed").setExecutor(new RandomTickSpeed(this));
        getCommand("reducedebuginfo").setExecutor(new ReduceDebugInfo(this));
        getCommand("sendcommandfeedback").setExecutor(new SendCommandFeedback(this));
        getCommand("showdeathmessages").setExecutor(new ShowDeathMessages(this));
        getCommand("spawnradius").setExecutor(new SpawnRadius(this));
        getCommand("spectatorsgeneratechunks").setExecutor(new SpectatorsGenerateChunks(this));
        getCommand("universalanger").setExecutor(new UniversalAnger(this));


        //Important
        getCommand("reload").setExecutor(new Reload(this));
        //
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[Majikku] Don't reload because it can sometimes corrupt files!");
    }
}
