package ca.joshuamc.serveressentials.commands;


import ca.joshuamc.serveressentials.ServerEssentials;
import ca.joshuamc.serveressentials.SpigotConst;
import ca.joshuamc.serveressentials.SpigotUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlightCommand implements CommandExecutor, SpigotConst {

    private ServerEssentials plugin;

    public FlightCommand(ServerEssentials plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender ,Command command ,String label ,String[] args) {

        if (!(sender instanceof Player) && (args.length == 0)) {
            sender.sendMessage(LANG_FILE.getConfig().getString("flight-command.command-usage").replaceAll("&" , "§").replaceAll("%commmand%" , label));
            return true;
        } else if ((args.length == 1) && !(sender instanceof Player)) {
            String playerName = args[0];
            Player target = Bukkit.getPlayerExact(playerName);
            if (target == null) {
                sender.sendMessage(SpigotUtil.getLangMsg("player-target-error"));
                return true;
            } else if (target.getGameMode() == GameMode.SURVIVAL ||  target.getGameMode() == GameMode.ADVENTURE) {
                if (target.getAllowFlight() == false) {
                    target.setAllowFlight(true);
                    target.sendMessage(SpigotUtil.getLangMsg("flight-command.onEnabled").replaceAll("%Sender%" , "Console"));
                    sender.sendMessage(SpigotUtil.getLangMsg("flight-command.senders-message-enabled").replaceAll("%TargetedPlayer%" , target.getDisplayName()));
                    return true;
                } else {
                    target.setAllowFlight(false);
                    target.sendMessage(SpigotUtil.getLangMsg("flight-command.onDisabled").replaceAll("%Sender%" , "Console"));
                    sender.sendMessage(SpigotUtil.getLangMsg("flight-command.senders-message-disabled").replaceAll("%TargetedPlayer%" , target.getDisplayName()));
                    return true;
                }
            } else {
                sender.sendMessage(SpigotUtil.getLangMsg("flight-command.PlayerIsNotInSurvival").replaceAll("%TargetedPlayer%" , target.getDisplayName()));
                return true;
            }

        } else if ((sender instanceof Player) && (args.length == 0)) {
            Player player = (Player) sender;
            if (!player.hasPermission("se.fly")) {
                SpigotUtil.sendLangMsg("flight-command.command.permission-message",sender);
                return true;
            } else if (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() ==GameMode.ADVENTURE) {
                if (player.getAllowFlight() == false) {
                    player.setAllowFlight(true);
                    player.sendMessage(SpigotUtil.getLangMsg("flight-command.onEnabled").replaceAll("%Sender%", player.getDisplayName()));
                    return true;
                } else {
                    player.setAllowFlight(false);
                    player.sendMessage(SpigotUtil.getLangMsg("flight-command.onDisabled").replaceAll("%Sender%", player.getDisplayName()));
                    return true;
                }
            } else {
                sender.sendMessage(SpigotUtil.getLangMsg("flight-command.YourNotInSurvival").replaceAll("%TargetedPlayer%", player.getDisplayName()));
                return true;
            }

        } else if ((sender instanceof Player) && (args.length == 1)) {
            Player player = (Player) sender;
            if (!sender.hasPermission("se.fly.others")) {
                SpigotUtil.sendLangMsg("flight-command.permission-message", sender);
                return true;
            } else {
                String playerName = args[0];
                Player target = Bukkit.getPlayerExact(playerName);
                if (target == null) {
                    SpigotUtil.sendLangMsg("player-target-error", sender);
                    return true;
                } else if (target.getGameMode() == GameMode.SURVIVAL ||  target.getGameMode() == GameMode.ADVENTURE) {
                    if (!target.getAllowFlight()) {
                        target.setAllowFlight(true);
                        target.sendMessage(SpigotUtil.getLangMsg("flight-command.onEnabled").replaceAll("%Sender%" , player.getDisplayName()));
                        sender.sendMessage(SpigotUtil.getLangMsg("flight-command.senders-message-enabled").replaceAll("%TargetedPlayer%" , target.getDisplayName()));
                        return true;
                    } else {
                        target.setAllowFlight(false);
                        target.sendMessage(SpigotUtil.getLangMsg("flight-command.onDisabled").replaceAll("%Sender%" , player.getDisplayName()));
                        sender.sendMessage(SpigotUtil.getLangMsg("flight-command.senders-message-disabled").replaceAll("%TargetedPlayer%" , target.getDisplayName()));
                        return true;
                    }
                }
                sender.sendMessage(SpigotUtil.getLangMsg("flight-command.PlayerIsNotInSurvival").replaceAll("%TargetedPlayer%" , target.getDisplayName()));
            }
            return true;
        } else {
            sender.sendMessage(SpigotUtil.getLangMsg("flight-command.command-usage").replaceAll("%commmand%" , command.getUsage()));
        }
        return true;
    }
}