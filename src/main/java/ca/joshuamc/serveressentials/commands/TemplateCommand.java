package ca.joshuamc.serveressentials.commands;


import ca.joshuamc.serveressentials.util.files.MessageConfig;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TemplateCommand implements CommandExecutor {

    @Override
    public boolean onCommand( CommandSender sender ,  Command command ,  String label ,  String[] args) {

        //Console cmd target others
        if (!(sender instanceof Player) && (args.length == 0)) {
            sender.sendMessage(MessageConfig.get().getString("se.CommandName").replaceAll("&" , "§").replaceAll("%commmand%" , command.getName()));
            return true;
        } else if ((args.length == 1) && !(sender instanceof Player)) {
            String playerName = args[0];
            Player target = Bukkit.getPlayerExact(playerName);
            if (target == null) {
                sender.sendMessage(MessageConfig.get().getString("player-target-error"));
                return true;
            }
            ///task here
            return true;
            //Target self
        } else if ((sender instanceof Player) && (args.length == 0)) {
            Player player = (Player) sender;
            if (!player.hasPermission("se.CommandName")) {
                sender.sendMessage(MessageConfig.get().getString("permission-message").replaceAll("&" , "§"));
                return true;
            }
            //task here
            return true;
            //Targeted at other
        } else if ((sender instanceof Player) && (args.length == 1)) {
            Player player = (Player) sender;
            if (!sender.hasPermission("se.CommandName.others")) {
                sender.sendMessage(MessageConfig.get().getString("permission-message").replaceAll("&" , "§"));
                return true;
            } else {
                String playerName = args[0];//selects target
                Player target = Bukkit.getPlayerExact(playerName);
                if (target == null) {
                    sender.sendMessage(MessageConfig.get().getString("player-target-error"));
                    return true;
                }
                //task here
                return true;
            }
        } else {
            sender.sendMessage(MessageConfig.get().getString("name.command-usage").replaceAll("&" , "§").replaceAll("%commmand%" , command.getUsage()));
        }
        return true;
    }
}