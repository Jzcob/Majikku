package majikku.majikku.HelpOP;

import majikku.majikku.Important.Variables;
import majikku.majikku.Majikku;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class Response implements CommandExecutor {
    Majikku plugin;

    public Response(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (this.plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player)sender;
            String prefix = this.plugin.getConfig().getString("Settings.prefix");
            String help = this.plugin.getConfig().getString("Settings.Chat.helpchat-prefix");
            String color = this.plugin.getConfig().getString("Settings.Chat.helpchat-message-color");
            if (p.hasPermission("majikku.helpop.respond")) {
                if (args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cYou need to have a name and a message."));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7/answer (name) (message)"));
                } else if (args.length == 1) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&cYou need to have a name and a message."));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7/answer (name) (message)"));
                } else if (args.length >= 2) {
                    Player ask = Bukkit.getPlayerExact(args[0]);
                    String message = "&c" + p.getName() + "» &7&c" + ask.getName() + "&7 " + color;
                    for (int i = 1; i < args.length; i++)
                        message = ChatColor.translateAlternateColorCodes('&', message + args[i] + " ");
                    if (ask == p) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are sending a message to yourself..."));
                    } else if (ask.hasPermission("majikku.helpop.see")) {
                        ask.sendMessage(message);
                    } else {
                        Bukkit.broadcast(message, "majikku.helpop.see");
                        ask.sendMessage(message);
                    }
                }
            } else {
                p.sendMessage(Variables.error + Variables.perms);
            }
        } else {
            sender.sendMessage(ChatColor.RED + "If you are a server administrator, please check the console.");
            Logger log = Bukkit.getLogger();
            log.info("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }
        return false;
    }
}
