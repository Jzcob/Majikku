package me.majikku.majikku.Events;

import me.clip.placeholderapi.PlaceholderAPI;
import me.majikku.majikku.Majikku;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Leave implements Listener {

    Majikku plugin;
    public Leave(Majikku plugin) {this.plugin = plugin;}


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        if (plugin.getConfig().getBoolean("Settings.permissions.vault")){
            String leave = "&7[&a-&7] %vault_group% &c%player_name%";
            Player p = e.getPlayer();

            leave = PlaceholderAPI.setPlaceholders(e.getPlayer(), leave);
            if (p.hasPermission("majikku.leavemessage")) {
                e.setJoinMessage(leave);
            }
        }
    }
}