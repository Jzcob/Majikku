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

import java.util.logging.Level;
import java.util.logging.Logger;

public class Discord implements CommandExecutor {

    Majikku plugin;

    public Discord(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player)sender;
        if (this.plugin.getConfig().getBoolean("Updated")) {
            String prefix = this.plugin.getConfig().getString("Settings.prefix");
            String message = this.plugin.getConfig().getString("Settings.DiscordMessage");
            if (this.plugin.getConfig().getBoolean("Settings.DiscordMessage")) {
                if (p.hasPermission("majikku.discord")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
                } else {
                    p.sendMessage(Variables.error + " " + Variables.perms);
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Discord is not enabled.");
                Logger log = Bukkit.getLogger();
                log.severe("[Majikku] If you would like Discord to be enabled, make sure that 'Discord' is set to true in the Majikku Config file.");
            }

        } else {
            sender.sendMessage(ChatColor.RED + "If you are a server administrator, please check the console.");
            Logger log = Bukkit.getLogger();
            log.info("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }
        return false;
    }
}
