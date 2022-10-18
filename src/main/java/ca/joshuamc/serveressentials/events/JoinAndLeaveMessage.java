package ca.joshuamc.serveressentials.events;

import ca.joshuamc.serveressentials.DefaultConfig;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinAndLeaveMessage implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent join) {

        Player joinPlayer = join.getPlayer();
        String player = joinPlayer.getDisplayName();
        String joinmessage = DefaultConfig.getJoinMessage().replaceAll("%player%", player).replaceAll("%server-name%", DefaultConfig.getServerName());
        String welcomeMessage = DefaultConfig.getWelcomeMessage().replaceAll("%player%", player).replaceAll("%server-name%", DefaultConfig.getServerName());

        //Sends Join Message
        join.joinMessage(Component.text(joinmessage));

        //Checks if Welcome Message is one Time Only
        if(!joinPlayer.hasPlayedBefore() && (DefaultConfig.getWelcomeMessageSwitch() == true)){
            joinPlayer.sendMessage(Component.text(welcomeMessage));
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent leave) {
        Player joinPlayer = leave.getPlayer();
        String player = joinPlayer.getDisplayName();
        String leaveMessage = DefaultConfig.getLeaveMessage().replaceAll("%player%", player).replaceAll("%server-name%", DefaultConfig.getServerName());
        leave.quitMessage(Component.text(leaveMessage));
    }
}