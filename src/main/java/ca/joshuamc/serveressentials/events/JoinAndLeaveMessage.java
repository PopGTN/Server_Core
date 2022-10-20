package ca.joshuamc.serveressentials.events;

import ca.joshuamc.serveressentials.DefaultConfig;
import ca.joshuamc.serveressentials.ServerEssentials;
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

        for (int i = 0; i < plugin.vanishlist.size(); i++){
            joinPlayer.hidePlayer(plugin, plugin.vanishlist.get(i));
        }


        String player = joinPlayer.getDisplayName();
        String joinmessage = DefaultConfig.getJoinMessage().replaceAll("%player%", player).replaceAll("%server-name%", DefaultConfig.getServerName());
        String welcomeMessage = DefaultConfig.getWelcomeMessage().replaceAll("%player%", player).replaceAll("%server-name%", DefaultConfig.getServerName());

        //Sends Join Message
        join.setJoinMessage(joinmessage);

        //Checks if Welcome Message is one Time Only
        if(!joinPlayer.hasPlayedBefore() && (DefaultConfig.getWelcomeMessageSwitch() == true)){
            joinPlayer.sendMessage(welcomeMessage);
        }
    }
    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent leave) {
        Player joinPlayer = leave.getPlayer();
        String player = joinPlayer.getDisplayName();
        String leaveMessage = DefaultConfig.getLeaveMessage().replaceAll("%player%", player).replaceAll("%server-name%", DefaultConfig.getServerName());
        leave.setQuitMessage(leaveMessage);
    }
}