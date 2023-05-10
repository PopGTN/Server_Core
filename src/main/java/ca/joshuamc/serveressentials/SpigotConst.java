package ca.joshuamc.serveressentials;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public interface SpigotConst {
    public static ConfigUtil LANG_FILE = new ConfigUtil(Bukkit.getServer().getPluginManager().getPlugin("Server-Essentials").getDataFolder().getAbsolutePath() + "/" +  "Messages.yml");

    public static Plugin PLUGIN = Bukkit.getServer().getPluginManager().getPlugin("Server-Essentials");
}

