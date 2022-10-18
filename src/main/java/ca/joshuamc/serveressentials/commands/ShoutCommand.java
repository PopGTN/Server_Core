package ca.joshuamc.serveressentials.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ca.joshuamc.serveressentials.files.MessageConfig;


public class ShoutCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender , @NotNull Command command , @NotNull String label , @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Component.text("Only players can run this Command"));
            return true;
        }
        Player player = (Player) sender;

        if (!player.hasPermission("se.shout")) {
            sender.sendMessage(Component.text(MessageConfig.get().getString("permission-message")));
            return true;
        }
        if(args.length == 0){
            player.sendMessage(Component.text("You didn't provide any arguments"));
            player.sendMessage(Component.text("Example: "+command.getUsage()+" <message here>"));
        }else if (args.length == 1){
            String word = args[0];
            player.getServer().broadcast(Component.text(player.getDisplayName()+ " Shouted: " + word));

        } else {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < args.length; i++) {

                builder.append(args[i]);
                builder.append(" ");
            }
            String finalMessage = builder.toString().stripTrailing();
            player.getServer().broadcast(Component.text(player.getDisplayName()+ " Shout's: " + finalMessage));

        }
        return true;
    }
}
