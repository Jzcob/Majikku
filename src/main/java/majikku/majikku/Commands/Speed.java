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

public class Speed implements CommandExecutor {

    Majikku plugin;

    public Speed(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if (this.plugin.getConfig().getBoolean("Updated")) {
            String prefix = this.plugin.getConfig().getString("Settings.prefix");
            float speed = Float.parseFloat(args[0]);
            if (p.hasPermission("majikku.speed")) {
                if (speed < 1 || speed > 10) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7Please provide a speed from 1-10"));
                    return false;
                }
                if (p.isFlying()) {
                    p.setFlySpeed(speed / 10);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7Fly speed set to &c" + speed + "&7."));
                } else {
                    p.setWalkSpeed(speed / 10);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7Walk speed set to &c" + speed + "&7."));
                }
            } else {
                p.sendMessage(Variables.error + " " + Variables.perms);
                return false;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "If you are a server administrator, please check the console.");
            Logger log = Bukkit.getLogger();
            log.info("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }


        return false;
    }
}
