package majikku.majikku.Events;

import majikku.majikku.Majikku;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnDeath implements Listener {

    Majikku plugin;

    public OnDeath(Majikku plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (this.plugin.getConfig().getBoolean("Settings.OnDeathMessage")) {
            Player p = e.getEntity().getPlayer();
            String prefix = this.plugin.getConfig().getString("Settings.prefix");
            Location dl = p.getLocation();
            double x = dl.getBlockX() + 0.5;
            int y = dl.getBlockY();
            double z = dl.getBlockZ() + 0.5;

            p.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix +"&7You died at " + x + " " + y + " " + z + "."));

        }
    }
}
