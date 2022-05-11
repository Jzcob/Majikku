package majikku.majikku.Commands;

import majikku.majikku.Files.SpawnConfig;
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

public class SetSpawn implements CommandExecutor {

    Majikku plugin;
    public SetSpawn(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (this.plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player)sender;
            String prefix = this.plugin.getConfig().getString("Settings.prefix");
            if (p.hasPermission("majikku.setspawn")) {
                Location l = p.getLocation();

                SpawnConfig.get().set("Spawn.x", l.getX());
                SpawnConfig.get().set("Spawn.y", l.getY());
                SpawnConfig.get().set("Spawn.z", l.getZ());
                SpawnConfig.get().set("Spawn.yaw", l.getYaw());
                SpawnConfig.get().set("Spawn.pitch", l.getPitch());
                SpawnConfig.get().set("SpawnSet", true);

                SpawnConfig.save();
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7Spawn location set."));

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
