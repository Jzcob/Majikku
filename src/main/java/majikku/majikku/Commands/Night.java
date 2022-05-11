package majikku.majikku.Commands;

import majikku.majikku.Important.Variables;
import majikku.majikku.Majikku;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class Night implements CommandExecutor {

    Majikku plugin;

    public Night(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (this.plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player) sender;
            if (args.length == 0) {
                if (p.hasPermission("minestack.admin")) {
                    p.getLocation().getWorld().setTime(13000);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have set the time to &cNight"));
                } else {
                    p.sendMessage(Variables.error + " " + Variables.perms);
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "If you are a server administrator, please check the console.");
            Logger log = Bukkit.getLogger();
            log.info("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }
        return false;
    }
}
