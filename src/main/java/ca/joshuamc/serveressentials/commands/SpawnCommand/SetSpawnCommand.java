package ca.joshuamc.serveressentials.commands.SpawnCommand;

import ca.joshuamc.serveressentials.ConfigUtil;
import ca.joshuamc.serveressentials.ServerEssentials;
import ca.joshuamc.serveressentials.SpigotTools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    ServerEssentials plugin;


    public SetSpawnCommand(ServerEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("se.setspawn")){
            if (sender instanceof Player) {
                Player player = (Player) sender;
                String worldName = player.getWorld().getName();

                //Get The file
                ConfigUtil worldFile = new ConfigUtil(plugin, "worlds/" + worldName + ".yml");


                //Sets the World Spawn point!
                player.getWorld().setSpawnLocation(player.getLocation());

                //Get the PLayer Cords
                double[] XYZ = {player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()};
                ;
                //save The cords
                worldFile.getConfig().addDefault("spawnPoint", XYZ);
                worldFile.save();

                sender.sendMessage("The World spawn point set to ");
                for (Object player1 : SpigotTools.whoHasPermission("se.setspawn")) {
                    if (player1 instanceof Player) {
                        if (!player1.equals(player)){
                            ((Player) player1).sendMessage(player.getName() + "Has set the world spawn to " + XYZ[0] + ", " + XYZ[1] + ", " + XYZ[2] + ".g");
                        }
                    }
                }
            } else {
                sender.sendMessage("This is Player only Command");
            }
        } else {
            sender.sendMessage("You do not have Permission to use this Command");
        }


        return true;
    }
}
