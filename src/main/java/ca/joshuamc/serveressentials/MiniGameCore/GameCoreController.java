package ca.joshuamc.serveressentials.MiniGameCore;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class GameCoreController implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;

        } else if (sender instanceof ConsoleCommandSender) {

        } else if (sender instanceof BlockCommandSender) {

        }

        return true;
    }
}
