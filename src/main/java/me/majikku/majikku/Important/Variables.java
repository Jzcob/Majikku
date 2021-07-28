package me.majikku.majikku.Important;

import me.majikku.majikku.Majikku;
import org.bukkit.ChatColor;

public class Variables {

    Majikku plugin;
    public Variables(Majikku plugin) {this.plugin = plugin;}

    public static String error = ChatColor.RED + "ERROR ";
    public static String perms = ChatColor.GRAY + "Unfortunately you do not have permission to use this command.";
    public static String gamerule = "majikku.gamerule.*";

}
