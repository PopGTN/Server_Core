package Testlab.CustomEventExample;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CustomEventListenerExample implements Listener {

    /* To register this event Do the Following under the onEnable method:
    *
    *  Bukkit.getServer().getPluginManager().registerEvents(new CustomEventListenerExample(). this);
    *
    * To Call event.
    * Bukkit.getServer().getPluginManager().callEvent(new CustomEventExample(player,target,1000))
    *
    * */


    @EventHandler
    public void onCustomEventExample(CustomEventExample event){
        Bukkit.getServer().broadcastMessage("Game as Ended!");
        Bukkit.getServer().broadcastMessage("Winner: "+ event.getWinner().getDisplayName());
        Bukkit.getServer().broadcastMessage("Loser: "+ event.getLoser().getDisplayName());
    }

}
