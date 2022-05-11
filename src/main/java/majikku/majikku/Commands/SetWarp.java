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

public class SetWarp implements CommandExecutor {

    Majikku plugin;

    public SetWarp(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (this.plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player)sender;
            String prefix = this.plugin.getConfig().getString("Settings.prefix");
            if (p.hasPermission("majikku.setwarp")) {
                if (args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7Please provide a name for your warp."));
                } else {
                    Location l = p.getLocation();
                    String name = args[0].toLowerCase();
                    WarpConfig.get().set("Warp."+ name, name);
                    WarpConfig.get().set("Warp." +  name + ".x", l.getX());
                    WarpConfig.get().set("Warp." +  name + ".y", l.getY());
                    WarpConfig.get().set("Warp." +  name + ".z", l.getZ());
                    WarpConfig.get().set("Warp." +  name + ".yaw", l.getYaw());
                    WarpConfig.get().set("Warp." +  name + ".pitch", l.getPitch());

                    WarpConfig.save();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7Warp &c" + name  + "&7 set."));
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
