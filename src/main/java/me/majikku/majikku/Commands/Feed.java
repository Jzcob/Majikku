package me.majikku.majikku.Commands;

import me.majikku.majikku.Majikku;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Feed implements CommandExecutor {

    Majikku plugin;
    public Feed(Majikku plugin) {this.plugin = plugin;}


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player) sender;
            String prefix = plugin.getConfig().getString("Settings." + "prefix");
            if (p.hasPermission("majikku.feed")) {
                if (args.length == 0) {
                    p.setFoodLevel(20);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have fed your appetite"));
                } else if (args.length == 1) {
                    if (p.hasPermission("majikku.feed.others")) {
                        Player t = Bukkit.getPlayerExact(args[0]);
                        t.setFoodLevel(20);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have fed &c" + t.getName() + "&7."));
                        t.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + "&7You have been fed."));
                    }
                }

            }
        } else {
            sender.sendMessage(ChatColor.RED + "If you are a server administrator please check the console.");
            System.out.println("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }
        return false;
    }
}
