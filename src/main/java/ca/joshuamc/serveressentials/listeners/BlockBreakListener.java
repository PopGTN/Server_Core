package ca.joshuamc.serveressentials.listeners;

import ca.joshuamc.serveressentials.events.SpawnerBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockbreak(BlockBreakEvent e){

        Block blockbroken = e.getBlock();
        if (blockbroken.getType().equals(Material.SPAWNER)) {
            Bukkit.getServer().getPluginManager().callEvent(new SpawnerBreakEvent(e.getPlayer(), blockbroken));

        }
    }
}
