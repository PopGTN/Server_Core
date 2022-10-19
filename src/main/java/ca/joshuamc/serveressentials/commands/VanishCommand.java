package ca.joshuamc.serveressentials.commands;


import ca.joshuamc.serveressentials.util.files.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


import java.util.HashSet;

public class VanishCommand implements CommandExecutor {

    private static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Server-Essentials");
    private static HashSet<Player> vanishlist = new HashSet<Player>();

    private static void addVanish(Player player) {
        vanishlist.add(player);
        for (Player hidefrom : getWhoCantSeeVanished()) {
            hidefrom.hidePlayer(plugin , player);
        }
    }

    private static void removeVanish(Player player) {
        for (Player showto : getWhoCantSeeVanished()) {
            showto.showPlayer(plugin , player);
        }
        vanishlist.remove(player);
    }

    private static HashSet<Player> getWhoCantSeeVanished() {
        HashSet<Player> SeeVanished = new HashSet<Player>();
        for (Player everyone : Bukkit.getOnlinePlayers()) {
            if (!everyone.hasPermission("se.vanish.see")) {
                SeeVanished.add(everyone);
            }
        }
        return SeeVanished;
    }

    public static boolean isVanished(Player player) {
        if (vanishlist.isEmpty()) {
            return false;
        }
        return vanishlist.contains(player);
    }

    /* Make sure the list gets resetShould have this on the event of the plugin Disabling
    This is so on Reloads it doesnt break the Plugin */
    public static void endVanish() {
        if (vanishlist.isEmpty() == false) {
            for (Player playerList : vanishlist) {
                Player player = playerList;
                player.setAllowFlight(false);
                player.setInvulnerable(false);
                for (Player showto : getWhoCantSeeVanished()) {
                    showto.showPlayer(plugin , player);
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender , Command command , String label , String[] args) {
        // Create a map

        if (!(sender instanceof Player) && (args.length == 0)) {
            sender.sendMessage(MessageConfig.get().getString(".command-usage").replaceAll("&" , "§").replaceAll("%commmand%" , command.getName()));
            return true;
        } else if ((args.length == 1) && !(sender instanceof Player)) {
            String playerName = args[0];
            Player target = Bukkit.getPlayerExact(playerName);
            if (target == null) {
                sender.sendMessage(MessageConfig.get().getString("player-target-error"));
                return true;
            } else {
                if (isVanished(target) == false) {
                    target.setAllowFlight(true);
                    target.setInvulnerable(true);
                    addVanish(target);
                    target.sendMessage(MessageConfig.get().getString("vanish.onEnabled").replaceAll("&" , "§").replaceAll("%Sender%" , "Console"));
                    sender.sendMessage(MessageConfig.get().getString("vanish.senders-message-enabled").replaceAll("&" , "§").replaceAll("%TargetedPlayer%" , target.getDisplayName()));
                    return true;
                } else {
                    target.setAllowFlight(false);
                    target.setInvulnerable(false);
                    removeVanish(target);
                    if (target.getGameMode() == GameMode.SURVIVAL) {
                        target.setAllowFlight(false);
                    }
                    target.sendMessage(MessageConfig.get().getString("vanish.onDisabled").replaceAll("&" , "§").replaceAll("%Sender%" , "Console"));
                    sender.sendMessage(MessageConfig.get().getString("vanish.senders-message-disabled").replaceAll("&" , "§").replaceAll("%TargetedPlayer%" , target.getDisplayName()));
                    return true;
                }
            }

        } else if ((sender instanceof Player) && (args.length == 0)) {
            Player player = (Player) sender;
            if (!player.hasPermission("se.vanish")) {
                sender.sendMessage(MessageConfig.get().getString("permission-message").replaceAll("&" , "§"));
                return true;
            } else {
                if (isVanished(player) == false) {
                    addVanish(player);
                    player.setInvulnerable(true);
                    player.setAllowFlight(true);
                    player.sendMessage(MessageConfig.get().getString("vanish.onEnabled").replaceAll("&" , "§").replaceAll("%Sender%" , player.getDisplayName()));
                    return true;
                } else {
                    if (player.getGameMode() == GameMode.SURVIVAL) {
                        player.setAllowFlight(false);
                    }
                    removeVanish(player);
                    player.setInvulnerable(false);
                    player.sendMessage(MessageConfig.get().getString("vanish.onDisabled").replaceAll("&" , "§").replaceAll("%Sender%" , player.getDisplayName()));
                    return true;
                }
            }

        } else if ((sender instanceof Player) && (args.length == 1)) {
            Player player = (Player) sender;
            if (!sender.hasPermission("se.vanish.others")) {
                sender.sendMessage(MessageConfig.get().getString("permission-message").replaceAll("&" , "§"));
                return true;
            } else {
                String playerName = args[0];
                Player target = Bukkit.getPlayerExact(playerName);
                if (target == null) {
                    sender.sendMessage(MessageConfig.get().getString("player-target-error"));
                    return true;
                } else if (target.getGameMode() == GameMode.SURVIVAL) {
                    if (isVanished(target) == false) {
                        target.setInvulnerable(true);
                        target.setAllowFlight(true);
                        target.sendMessage(MessageConfig.get().getString("vanish.onEnabled").replaceAll("&" , "§").replaceAll("%Sender%" , player.getDisplayName()));
                        sender.sendMessage(MessageConfig.get().getString("vanish.senders-message-enabled").replaceAll("&" , "§").replaceAll("%TargetedPlayer%" , target.getDisplayName()));
                        return true;
                    } else {
                        if (target.getGameMode() == GameMode.SURVIVAL) {
                            target.setAllowFlight(false);
                        }
                        removeVanish(target);
                        target.setInvulnerable(false);
                        target.sendMessage(MessageConfig.get().getString("vanish.onDisabled").replaceAll("&" , "§").replaceAll("%Sender%" , player.getDisplayName()));
                        sender.sendMessage(MessageConfig.get().getString("vanish.senders-message-disabled").replaceAll("&" , "§").replaceAll("%TargetedPlayer%" , target.getDisplayName()));
                        return true;
                    }
                }
            }
            return true;
        } else {
            sender.sendMessage(MessageConfig.get().getString("vanish.command-usage").replaceAll("&" , "§").replaceAll("%commmand%" , command.getUsage()));
        }
        return true;
    }
}