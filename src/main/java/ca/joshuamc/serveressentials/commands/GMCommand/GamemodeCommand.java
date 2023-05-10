package ca.joshuamc.serveressentials.commands;

import ca.joshuamc.serveressentials.ServerEssentials;
import ca.joshuamc.serveressentials.SpigotUtil;
import org.bukkit.GameMode;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
    ServerEssentials plugin;

    public GamemodeCommand(ServerEssentials plugin) {
        this.plugin = plugin;
        plugin.getCommand("gamemode").setDescription(SpigotUtil.getLangMsg("gamemode.description"));
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage(label);
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("se.gamemode")) {
                sender.sendMessage("Invalid Permissions");

                return true;
            } else if (args.length == 2) {
                sender.sendMessage("Not Implemented Yet!");
            } else {
                String gamemode = setGamemode(player, args[0]);
                if (!gamemode.equals(null)) {
                    player.sendMessage("Your game has changed to " + gamemode);
                } else {
                    player.sendMessage("Invalid Gamemode!");
                }
            }


        } else if (sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender) {
            sender.sendMessage(SpigotUtil.getLangMsg("gamemode.command-usage"));
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
                player.setGameMode(GameMode.SURVIVAL);
                return "Default";
            default:
                return null;
        }
    }
}


