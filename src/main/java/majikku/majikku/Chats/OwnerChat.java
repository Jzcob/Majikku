package majikku.majikku.Chats;

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

public class OwnerChat implements CommandExecutor {
    Majikku plugin;

    public OwnerChat(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (this.plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player)sender;
            String owner = this.plugin.getConfig().getString("Settings.Chat.ownerchat-prefix");
            String color = this.plugin.getConfig().getString("Settings.Chat.ownerchat-message-color");
            if (p.hasPermission("majikku.ownerchat")) {
                if (args.length == 0) {
                    p.sendMessage(ChatColor.GRAY + "/oc (message)");
                } else if (args.length >= 1) {
                    String message = owner + p.getName() + "&7 » " + color;
                    for (String s : args)
                        message = ChatColor.translateAlternateColorCodes('&', message + s + " ");
                    Bukkit.broadcast(message, "majikku.ownerchat");
                }
            } else {
                p.sendMessage(Variables.perms);
            }
        } else {
            sender.sendMessage(ChatColor.RED + "If you are a server administrator, please check the console.");
            Logger log = Bukkit.getLogger();
            log.info("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }
        return false;
    }
}
