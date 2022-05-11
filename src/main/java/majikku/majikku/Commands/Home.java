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

public class Home implements CommandExecutor {

    Majikku plugin;
    public Home(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (this.plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player) sender;
            if (this.plugin.getConfig().getBoolean("Settings.Homes")){
                UUID uuid = p.getUniqueId();
                String prefix = this.plugin.getConfig().getString("Settings.prefix");
                if (!HomeConfig.get().contains("Homes." + uuid)) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You do not have set a home."));
                } else {
                    if (p.hasPermission("majikku.home")) {
                        double x = HomeConfig.get().getDouble("Homes." + uuid + ".x");
                        double y = HomeConfig.get().getDouble("Homes." + uuid + ".y");
                        double z = HomeConfig.get().getDouble("Homes." + uuid + ".z");
                        float yaw = (float) HomeConfig.get().getDouble("Homes." + uuid + ".yaw");
                        float pitch = (float) HomeConfig.get().getDouble("Homes." + uuid + ".pitch");
                        Location l = new Location(p.getWorld(), x, y, z , yaw, pitch);
                        p.teleport(l);
                    } else {
                        p.sendMessage(Variables.error + " " + Variables.perms);
                    }
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
