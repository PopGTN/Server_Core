package ca.joshuamc.serveressentials.commands.SpawnCommand;

import ca.joshuamc.serveressentials.ServerEssentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpawnCommand implements CommandExecutor {

    ServerEssentials plugin;

    public SpawnCommand(ServerEssentials plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        return false;
    }
}

