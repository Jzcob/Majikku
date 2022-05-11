package majikku.majikku.Commands;

import majikku.majikku.Files.HomeConfig;
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

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SetHome implements CommandExecutor {

    Majikku plugin;
    public SetHome(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (this.plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player)sender;
            String prefix = this.plugin.getConfig().getString("Settings.prefix");
            if (this.plugin.getConfig().getBoolean("Settings.Homes")) {
                if (p.hasPermission("majikku.sethome")) {
                    Location l = p.getLocation();
                    UUID uuid = p.getUniqueId();

                    HomeConfig.get().set("Homes." + uuid, uuid);
                    HomeConfig.get().set("Homes." + uuid + ".x", l.getX());
                    HomeConfig.get().set("Homes." + uuid + ".y", l.getY());
                    HomeConfig.get().set("Homes." + uuid + ".z", l.getZ());
                    HomeConfig.get().set("Homes." + uuid + ".yaw", l.getYaw());
                    HomeConfig.get().set("Homes." + uuid + ".pitch", l.getPitch());

                    HomeConfig.save();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7Home location set."));

                } else {
                    p.sendMessage(Variables.error + " " + Variables.perms);
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Homes are not enabled.");
                Logger log = Bukkit.getLogger();
                log.severe("[Majikku] If you would like Homes to be enabled, make sure that 'Homes' is set to true in the Majikku Config file.");
            }

        } else {
            sender.sendMessage(ChatColor.RED + "If you are a server administrator, please check the console.");
            Logger log = Bukkit.getLogger();
            log.info("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }

        return false;
    }
}
