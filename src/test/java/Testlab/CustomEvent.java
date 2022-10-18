package Testlab;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CustomEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final String playerName;
    private boolean isCancelled;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }


    public CustomEvent(String playerName) {
        this.playerName = playerName;
        this.isCancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return null;
    }
}