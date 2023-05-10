package ca.joshuamc.serveressentials.commands.SpawnCommand;

import ca.joshuamc.serveressentials.ServerEssentials;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    ServerEssentials plugin;

    public SpawnCommand(ServerEssentials plugin) {
        this.plugin = plugin;
        plugin.getCommand("spawn").setDescription("This is the spawn command. It will teleport you to the world spawnpoint");

    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("se.setspawn"))  {
                Location spawnLocation = Bukkit.getWorld(player.getWorld().getName()).getSpawnLocation();
                //Get The file
               if(args.length == 1){
                   player.sendMessage("Teleporting to Spawn");
                   player.teleport(spawnLocation);
               } else {
                   player.sendMessage("Teleporting to Spawn");
                   player.teleport(spawnLocation);
               }

            } else{
                player.sendMessage("You don't have permission to use this command");
            }
        } else if (sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender) {

        }


        return false;
    }
}

