package me.majikku.majikku.GameRule;

import me.majikku.majikku.Important.Variables;
import me.majikku.majikku.Majikku;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RandomTickSpeed implements CommandExecutor {
    Majikku plugin;
    public RandomTickSpeed(Majikku plugin) {this.plugin = plugin;}
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player) sender;
            World world = p.getWorld();
            String prefix = plugin.getConfig().getString("Settings." + "prefix");
            if (sender instanceof Player) {
                if (p.hasPermission("majikku.gamerule.randomtickspeed") || p.hasPermission(Variables.gamerule)) {
                    if (args.length == 0) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7Usage /randomtickspeed <#>"));
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7The gamerule is currently set to &c" + p.getWorld().getGameRuleValue(GameRule.RANDOM_TICK_SPEED) + "&7."));
                    } else if (args.length == 1) {
                        int a = Integer.parseInt(args[0]);
                        world.setGameRule(GameRule.RANDOM_TICK_SPEED, a);
                        Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', prefix + "&c" + p.getName() + " &7has set the gamerule &cRandomTickSpeed&7 to &c" + a + "&7."), Variables.gamerule);
                    }
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "If you are a server administrator please check the console.");
            System.out.println("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }
        return false;
    }
}