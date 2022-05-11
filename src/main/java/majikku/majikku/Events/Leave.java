package majikku.majikku.Events;

import majikku.majikku.Commands.Fly;
import majikku.majikku.Commands.God;
import me.clip.placeholderapi.PlaceholderAPI;
import majikku.majikku.Majikku;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave implements Listener {
    Majikku plugin;

    public Leave(Majikku plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        String leave = this.plugin.getConfig().getString("Settings.Chat.leave-message");
        leave = PlaceholderAPI.setPlaceholders(e.getPlayer(), leave);
        e.setQuitMessage(leave);

        if (Fly.fly.contains(p)) {
            p.setAllowFlight(false);
        }
        if (God.g.contains(p)) {
            p.setInvulnerable(false);
        }
        p.setFlySpeed((float) 0.2);
        p.setWalkSpeed((float) 0.2);
    }
}
