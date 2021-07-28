package me.majikku.majikku.Commands;

import me.majikku.majikku.Majikku;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

public class Heal implements CommandExecutor {

    Majikku plugin;

    public Heal(Majikku plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (plugin.getConfig().getBoolean("Updated")) {
            Player p = (Player) sender;
            String prefix = plugin.getConfig().getString("Settings." + "prefix");
            if (args.length == 0) {
                if (p.hasPermission("majikku.heal")) {
                    p.setFoodLevel(20);
                    p.setHealth(20.0);
                    for (PotionEffect effect : p.getActivePotionEffects())
                        p.removePotionEffect(effect.getType());
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have healed yourself."));
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&4You do not have permission to do this."));
                }
            } else {
                if (p.hasPermission("majikku.heal.other")) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    target.setHealth(20.0);
                    target.setFoodLevel(20);
                    for (PotionEffect effect : target.getActivePotionEffects())
                        target.removePotionEffect(effect.getType());
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have been healed."));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&7You have healed &c" + target.getName() + "&7."));
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&4You do not have permission to do this."));
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "If you are a server administrator please check the console.");
            System.out.println("[Majikku] Please make sure that 'Updated' is set to true in the Majikku Config file.");
        }
        return false;
    }
}