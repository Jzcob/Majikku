package majikku.majikku.Events;

import majikku.majikku.Files.SpawnConfig;
import me.clip.placeholderapi.PlaceholderAPI;
import majikku.majikku.Majikku;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
    Majikku plugin;

    public Join(Majikku plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String join = this.plugin.getConfig().getString("Settings.Chat.join-message");
        join = PlaceholderAPI.setPlaceholders(e.getPlayer(), join);
        e.setJoinMessage(join);

        double x = SpawnConfig.get().getDouble("Spawn.x");
        double y = SpawnConfig.get().getDouble("Spawn.y");
        double z = SpawnConfig.get().getDouble("Spawn.z");
        float pitch = (float) SpawnConfig.get().getDouble("Spawn.pitch");
        float yaw = (float) SpawnConfig.get().getDouble("Spawn.yaw");

        Location l = new Location(p.getWorld(), x, y, z , yaw, pitch);
        if (SpawnConfig.get().getBoolean("SpawnSet")) {
            p.teleport(l);
        }

    }
}
