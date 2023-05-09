package ca.joshuamc.serveressentials.commands;

import ca.joshuamc.serveressentials.CommandConfig;
import ca.joshuamc.serveressentials.util.files.MessageConfig;
import org.bukkit.GameMode;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    private final String VICTIMS_MESSAGE = MessageConfig.get().getString("gamemode.victims-message").replaceAll("&", "§");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("se.gamemode")) {
                player.sendMessage(CommandConfig.getInvalidPermsMsg());
                return true;
            } else {
                if (args.length > 0) {
                    String gamemode = setGamemode(player, args[0]);
                    if (gamemode != null) {
                        player.sendMessage("Your game mode has been changed to " + gamemode);
                    } else {
                        player.sendMessage("Invalid gamemode!");
                    }
                } else {
                    player.sendMessage("Usage: /gamemode <mode>");
                }
            }
        } else {
            sender.sendMessage(MessageConfig.get().getString("gamemode.command-usage").replaceAll("&", "§"));
        }
        return true;
    }

    private String setGamemode(Player player, String gamemode) {
        switch (gamemode.toLowerCase()) {
            case "survival":
            case "0":
            case "s":
                player.setGameMode(GameMode.SURVIVAL);
                return "Survival";
            case "creative":
            case "1":
            case "c":
                player.setGameMode(GameMode.CREATIVE);
                return "Creative";
            case "adventure":
            case "2":
            case "a":
                player.setGameMode(GameMode.ADVENTURE);
                return "Adventure";
            case "spectator":
            case "3":
            case "sp":
                player.setGameMode(GameMode.SPECTATOR);
                return "Spectator";
            case "default":
            case "4":
            case "d":
                player.setGameMode(GameMode.SURVIVAL);
                return "Default";
            default:
                return null;
        }
    }
}
