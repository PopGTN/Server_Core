package ca.joshuamc.serveressentials.commands;



import ca.joshuamc.serveressentials.util.files.MessageConfig;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender , @NotNull Command command , @NotNull String label , @NotNull String[] args) {

        if (!(sender instanceof Player) && (args.length == 0)) {
            sender.sendMessage(Component.text(MessageConfig.get().getString(".command-usage").replaceAll("&" , "§").replaceAll("%commmand%" , command.getName())));
            return true;
        } else if ((args.length == 1) && !(sender instanceof Player)) {
            String playerName = args[0];
            Player target = Bukkit.getPlayerExact(playerName);
            if (target == null) {
                sender.sendMessage(MessageConfig.get().getString("player-target-error"));
                return true;
            } else {
                if (target.isInvisible() == false) {
                    target.setAllowFlight(true);
                    target.setInvulnerable(true);
                    target.setInvisible(false);
                    target.sendMessage(Component.text(MessageConfig.get().getString("vanish.onEnabled").replaceAll("&" , "§").replaceAll("%Sender%" , "Console")));
                    sender.sendMessage(Component.text(MessageConfig.get().getString("vanish.senders-message-enabled").replaceAll("&" , "§").replaceAll("%TargetedPlayer%" , target.getDisplayName())));
                    return true;
                } else {
                    target.setInvisible(false);
                    target.setInvulnerable(true);
                    if (target.getGameMode() == GameMode.SURVIVAL){target.setAllowFlight(false);}
                    target.sendMessage(Component.text(MessageConfig.get().getString("vanish.onDisabled").replaceAll("&" , "§").replaceAll("%Sender%" , "Console")));
                    sender.sendMessage(Component.text(MessageConfig.get().getString("vanish.senders-message-disabled").replaceAll("&" , "§").replaceAll("%TargetedPlayer%" , target.getDisplayName())));
                    return true;
                }
            }

        } else if ((sender instanceof Player) && (args.length == 0)) {
            Player player = (Player) sender;
            if (!player.hasPermission("se.vanish")) {
                sender.sendMessage(Component.text(MessageConfig.get().getString("vanish.command.permission-message").replaceAll("&", "§")));
                return true;
            }else{
                if (player.isInvisible() == false) {
                    player.setInvulnerable(true);
                    player.setAllowFlight(true);
                    player.setInvisible(false);
                    player.sendMessage(Component.text(MessageConfig.get().getString("vanish.onEnabled").replaceAll("&" , "§").replaceAll("%Sender%" , player.getDisplayName())));
                    return true;
                } else {
                    if (player.getGameMode() == GameMode.SURVIVAL){player.setAllowFlight(false);}
                    player.setInvisible(false);
                    player.setInvulnerable(true);
                    player.sendMessage(Component.text(MessageConfig.get().getString("vanish.onDisabled").replaceAll("&" , "§").replaceAll("%Sender%" , player.getDisplayName())));
                    return true;
                }
            }

        } else if ((sender instanceof Player) && (args.length == 1)) {
            Player player = (Player) sender;
            if (!sender.hasPermission("se.vanish.others")) {
                sender.sendMessage(Component.text(MessageConfig.get().getString("vanish.permission-message").replaceAll("&" , "§")));
                return true;
            } else {
                String playerName = args[0];
                Player target = Bukkit.getPlayerExact(playerName);
                if (target == null) {
                    sender.sendMessage(MessageConfig.get().getString("player-target-error"));
                    return true;
                } else if (target.getGameMode() == GameMode.SURVIVAL) {
                    if (target.isInvisible() == false) {
                        target.setInvulnerable(false);
                        target.setAllowFlight(true);
                        if (target.getGameMode() == GameMode.SURVIVAL){target.setAllowFlight(true);}
                        target.setInvisible(true);
                        target.sendMessage(Component.text(MessageConfig.get().getString("vanish.onEnabled").replaceAll("&", "§").replaceAll("%Sender%", player.getDisplayName())));
                        sender.sendMessage(Component.text(MessageConfig.get().getString("vanish.senders-message-enabled").replaceAll("&", "§").replaceAll("%TargetedPlayer%", target.getDisplayName())));
                        return true;
                    } else {
                        if (target.getGameMode() == GameMode.SURVIVAL){target.setAllowFlight(false);}
                        target.setInvisible(false);
                        target.setInvulnerable(true);
                        target.sendMessage(Component.text(MessageConfig.get().getString("vanish.onDisabled").replaceAll("&", "§").replaceAll("%Sender%", player.getDisplayName())));
                        sender.sendMessage(Component.text(MessageConfig.get().getString("vanish.senders-message-disabled").replaceAll("&", "§").replaceAll("%TargetedPlayer%", target.getDisplayName())));
                        return true;
                    }
                }
            }
            return true;
        } else {
            sender.sendMessage(Component.text(MessageConfig.get().getString("vanish.command-usage").replaceAll("&" , "§").replaceAll("%commmand%" , command.getUsage())));
        }
        return true;
    }
}