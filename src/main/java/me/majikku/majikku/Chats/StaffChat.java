package me.majikku.majikku.Chats;

import me.majikku.majikku.Important.Variables;
import me.majikku.majikku.Majikku;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StaffChat implements CommandExecutor {

    Majikku plugin;
    public StaffChat(Majikku plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player) sender;
            String staff = plugin.getConfig().getString("Settings." + "Chat." + "staffchat-prefix");
            String color = plugin.getConfig().getString("Settings." + "Chat." + "staffchat-message-color");
            if (p.hasPermission("majikku.staffchat")) {
                if (args.length == 0) {
                    p.sendMessage(ChatColor.GRAY + "/sc (message)");
                } else if (args.length >= 1) {
                    String message = staff + " " + p.getName() + "&7 » " + color;
                    for (String s : args) {
                        message = ChatColor.translateAlternateColorCodes('&', message + s + " ");
                    }
                    Bukkit.broadcast(message, "majikku.staffchat");
                }
            } else {
                p.sendMessage(Variables.perms);
            }
        } else {
            sender.sendMessage(ChatColor.RED + "If you are a server administrator please check the console.");
            System.out.println("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }
        return false;
    }
}