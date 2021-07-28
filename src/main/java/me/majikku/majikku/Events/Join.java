package me.majikku.majikku.Events;

//import me.majikku.majikku.Files.PlayerData;
import me.clip.placeholderapi.PlaceholderAPI;
import me.majikku.majikku.Files.SpawnFile;
import me.majikku.majikku.Majikku;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    Majikku plugin;
    public Join(Majikku plugin) {this.plugin = plugin;}


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        if (plugin.getConfig().getBoolean("Settings.permissions.vault")){
            String join = "&7[&a+&7] %vault_group% &c%player_name%";
            Player p = e.getPlayer();

            join = PlaceholderAPI.setPlaceholders(e.getPlayer(), join);
            if (p.hasPermission("majikku.joinmessage")) {
                e.setJoinMessage(join);
            }
        }
    }
}
