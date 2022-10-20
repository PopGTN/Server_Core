package ca.joshuamc.serveressentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ca.joshuamc.serveressentials.util.files.MessageConfig;


public class ShoutCommand implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender ,  Command command ,  String label ,  String[] args) {

        //This Command is very poorly Made and will be receiving an update
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this Command");
            return true;
        }
        Player player = (Player) sender;

        if (!player.hasPermission("se.shout")) {
            sender.sendMessage(MessageConfig.get().getString("permission-message"));
            return true;
        }
        if(args.length == 0){
            player.sendMessage("You didn't provide any arguments");
            player.sendMessage("Example: "+command.getUsage()+" <message here>");
        }else if (args.length == 1){
            String word = args[0];
            player.getServer().broadcastMessage(player.getDisplayName()+ " Shouted: " + word);

        } else {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < args.length; i++) {

                builder.append(args[i]);
                builder.append(" ");
            }
            String finalMessage = builder.toString().stripTrailing();
            player.getServer().broadcastMessage(player.getDisplayName()+ " Shout's: " + finalMessage);

        }
        return true;
    }
}
