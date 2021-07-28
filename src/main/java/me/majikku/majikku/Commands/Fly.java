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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Fly implements CommandExecutor {
    Majikku plugin;
    public Fly(Majikku plugin) {this.plugin = plugin;}

    ArrayList<Player> fly = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player) sender;
            String prefix = plugin.getConfig().getString("Settings." + "prefix");
            if (p.hasPermission("majikku.flymode")) {
                if (args.length == 0) {
                    if (p.getAllowFlight() == true) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have &c&lDISABLED&7 Fly Mode."));
                        fly.remove(p);
                        p.setAllowFlight(false);
                    } else {
                        fly.add(p);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have &a&lENABLED&7 Fly Mode&7."));
                        p.setAllowFlight(true);
                    }
                } else if (args.length == 1) {
                    if (p.hasPermission("majikku.flymode.other")) {
                        Player t = Bukkit.getPlayerExact(args[0]);
                        if (fly.contains(t)) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have &c&lDISABLED&7 Fly Mode for &c" + t.getName() + "&7."));
                            t.setAllowFlight(false);
                            if (plugin.getConfig().getBoolean("Settings." + "Notify." + "notify-target")) {
                                fly.remove(t);
                                t.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have been put into Fly Mode."));
                            } else if (plugin.getConfig().getBoolean("Settings." + "Notify." + "notify-who-did-it")) {
                                fly.add(t);
                                t.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have been put into Fly Mode by &c" + p.getName() + "&7."));
                            }
                        } else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have &a&lENABLED&7 Fly Mode for &c" + t.getName() + "&7."));
                            t.setAllowFlight(true);
                        }
                    } else {
                        p.sendMessage(Variables.error + Variables.perms);
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Variables.error + "&7To many arguments."));
                }
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
