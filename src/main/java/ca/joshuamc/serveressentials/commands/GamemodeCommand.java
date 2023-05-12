package ca.joshuamc.serveressentials.commands;

import ca.joshuamc.serveressentials.ServerEssentials;
import ca.joshuamc.serveressentials.SpigotUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SpongeAbsorbEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GamemodeCommand implements TabExecutor {
    ServerEssentials plugin;

    public GamemodeCommand(ServerEssentials plugin) {
        this.plugin = plugin;
        plugin.getCommand("gamemode").setDescription(SpigotUtil.getLangMsg("gamemode.description"));
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(label);
        if (label.toLowerCase().contains("gms") || label.equalsIgnoreCase("survival")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                return true;
            } else if (sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender){
                return true;
            }
            
        }
        else if (label.toLowerCase().contains("gmc") || label.equalsIgnoreCase("creative")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                return true;
            } else if (sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender){
                return true;
            }
        }
        else if (label.toLowerCase().contains("gma") || label.equalsIgnoreCase("adventure")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                return true;
            } else if (sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender){
                return true;
            }
        }
        else if (label.toLowerCase().contains("gmsp") || label.equalsIgnoreCase("spectator")) {
            if (sender instanceof Player){
                Player player = (Player) sender;
                return true;
            } else if (sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender){
                return true;
            }
        }
        else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.hasPermission("se.gamemode")) {
                    sender.sendMessage(SpigotUtil.getLangMsg("permission-message"));

                    return true;
                } else if (args.length == 2) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    String gamemode = setGamemode(target, args[0]);
                    if (target != null) {
                        if (!gamemode.equals(null)) {
                            target.sendMessage(SpigotUtil.getLangMsg("gamemode.victims-message").replaceAll("%Sender%",sender.getName()).replaceAll("%Target%", target.getName()).replaceAll("%Gamemode%", gamemode));
                            SpigotUtil.MsgtoPplWithPerm("se.gamemode.others", target, SpigotUtil.getLangMsg("gamemode.broadcast-msg").replaceAll("%Sender%",sender.getName()).replaceAll("%Target%", target.getName()).replaceAll("%Gamemode%", gamemode));

                        } else {
                            sender.sendMessage(SpigotUtil.getLangMsg("gamemode.invalid-gamemode"));
                        }
                        return true;
                    } else {
                        sender.sendMessage(SpigotUtil.getLangMsg("player-target-error"));
                        return true;
                    }
                } else {
                    String gamemode = setGamemode(player, args[0]);
                    if (!gamemode.equals(null)) {
                        player.sendMessage("Your game has changed to " + gamemode);
                    } else {
                        player.sendMessage("Invalid Gamemode!");
                    }
                }


            } else if (sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender) {
                if (args.length == 2) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    String gamemode = setGamemode(target, args[0]);
                    if (target != null) {
                        if (!gamemode.equals(null)) {
                            target.sendMessage(SpigotUtil.getLangMsg("gamemode.victims-message").replaceAll("%Sender%",sender.getName()).replaceAll("%Target%", target.getName()).replaceAll("%Gamemode%", gamemode));
                            SpigotUtil.MsgtoPplWithPerm("se.gamemode.others", target, SpigotUtil.getLangMsg("gamemode.broadcast-msg").replaceAll("%Sender%",sender.getName()).replaceAll("%Target%", target.getName()).replaceAll("%Gamemode%", gamemode));

                        } else {
                            sender.sendMessage(SpigotUtil.getLangMsg("gamemode.invalid-gamemode"));
                        }
                        return true;
                    } else {
                        sender.sendMessage(SpigotUtil.getLangMsg("player-target-error"));
                        return true;
                    }

                } else {
                    sender.sendMessage(SpigotUtil.getLangMsg("gamemode.command-usage"));
                }
            }
        }
        return true;
    }

    private String setGamemode(Player player, String gamemode) {
        switch (gamemode.toLowerCase()) {
            case "survival":
            case "sur":
            case "0":
            case "s":
                player.setGameMode(GameMode.SURVIVAL);
                return "Survival";
            case "creative":
            case "cre":
            case "1":
            case "c":
                player.setGameMode(GameMode.CREATIVE);
                return "Creative";
            case "adventure":
            case "adv":
            case "2":
            case "a":
                player.setGameMode(GameMode.ADVENTURE);
                return "Adventure";
            case "spectator":
            case "spec":
            case "3":
            case "sp":
                player.setGameMode(GameMode.SPECTATOR);
                return "Spectator";
            case "default":
            case "def":
            case "4":
            case "d":
                try {
                    World world = player.getWorld();
//                    GameMode defaultGameMode = world.getDefaultGameMode();
//                    player.setGameMode(defaultGameMode);
//                    return "Default";
                    return null;
                } catch (Exception e) {
                    return null;
                }
            default:
                return null;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        if (label.toLowerCase().contains("gms") || label.equalsIgnoreCase("survival")
                || label.toLowerCase().contains("gmc") || label.equalsIgnoreCase("creative")
                || label.toLowerCase().contains("gma") || label.equalsIgnoreCase("adventure")
                || label.toLowerCase().contains("gmsp") || label.equalsIgnoreCase("spectator")) {
            if (args.length == 1) {
                List<Player> listOfPlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
                ArrayList<String> ListOfPlayersNames = new ArrayList<String>();
                for (Player player : listOfPlayers) {
                    ListOfPlayersNames.add(player.getName());
                }
                return ListOfPlayersNames;
            }

        } else if (label.toLowerCase().contains("gm") || label.equalsIgnoreCase("gamemode")){
            if (args.length == 1) {
                String[] Options = {
                        "survival",
                        "creative",
                        "adventure",
                        "spectator"
                };
                return Arrays.asList(Options);
            }
        }
        return null;
    }
}


