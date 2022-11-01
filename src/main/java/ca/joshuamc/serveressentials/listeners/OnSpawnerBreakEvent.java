package ca.joshuamc.serveressentials.listeners;

import ca.joshuamc.serveressentials.events.SpawnerBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class OnSpawnerBreakEvent implements Listener {

    @EventHandler
    public void onSpawnerBreak(SpawnerBreakEvent e) {

        Player player = (Player) e.getPlayerBreaker();

      //  boolean hasPerms = (player.hasPermission("se.silktouchspawner") && DefaultConfig.isSilkTouchSpawnersPermOnly() == true && DefaultConfig.isSilkTouchSpawners() == true);

//        if (hasPerms || DefaultConfig.isSilkTouchSpawners() == true) {



        CreatureSpawner cs = (CreatureSpawner) e.getSpawner().getState();

        ItemStack spawnerToGive = new ItemStack(Material.SPAWNER);
        BlockStateMeta meta = (BlockStateMeta) spawnerToGive.();
        CreatureSpawner css = (CreatureSpawner) meta.getBlockState();
c
        player.sendMessage(String.valueOf(cs.getSpawnedType()));

        css.setSpawnedType(cs.getSpawnedType());
        css.setBlockData(cs.getBlockData());
        meta.setBlockState(cs.getBlock().getState());
        spawnerToGive.setItemMeta(meta);

        player.getInventory().addItem();
        player.sendMessage(String.valueOf(css.getSpawnedType()));



/*        } else{
            player.sendMessage("Silk touch on spawners is Disabled");
        }*/
    }
}
