package majikku.majikku.Commands;

import majikku.majikku.Important.Variables;
import majikku.majikku.Majikku;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class Thunder implements CommandExecutor {
    Majikku plugin;

    public Thunder(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player)sender;
        if (this.plugin.getConfig().getBoolean("Updated")) {
            String prefix = this.plugin.getConfig().getString("Settings.prefix");
            if (p.hasPermission("majikku.weather")) {
                p.getWorld().setStorm(true);
                p.getWorld().setThundering(true);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have changed the weather to &c&lTHUNDER&7."));
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
