package ca.joshuamc.serveressentials.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SpawnerBreakEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player playerBreaker;
    private Block spawner;


    public SpawnerBreakEvent(Player playerBreaker , Block spawner){
        this.playerBreaker = playerBreaker;
        this.spawner = spawner;
    }

    public Player getPlayerBreaker() {
        return playerBreaker;
    }

    public Block getSpawner() {
        return spawner;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
