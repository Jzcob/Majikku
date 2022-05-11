package majikku.majikku.Commands;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import majikku.majikku.Important.Variables;
import majikku.majikku.Majikku;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPA implements CommandExecutor {

    Majikku plugin;

    public TPA(Majikku plugin) {
        this.plugin = plugin;
    }

    private static HashMap<UUID, UUID> requests = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (this.plugin.getConfig().getBoolean("Updated")) {
            if (this.plugin.getConfig().getBoolean("Settings.TPA")){
                Player player = (Player) sender;
                if (player.hasPermission("majikku.tpa")) {
                    if (command.getName().equalsIgnoreCase("tpa")) {
                        if (args[0] == player.getName()){
                            player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§7You can not teleport to yourself."));
                        }
                        if (args.length != 1) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§cUsage: /tpa <player>"));
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            requests.put(target.getUniqueId(), player.getUniqueId());
                            player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§7You sent a teleport request to§c " + target.getName() + "§7."));
                            target.sendMessage(ChatColor.translateAlternateColorCodes('§', "§c" + player.getName() + " §7sent a teleport request to you."));
                            target.sendMessage(ChatColor.translateAlternateColorCodes('§', "§7Type §c/tpaccept §7to accept the teleport request, or §c/tpdeny §7to deny the request."));
                            return true;
                        }
                        player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§7The player is offline."));
                    }
                    if (command.getName().equalsIgnoreCase("tpaccept")) {
                        if (requests.containsKey(player.getUniqueId())) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§7You accepted the teleport request."));
                            Bukkit.getPlayer(requests.get(player.getUniqueId())).sendMessage(ChatColor.translateAlternateColorCodes('§', "§c" +  player.getName() + " §7accepted the teleport request."));
                            Bukkit.getPlayer(requests.get(player.getUniqueId())).teleport(player);
                            requests.remove(player.getUniqueId());
                            return true;
                        }
                        player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§cThere's no request to accept."));
                    }
                    if (command.getName().equalsIgnoreCase("tpdeny")) {
                        if (requests.containsKey(player.getUniqueId())) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§7You denied the teleport request."));
                            Bukkit.getPlayer(requests.get(player.getUniqueId())).sendMessage(ChatColor.translateAlternateColorCodes('§', "§c" +  player.getName() + " §7denied the teleport request."));
                            requests.remove(player.getUniqueId());
                            return true;
                        }
                        player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§cThere's no request to deny."));
                    }
                } else {
                    player.sendMessage(Variables.error + " " + Variables.perms);
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "TPA is not enabled.");
                Logger log = Bukkit.getLogger();
                log.severe("[Majikku] If you would like TPA to be enabled, make sure that 'TPA' is set to true in the Majikku Config file.");
            }

        } else {
            sender.sendMessage(ChatColor.RED + "If you are a server administrator, please check the console.");
            Logger log = Bukkit.getLogger();
            log.info("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }

        return false;
    }
}
