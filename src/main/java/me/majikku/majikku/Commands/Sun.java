package me.majikku.majikku.Commands;

import me.majikku.majikku.Important.Variables;
import me.majikku.majikku.Majikku;
import org.bukkit.ChatColor;
import org.bukkit.WeatherType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Sun implements CommandExecutor {

    Majikku plugin;
    public Sun(Majikku plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;
        if(plugin.getConfig().getBoolean("Updated")){
            String prefix = plugin.getConfig().getString("Settings." + "prefix");
            if(p.hasPermission("majikku.weather")){
                p.getWorld().setStorm(false);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have changed the weather to &c&lSUNNY&7."));
            } else {
                p.sendMessage(Variables.error + Variables.perms);
            }
        } else {
            sender.sendMessage(ChatColor.RED + "If you are a server administrator please check the console.");
            System.out.println("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }

        return false;
    }
}
