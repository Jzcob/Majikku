package majikku.majikku.Commands;

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

public class EnderChest implements CommandExecutor {
    Majikku plugin;

    public EnderChest(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (this.plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player)sender;
            if (sender instanceof Player) {
                String prefix = this.plugin.getConfig().getString("Settings.prefix");
                if (args.length == 0) {
                    if (p.hasPermission("majikku.enderchest")) {
                        p.openInventory(p.getEnderChest());
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have opened your Enderchest"));
                    } else {
                        p.sendMessage(Variables.error + " " + Variables.perms);
                    }
                } else if (args.length == 1) {
                    if (p.hasPermission("majikku.enderchest.other")) {
                        Player t = Bukkit.getPlayerExact(args[0]);
                        if (t == null) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7That player doesn't exist."));
                        } else {
                            p.openInventory(t.getEnderChest());
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have opened &c" + t.getName() + "&7's enderchest."));
                        }
                    } else {
                        p.sendMessage(Variables.error + " " + Variables.perms);
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You must be a player to do this!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "If you are a server administrator, please check the console.");
            Logger log = Bukkit.getLogger();
            log.info("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }
        return false;
    }
}
