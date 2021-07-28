package me.majikku.majikku.Commands;

import me.majikku.majikku.Important.Variables;
import me.majikku.majikku.Majikku;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spectator implements CommandExecutor {

    Majikku plugin;
    public Spectator(Majikku plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (plugin.getConfig().getBoolean("Updated")) {
            if (args.length == 0) {
                if (p.hasPermission("majikku.gamemode.spectator") || p.hasPermission("majikku.gamemode.*")) {
                    p.setGameMode(GameMode.SPECTATOR);
                    String prefix = plugin.getConfig().getString("Settings." + "prefix");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have set your gamemode to &e&lSPECTATOR&7."));
                } else {
                    p.sendMessage(Variables.error + Variables.perms);
                }
            } else {
                if (p.hasPermission("majikku.gamemode.spectator.other") || p.hasPermission("majikku.gamemode.*")) {
                    Player t = Bukkit.getPlayerExact(args[0]);
                    t.setGameMode(GameMode.SPECTATOR);
                    String prefix = plugin.getConfig().getString("Settings." + "prefix");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have set &c" + t.getName() + "&7 to &e&lSPECTATOR&7."));
                    if (plugin.getConfig().getBoolean("Settings." + "Notify." + "notify-target")) {
                        t.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7Your gamemode has been set to &e&lSPECTATOR&7."));
                    } else if (plugin.getConfig().getBoolean("Settings." + "Notify." + "notify-who-did-it")) {
                        t.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&c" + p.getName() + "&7 updated your gamemode to &e&lSPECTATOR&7."));
                    } else {
                        return false;
                    }
                } else {
                    p.sendMessage(Variables.error + Variables.perms);
                }
            }

        } else {
            sender.sendMessage(ChatColor.RED + "If you are a server administrator please check the console.");
            System.out.println("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }

        return false;
    }
}