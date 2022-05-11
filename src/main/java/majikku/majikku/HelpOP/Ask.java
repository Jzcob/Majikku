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

public class Ask implements CommandExecutor {
    Majikku plugin;

    public Ask(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (this.plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player)sender;
            String prefix = this.plugin.getConfig().getString("Settings.prefix");
            String help = this.plugin.getConfig().getString("Settings.Chat.helpchat-prefix");
            String color = this.plugin.getConfig().getString("Settings.Chat.helpchat-message-color");
            if (p.hasPermission("majikku.helpop")) {
                if (args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "Use /helpop (message) to talk to staff members!"));
                } else if (args.length >= 1) {
                    String message = help + " " + p.getName() + "&7 " + color;
                    for (String s : args)
                        message = ChatColor.translateAlternateColorCodes('&', message + s + " ");
                    Bukkit.broadcast(message, "majikku.helpop.see");
                    if (!p.hasPermission("majikku.helpop.see")) {
                        p.sendMessage(message);
                    } else {
                        return true;
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