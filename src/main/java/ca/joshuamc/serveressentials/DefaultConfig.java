package ca.joshuamc.serveressentials;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class DefaultConfig {
    private static String SERVER_NAME;

    private static boolean EIGHTBALL_BQUESTION;
    private static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Server-Essentials");

    public static String getServerName () {
        SERVER_NAME = plugin.getConfig().getString("server-name").replaceAll("&","§");
        return SERVER_NAME;
    }
}
