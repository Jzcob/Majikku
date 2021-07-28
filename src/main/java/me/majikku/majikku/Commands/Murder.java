package me.majikku.majikku.Commands;

//import me.majikku.majikku.Files.PlayerData;
import me.majikku.majikku.Important.Variables;
import me.majikku.majikku.Majikku;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Murder implements CommandExecutor {

    Majikku plugin;
    public Murder(Majikku plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (plugin.getConfig().getBoolean("Updated")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                String prefix = plugin.getConfig().getString("Settings." + "prefix");
                if (p.hasPermission("majikku.murder")) {
                    if (args.length == 0) {
                        p.setHealth(0);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7Oh no! You have died!"));
                    } else if (args.length == 1) {
                        if (p.hasPermission("majikku.murder.other")) {
                            Player t = Bukkit.getPlayerExact(args[0]);
                            t.setHealth(0);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have killed &c" + t.getName() + "&7."));
                            if (plugin.getConfig().getBoolean("Settings." + "Notify." + "notify-target")) {
                                t.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + "&7You have been killed, but I don't know who did it. Sorry."));
                            } else if (plugin.getConfig().getBoolean("Settings." + "Notify." + "notify-who-did-it")) {
                                t.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have been killed by &c" + p.getName() + "&7."));
                            }
                        } else {
                            p.sendMessage(Variables.error + Variables.perms);
                        }
                    }
                } else {
                    p.sendMessage(Variables.error + Variables.perms);
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You are not a player!");
            }
        } else {
        sender.sendMessage(ChatColor.RED + "If you are a server administrator please check the console.");
        System.out.println("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }

        return false;
    }
}
