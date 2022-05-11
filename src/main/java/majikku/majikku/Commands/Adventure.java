package majikku.majikku.Commands;

import majikku.majikku.Important.Variables;
import majikku.majikku.Majikku;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class Adventure implements CommandExecutor {
    Majikku plugin;

    public Adventure(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;
        if (this.plugin.getConfig().getBoolean("Updated")) {
            if (args.length == 0) {
                if (p.hasPermission("majikku.gamemode.adventure") || p.hasPermission("majikku.gamemode.*")) {
                    p.setGameMode(GameMode.ADVENTURE);
                    String prefix = this.plugin.getConfig().getString("Settings.prefix");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have set your gamemode to &b&lADVENTURE&7."));
                } else {
                    p.sendMessage(Variables.error + " " + Variables.perms);
                }
            } else if (p.hasPermission("majikku.gamemode.adventure.other") || p.hasPermission("majikku.gamemode.*")) {
                Player t = Bukkit.getPlayerExact(args[0]);
                t.setGameMode(GameMode.ADVENTURE);
                String prefix = this.plugin.getConfig().getString("Settings.prefix");
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have set &c" + t.getName() + "&7 to &b&lADVENTURE&7."));
                if (this.plugin.getConfig().getBoolean("Settings.Notify.notify-target")) {
                    t.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7Your gamemode has been set to &b&lADVENTURE&7."));
                } else if (this.plugin.getConfig().getBoolean("Settings.Notify.notify-who-did-it")) {
                    t.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&c" + p.getName() + "&7 updated your gamemode to &b&lADVENTURE&7."));
                } else {
                    return false;
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
