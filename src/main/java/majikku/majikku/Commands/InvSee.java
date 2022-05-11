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

public class InvSee implements CommandExecutor {
    Majikku plugin;
    public InvSee(Majikku plugin) {
        this.plugin = plugin;
    }
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (this.plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player) sender;
            if (p.hasPermission("majikku.invsee")) {
                if (args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou must specify a player."));
                } else if (args.length == 1) {
                    Player t = Bukkit.getPlayerExact(args[0]);
                    p.openInventory(t.getInventory());
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage /invsee {name}."));
                }
            } else {
                p.sendMessage(Variables.error + " " + Variables.perms);
            }
        } else {
            sender.sendMessage(ChatColor.RED + "If you are a server administrator, please check the console.");
            Logger log = Bukkit.getLogger();
            log.info("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }

        return false;
    }
}
