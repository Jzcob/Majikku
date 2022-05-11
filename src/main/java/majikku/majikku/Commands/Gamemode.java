package majikku.majikku.Commands;

import majikku.majikku.Important.Variables;
import majikku.majikku.Majikku;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class Gamemode implements CommandExecutor {
    Majikku plugin;

    public Gamemode(Majikku plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String prefix = this.plugin.getConfig().getString("Settings.prefix");
        Player p = (Player)sender;
        if (this.plugin.getConfig().getBoolean("Updated")) {
            if (args.length == 0)
                if (p.hasPermission("majikku.gamemode.switcher") || p.hasPermission("majikku.gamemode.*")) {
                    if (p.getGameMode() == GameMode.CREATIVE) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have set your gamemode to &a&lSURVIVAL&7."));
                    } else if (p.getGameMode() == GameMode.SURVIVAL) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have set your gamemode to &c&lCREATIVE&7."));
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
