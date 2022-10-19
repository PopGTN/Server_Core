package ca.joshuamc.serveressentials.commands;


import ca.joshuamc.serveressentials.util.files.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlightCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender ,Command command ,String label ,String[] args) {

        if (!(sender instanceof Player) && (args.length == 0)) {
            sender.sendMessage(MessageConfig.get().getString("flight-command.command-usage").replaceAll("&" , "§").replaceAll("%commmand%" , command.getName()));
            return true;
        } else if ((args.length == 1) && !(sender instanceof Player)) {
            String playerName = args[0];
            Player target = Bukkit.getPlayerExact(playerName);
            if (target == null) {
                sender.sendMessage(MessageConfig.get().getString("player-target-error"));
                return true;
            } else if (target.getGameMode() == GameMode.SURVIVAL) {
                if (target.getAllowFlight() == false) {
                    target.setAllowFlight(true);
                    target.sendMessage(MessageConfig.get().getString("flight-command.onEnabled").replaceAll("&" , "§").replaceAll("%Sender%" , "Console"));
                    sender.sendMessage(MessageConfig.get().getString("flight-command.senders-message-enabled").replaceAll("&" , "§").replaceAll("%TargetedPlayer%" , target.getDisplayName()));
                    return true;
                } else {
                    target.setAllowFlight(false);
                    target.sendMessage(MessageConfig.get().getString("flight-command.onDisabled").replaceAll("&" , "§").replaceAll("%Sender%" , "Console"));
                    sender.sendMessage(MessageConfig.get().getString("flight-command.senders-message-disabled").replaceAll("&" , "§").replaceAll("%TargetedPlayer%" , target.getDisplayName()));
                    return true;
                }
            } else {
                sender.sendMessage(
(MessageConfig.get().getString("flight-command.PlayerIsNotInSurvival").replaceAll("&" , "§").replaceAll("%TargetedPlayer%" , target.getDisplayName())));
                return true;
            }

        } else if ((sender instanceof Player) && (args.length == 0)) {
            Player player = (Player) sender;
            if (!player.hasPermission("se.fly")) {
                sender.sendMessage(MessageConfig.get().getString("flight-command.command.permission-message").replaceAll("&" , "§"));
                return true;
            } else if (player.getGameMode() == GameMode.SURVIVAL) {
                if (player.getAllowFlight() == false) {
                    player.setAllowFlight(true);
                    player.sendMessage(MessageConfig.get().getString("flight-command.onEnabled").replaceAll("&" , "§").replaceAll("%Sender%" , player.getDisplayName()));
                    return true;
                } else {
                    player.setAllowFlight(false);
                    player.sendMessage(MessageConfig.get().getString("flight-command.onDisabled").replaceAll("&" , "§").replaceAll("%Sender%" , player.getDisplayName()));
                    return true;
                }
            } else {
                sender.sendMessage(MessageConfig.get().getString("flight-command.YourNotInSurvival").replaceAll("&" , "§").replaceAll("%TargetedPlayer%" , player.getDisplayName()));
                return true;
            }

        } else if ((sender instanceof Player) && (args.length == 1)) {
            Player player = (Player) sender;
            if (!sender.hasPermission("se.fly.others")) {
                sender.sendMessage(MessageConfig.get().getString("flight-command.permission-message").replaceAll("&" , "§"));
                return true;
            } else {
                String playerName = args[0];
                Player target = Bukkit.getPlayerExact(playerName);
                if (target == null) {
                    sender.sendMessage(MessageConfig.get().getString("player-target-error"));
                    return true;
                } else if (target.getGameMode() == GameMode.SURVIVAL) {
                    if (target.getAllowFlight() == false) {
                        target.setAllowFlight(true);
                        target.sendMessage(MessageConfig.get().getString("flight-command.onEnabled").replaceAll("&" , "§").replaceAll("%Sender%" , player.getDisplayName()));
                        sender.sendMessage(MessageConfig.get().getString("flight-command.senders-message-enabled").replaceAll("&" , "§").replaceAll("%TargetedPlayer%" , target.getDisplayName()));
                        return true;
                    } else {
                        target.setAllowFlight(false);
                        target.sendMessage(MessageConfig.get().getString("flight-command.onDisabled").replaceAll("&" , "§").replaceAll("%Sender%" , player.getDisplayName()));
                        sender.sendMessage(MessageConfig.get().getString("flight-command.senders-message-disabled").replaceAll("&" , "§").replaceAll("%TargetedPlayer%" , target.getDisplayName()));
                        return true;
                    }
                }
                sender.sendMessage(MessageConfig.get().getString("flight-command.PlayerIsNotInSurvival").replaceAll("&" , "§").replaceAll("%TargetedPlayer%" , target.getDisplayName()));
            }
            return true;
        } else {
            sender.sendMessage(MessageConfig.get().getString("flight-command.command-usage").replaceAll("&" , "§").replaceAll("%commmand%" , command.getUsage()));
        }
        return true;
    }
}