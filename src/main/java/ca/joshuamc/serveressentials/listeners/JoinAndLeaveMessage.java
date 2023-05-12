package ca.joshuamc.serveressentials.listeners;

import ca.joshuamc.serveressentials.DefaultConfig;
import ca.joshuamc.serveressentials.ServerEssentials;
import ca.joshuamc.serveressentials.SpigotUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinAndLeaveMessage implements Listener {

    ServerEssentials plugin;

    public JoinAndLeaveMessage(ServerEssentials plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent join) {
        Player joinPlayer = join.getPlayer();
        if (!joinPlayer.hasPermission("se.vanish.see")) {
            for (int i = 0; i < plugin.vanishlist.size(); i++) {
                joinPlayer.hidePlayer(plugin, plugin.vanishlist.get(i));
            }
        }


        String player = joinPlayer.getDisplayName();
        String joinmessage = plugin.getConfig().getString("join-message").replaceAll("&","§").replaceAll("%player%", player).replaceAll("%server-name%", SpigotUtil.getConfigString("server-name"));
        String welcomeMessage = plugin.getConfig().getString("welcome-message").replaceAll("&","§").replaceAll("%player%", player).replaceAll("%server-name%", SpigotUtil.getConfigString("server-name"));

        //Sends Join Message
        join.setJoinMessage(joinmessage);

        //Checks if Welcome Message is one Time Only
        if(!joinPlayer.hasPlayedBefore() && (plugin.getConfig().getBoolean("First-time-only"))){
            joinPlayer.sendMessage(welcomeMessage);
        }
    }
    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent leave) {
        Player joinPlayer = leave.getPlayer();
        String player = joinPlayer.getDisplayName();
        String leaveMessage = plugin.getConfig().getString("leave-message").replaceAll("&","§").replaceAll("%player%", player).replaceAll("%server-name%", SpigotUtil.getConfigString("server-name"));
        leave.setQuitMessage(leaveMessage);
    }
}

