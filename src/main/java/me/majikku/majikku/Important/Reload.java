package me.majikku.majikku.Important;

import me.majikku.majikku.Majikku;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor {

    Majikku plugin;
    public Reload(Majikku plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission("majikku.*")) {
            if (plugin.getConfig().getBoolean("Updated")) {
                plugin.reloadConfig();
                plugin.saveConfig();
                String prefix = plugin.getConfig().getString("Settings." + "prefix");
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',  prefix + "&7You have reloaded the plugin!"));
            } else {
                p.sendMessage(ChatColor.RED + "There is no config file loaded on the server.");
            }
        } else {
            p.sendMessage(Variables.perms);
        }
        return false;
    }
}
