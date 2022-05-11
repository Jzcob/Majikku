package majikku.majikku.Events;

import majikku.majikku.Majikku;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class OnePlayerSleep implements Listener {

    Majikku plugin;

    public OnePlayerSleep(Majikku plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPLayerSleep(PlayerBedLeaveEvent e){
        if (this.plugin.getConfig().getBoolean("Settings.OnePlayerSleep")) {
            Player p = e.getPlayer();
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c"+ p.getDisplayName() + " &7went to sleep."));
            p.getLocation().getWorld().setTime(1000);
            Bukkit.getWorld("world").setStorm(false);
        }

    }
}
