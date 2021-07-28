package me.majikku.majikku.Commands;

import me.majikku.majikku.Important.Variables;
import me.majikku.majikku.Majikku;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Teleport implements CommandExecutor {

    Majikku plugin;
    public Teleport(Majikku plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        String prefix = plugin.getConfig().getString("Settings." + "prefix");
        String tp = "majikku.teleport.*";
        if (plugin.getConfig().getBoolean("Updated")) {
            if (sender instanceof Player) {
                if (p.hasPermission("majikku.teleport") || p.hasPermission(tp)) {
                    if (args.length == 0) {
                        p.sendMessage(ChatColor.RED + "You must specify player");
                    } else if (args.length == 1) {
                        Player t = Bukkit.getPlayerExact(args[0]);
                        p.teleport(t);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have been teleported to &c" + t.getName() + "&7."));
                        if (plugin.getConfig().getBoolean("Settings." + "Notify." + "notify-who-did-it")) {
                            t.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&c" + p.getName() + "&7 has teleported to you."));
                        }
                    } else if (args.length == 2) {
                        if (p.hasPermission("majikku.teleport.other") || p.hasPermission(tp)) {
                            Player t = Bukkit.getPlayerExact(args[0]);
                            Player t2 = Bukkit.getPlayerExact(args[1]);
                            t.teleport(t2);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have teleported &c" + t.getName() + " &7to &c" + t2.getName() + "&7."));
                            if (plugin.getConfig().getBoolean("Settings." + "Notify." + "notify-target")) {
                                t.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have been teleported to &c" + t2.getName() + "&7."));
                            } else if (plugin.getConfig().getBoolean("Settings." + "Notify." + "notify-who-did-it")) {
                                t2.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&c" + t.getName() + " &7has teleported to you."));
                            }
                        } else {
                            p.sendMessage(Variables.error + Variables.perms);
                        }
                    } else if (args.length == 3) {
                        if (p.hasPermission("majikku.teleport.xyz") || p.hasPermission(tp)) {
                            double x = Double.parseDouble(args[0]);
                            double y = Double.parseDouble(args[1]);
                            double z = Double.parseDouble(args[2]);
                            Location l = new Location(p.getWorld(),x, y, z);
                            p.teleport(l);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have been teleported to &c" + x + "&7, &c" + y + "&7, &c" + z + "&7."));
                        } else {
                            p.sendMessage(Variables.error + Variables.perms);
                        }
                    } else if (args.length == 4) {
                        if (p.hasPermission("majikku.teleport.xyz.other") || p.hasPermission(tp)) {
                            Player t = Bukkit.getPlayerExact(args[0]);
                            double x = Double.parseDouble(args[1]);
                            double y = Double.parseDouble(args[2]);
                            double z = Double.parseDouble(args[3]);
                            Location l = new Location(p.getWorld(), x, y, z);
                            t.teleport(l);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have teleported &c" + t.getName() + " &7to &c" + x + ", " + y + ", " + z + "."));
                            if (plugin.getConfig().getBoolean("Settings." + "Notify." + "notify target")) {
                                t.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have been teleported to &c" + x + "&7, &c" + y + "&7, &c" + z + "&7."));
                            } else if (plugin.getConfig().getBoolean("Settings." + "Notify." + "notify-who-did-it")) {
                                t.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&c" + p.getName() + " &7has teleported you to &c" + x + "&7, &c" + y + "&7, &c" + z + "&7."));
                            }
                        } else {
                            p.sendMessage(Variables.error + Variables.perms);
                        }
                    } else if (args.length == 5) {
                        if (p.hasPermission("majikku.teleport.xyzpy") || p.hasPermission(tp)) {
                            double x = Double.parseDouble(args[0]);
                            double y = Double.parseDouble(args[1]);
                            double z = Double.parseDouble(args[2]);
                            float pitch = Float.parseFloat(args[3]);
                            float yaw = Float.parseFloat(args[4]);
                            Location l = new Location(p.getWorld(), x, y, z, pitch, yaw);
                            p.teleport(l);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have been teleported to &c" + x + "&7, &c" + y + "&7, &c" + z + "&7 with a pitch of &c" + pitch + "&7 and a yaw of &c" + yaw + "&7."));
                        } else {
                            p.sendMessage(Variables.error + Variables.perms);
                        }
                    } else if (args.length == 6) {
                        if (p.hasPermission("majikku.teleport.xyzpy.other") || p.hasPermission(tp)) {
                            Player t = Bukkit.getPlayerExact(args[0]);
                            double x = Double.parseDouble(args[1]);
                            double y = Double.parseDouble(args[2]);
                            double z = Double.parseDouble(args[3]);
                            float pitch = Float.parseFloat(args[4]);
                            float yaw = Float.parseFloat(args[5]);
                            Location l = new Location(p.getWorld(), x, y, z, pitch, yaw);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have teleported &c" + t.getName() + " &7to &c" + x + "&7, &c" + y + "&7, &c" + z + "&7 with a pitch of &c" + pitch + "&7 and a yaw of &c" + yaw + "&7."));
                            if (plugin.getConfig().getBoolean("Settings." + "Notify." + "notify target")) {
                                t.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have been teleported to &c" + x + "&7, &c" + y + "&7, &c" + z + "&7 with a pitch of &c" + pitch + "&7 and a yaw of &c" + yaw + "&7."));
                            } else if (plugin.getConfig().getBoolean("Settings." + "Notify." + "notify-who-did-it")) {
                                t.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&c" + p.getName() + " &7has teleported you to &c" + x + "&7, &c" + y + "&7, &c" + z + "&7 with a pitch of &c" + pitch + "&7 and a yaw of &c" + yaw + "&7."));
                            }
                        } else {
                            p.sendMessage(Variables.error + Variables.perms);
                        }
                    }
                } else {
                    p.sendMessage(Variables.error + Variables.perms);
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Variables.error + "You are not a player!"));
            }
        } else {
            sender.sendMessage(ChatColor.RED + "If you are a server administrator please check the console.");
            System.out.println("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }
        return false;
    }
}
