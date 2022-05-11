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

public class TPHere implements CommandExecutor {

    Majikku plugin;

    public TPHere(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        Player t = Bukkit.getPlayerExact(args[0]);
        if (this.plugin.getConfig().getBoolean("Updated")) {
            if (p.hasPermission("majikku.teleport.here")){
                t.teleport(p);
                t.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have been teleported to &c" + p.getDisplayName() + "&7."));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have teleported &c" + t.getDisplayName() + " &7to you."));
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
