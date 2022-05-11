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

public class Spawn implements CommandExecutor {

    Majikku plugin;
    public Spawn(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
       if (this.plugin.getConfig().getBoolean("Updated")) {
           Player p = (Player) sender;
           String prefix = this.plugin.getConfig().getString("Settings.prefix");
           if (!SpawnConfig.get().getBoolean("SpawnSet")) {
               p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7Spawn has not been set."));
           } else {
               if (p.hasPermission("majikku.spawn")) {
                   double x = SpawnConfig.get().getDouble("Spawn.x");
                   double y = SpawnConfig.get().getDouble("Spawn.y");
                   double z = SpawnConfig.get().getDouble("Spawn.z");
                   float yaw = (float) SpawnConfig.get().getDouble("Spawn.yaw");
                   float pitch = (float) SpawnConfig.get().getDouble("Spawn.pitch");
                   Location l = new Location(p.getWorld(), x, y, z , yaw, pitch);
                   p.teleport(l);
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
