package majikku.majikku.Commands;

import majikku.majikku.Files.SpawnConfig;
import majikku.majikku.Files.WarpConfig;
import majikku.majikku.Important.Variables;
import majikku.majikku.Majikku;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class Warp implements CommandExecutor {

    Majikku plugin;

    public Warp(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (this.plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player) sender;
            String name = args[0].toLowerCase();
            String prefix = this.plugin.getConfig().getString("Settings.prefix");
            if (args.length == 0) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7Please specify the warp."));
            } else {
                if (!WarpConfig.get().contains("Warp." + name)) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7That warp does not exist."));
                } else {
                    if (p.hasPermission("majikku.warp")) {
                        double x = WarpConfig.get().getDouble("Warp." + name + ".x");
                        double y = WarpConfig.get().getDouble("Warp." + name + ".y");
                        double z = WarpConfig.get().getDouble("Warp." + name + ".z");
                        float yaw = (float) WarpConfig.get().getDouble("Warp." + name + ".yaw");
                        float pitch = (float) WarpConfig.get().getDouble("Warp." + name + ".pitch");
                        Location l = new Location(p.getWorld(), x, y, z , yaw, pitch);
                        p.teleport(l);
                    } else {
                        p.sendMessage(Variables.error + " " + Variables.perms);
                    }
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
