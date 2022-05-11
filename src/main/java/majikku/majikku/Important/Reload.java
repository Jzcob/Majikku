package majikku.majikku.Important;

import majikku.majikku.Files.HomeConfig;
import majikku.majikku.Files.SpawnConfig;
import majikku.majikku.Files.WarpConfig;
import majikku.majikku.Majikku;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor {
    Majikku plugin;

    public Reload(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;
        if (p.hasPermission("majikku.*")) {
            if (this.plugin.getConfig().getBoolean("Updated")) {
                this.plugin.reloadConfig();
                WarpConfig.reload();
                HomeConfig.reload();
                SpawnConfig.reload();
                this.plugin.saveConfig();
                WarpConfig.save();
                HomeConfig.save();
                SpawnConfig.save();
                String prefix = this.plugin.getConfig().getString("Settings.prefix");
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have reloaded the plugin!"));
            } else {
                p.sendMessage(ChatColor.RED + "There is no config file loaded on the server.");
            }
        } else {
            p.sendMessage(Variables.perms);
        }
        return false;
    }
}
