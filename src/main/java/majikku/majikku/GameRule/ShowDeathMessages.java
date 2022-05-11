package majikku.majikku.GameRule;

import majikku.majikku.Important.Variables;
import majikku.majikku.Majikku;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class ShowDeathMessages implements CommandExecutor {
    Majikku plugin;

    public ShowDeathMessages(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (this.plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player)sender;
            World world = p.getWorld();
            String prefix = this.plugin.getConfig().getString("Settings.prefix");
            if (sender instanceof Player && (p.hasPermission("majikku.gamerule.showdeathmessages") || p.hasPermission(Variables.gamerule))) {
                if (args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7Usage /showdeathmessages <on/off>"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7The gamerule is currently set to &c" + p.getWorld().getGameRuleValue(GameRule.SHOW_DEATH_MESSAGES) + "&7."));
                } else if (args.length == 1 && args[0].equalsIgnoreCase("on")) {
                    world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, Boolean.valueOf(true));
                    Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', prefix + "&c" + p.getName() + " &7has &a&lENABLED&7 the gamerule &cShowDeathMessages&7."), Variables.gamerule);
                } else if (args.length == 1 && args[0].equalsIgnoreCase("off")) {
                    world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, Boolean.valueOf(false));
                    Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', prefix + "&c" + p.getName() + " &7has &c&lDISABLED&7 the gamerule &cShowDeathMessages&7."), Variables.gamerule);
                }
            } else {
                p.sendMessage(Variables.error + " " + Variables.perms);
            }

        } else {
            sender.sendMessage(ChatColor.RED + "If you are a server administrator, please check the console.");
            Logger log = Bukkit.getLogger();
            log.info("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }
        return false;
    }
}

