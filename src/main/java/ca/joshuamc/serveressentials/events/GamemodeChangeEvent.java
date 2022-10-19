package ca.joshuamc.serveressentials.events;

import ca.joshuamc.serveressentials.DefaultConfig;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class GamemodeChangeEvent implements Listener {

    @EventHandler
    public void onGamemodeChange(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        if (player.isInvulnerable() == true && player.getGameMode() == GameMode.SURVIVAL){
            player.setAllowFlight(true);
        }

    }
}