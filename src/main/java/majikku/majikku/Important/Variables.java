package majikku.majikku.Important;

import majikku.majikku.Majikku;
import net.luckperms.api.*;
import net.luckperms.api.model.user.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

public class Variables {
    Majikku plugin;
    public Variables(Majikku plugin) {
        this.plugin = plugin;
    }

    public static String error = ChatColor.RED + "ERROR";

    public static String perms = ChatColor.GRAY + "Unfortunately, you do not have permissions to use this command.";

    public static String gamerule = "majikku.gamerule.*";

    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }
    public static String getPlayerGroup(Player player, Collection<String> possibleGroup) {
        for (String group : possibleGroup) {
            if (player.hasPermission("group." + group)) {
                return group;
            }
        }
        return null;
    }
}