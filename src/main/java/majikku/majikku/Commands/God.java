package majikku.majikku.Commands;

import java.util.ArrayList;
import java.util.logging.Logger;

import majikku.majikku.Important.Variables;
import majikku.majikku.Majikku;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class God implements CommandExecutor {
    Majikku plugin;

    public static ArrayList<Player> g;

    public God(Majikku plugin) {
        this.g = new ArrayList<>();
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (this.plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player)sender;
            String prefix = this.plugin.getConfig().getString("Settings.prefix");
            if (p.hasPermission("majikku.godmode")) {
                if (args.length == 0) {
                    if (p.isInvulnerable()) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have &c&lDISABLED&7 God Mode."));
                        this.g.remove(p);
                        p.setInvulnerable(false);
                    } else {
                        this.g.add(p);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have &a&lENABLED&7 God Mode&7."));
                        p.setInvulnerable(true);
                    }
                } else if (args.length == 1) {
                    if (p.hasPermission("majikku.godmode.other")) {
                        Player t = Bukkit.getPlayerExact(args[0]);
                        if (t.isInvulnerable()) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have &c&lDISABLED&7 God Mode for &c" + t.getName() + "&7."));
                            t.setInvulnerable(false);
                            if (this.plugin.getConfig().getBoolean("Settings.Notify.notify-target")) {
                                this.g.remove(t);
                                t.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have been put into God Mode."));
                            } else if (this.plugin.getConfig().getBoolean("Settings.Notify.notify-who-did-it")) {
                                this.g.add(t);
                                t.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have been put into God Mode by &c" + p.getName() + "&7."));
                            }
                        } else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have &a&lENABLED&7 God Mode for &c" + t.getName() + "&7."));
                            t.setInvulnerable(true);
                        }
                    } else {
                        p.sendMessage(Variables.error + " " + Variables.perms);
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Variables.error + " &7To many arguments."));
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
