package ca.joshuamc.serveressentials.commands;


import ca.joshuamc.serveressentials.ServerEssentials;
import ca.joshuamc.serveressentials.SpigotUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishCommand implements CommandExecutor {



    public static ArrayList<Player> getWhoCantSeeVanished() {
        ArrayList<Player> WhoSeeVanished = new ArrayList<Player>();
        for (Player everyone : Bukkit.getOnlinePlayers()) {
            if (!everyone.hasPermission("se.vanish.see")) {
                WhoSeeVanished.add(everyone);
            }
        }
        return WhoSeeVanished;
    }

    ServerEssentials Plugin;

    public VanishCommand(ServerEssentials plugin) {
        Plugin = plugin;
        plugin.getCommand("vanish").setDescription(SpigotUtil.getLangMsg("vanish.description"));
    }

    public void addVanish(Player player){
        Plugin.vanishlist.add(player);
        for (Player playerlist : getWhoCantSeeVanished()){
            playerlist.hidePlayer(Plugin, player);
        }
    }
    public void removeVanish(Player player){
        Plugin.vanishlist.remove(player);
        for (Player playerlist : getWhoCantSeeVanished()){
            playerlist.showPlayer(Plugin, player);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender , Command command , String label , String[] args) {
        // Create a map
        if (!(sender instanceof Player) && (args.length == 0)) {

            sender.sendMessage(SpigotUtil.getLangMsg("vanish.command-usage").replaceAll("%commmand%" , command.getName()));
            return true;
        } else if ((args.length == 1) && !(sender instanceof Player)) {
            String playerName = args[0];
            Player target = Bukkit.getPlayerExact(playerName);
            if (target == null) {
                sender.sendMessage(SpigotUtil.getLangMsg("player-target-error"));
                return true;
            } else {
                if (!Plugin.vanishlist.contains(target)) {
                    target.setAllowFlight(true);
                    target.setInvulnerable(true);
                    addVanish(target);
                    target.sendMessage(SpigotUtil.getLangMsg("vanish.onEnabled").replaceAll("%Sender%" , "Console"));
                    sender.sendMessage(SpigotUtil.getLangMsg("vanish.senders-message-enabled").replaceAll("%Target%" , target.getDisplayName()));
                    return true;
                } else if (Plugin.vanishlist.contains(target)){

                    target.setAllowFlight(false);
                    target.setInvulnerable(false);
                    removeVanish(target);
                    if (target.getGameMode() == GameMode.SURVIVAL) {
                        target.setAllowFlight(false);
                    }
                    target.sendMessage(SpigotUtil.getLangMsg("vanish.onDisabled").replaceAll("%Sender%" , "Console"));
                    sender.sendMessage(SpigotUtil.getLangMsg("vanish.senders-message-disabled").replaceAll("%Target%" , target.getDisplayName()));
                    return true;
                }
            }

        } else if ((sender instanceof Player) && (args.length == 0)) {
            Player player = (Player) sender;
            if (!player.hasPermission("se.vanish")) {
                sender.sendMessage(SpigotUtil.getLangMsg("permission-message"));
                return true;
            } else {
                if (!Plugin.vanishlist.contains(player)) {
                    addVanish(player);



                    player.setInvulnerable(true);
                    player.setAllowFlight(true);
                    player.sendMessage(SpigotUtil.getLangMsg("vanish.onEnabled").replaceAll("%Sender%" , player.getDisplayName()));
                    return true;
                } else if (Plugin.vanishlist.contains(player)) {


                    if (player.getGameMode() == GameMode.SURVIVAL) {
                        player.setAllowFlight(false);
                    }
                    removeVanish(player);
                    player.setInvulnerable(false);
                    player.sendMessage(SpigotUtil.getLangMsg("vanish.onDisabled").replaceAll("%Sender%" , player.getDisplayName()));
                    return true;
                }
            }

        } else if ((sender instanceof Player) && (args.length == 1)) {
            Player player = (Player) sender;
            if (!sender.hasPermission("se.vanish.others")) {
                sender.sendMessage(SpigotUtil.getLangMsg("permission-message"));
                return true;
            } else {
                String playerName = args[0];
                Player target = Bukkit.getPlayerExact(playerName);
                if (target == null) {
                    sender.sendMessage(SpigotUtil.getLangMsg("player-target-error"));
                    return true;
                } else if (target.getGameMode() == GameMode.SURVIVAL) {
                    if (!Plugin.vanishlist.contains(target)) {
                        addVanish(target);
                        target.setInvulnerable(true);
                        target.setAllowFlight(true);
                        target.sendMessage(SpigotUtil.getLangMsg("vanish.onEnabled").replaceAll("%Sender%" , player.getDisplayName()));
                        sender.sendMessage(SpigotUtil.getLangMsg("vanish.senders-message-enabled").replaceAll("%Target%" , target.getDisplayName()));
                        return true;
                    } else if (Plugin.vanishlist.contains(target)){
                        removeVanish(target);
                        if (target.getGameMode() == GameMode.SURVIVAL) {
                            target.setAllowFlight(false);
                        }
                        target.setInvulnerable(false);
                        target.sendMessage(SpigotUtil.getLangMsg("vanish.onDisabled").replaceAll("%Sender%" , player.getDisplayName()));
                        sender.sendMessage(SpigotUtil.getLangMsg("vanish.senders-message-disabled").replaceAll("%Target%" , target.getDisplayName()));
                        return true;
                    }
                }
            }
            return true;
        } else {
            sender.sendMessage(SpigotUtil.getLangMsg("vanish.command-usage").replaceAll("%commmand%" , command.getUsage()));
        }
        return true;
    }
}