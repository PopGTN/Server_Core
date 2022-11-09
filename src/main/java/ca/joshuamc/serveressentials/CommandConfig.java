package ca.joshuamc.serveressentials;

import ca.joshuamc.serveressentials.util.files.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandConfig {
    private static String INVALID_PERMS_MSG;
    private static String PLAYERS_ONLY_MSG;

    private static String PLAYER_TARGET_ERROR_MSG;

    public static String getInvalidPermsMsg() {
        INVALID_PERMS_MSG = MessageConfig.get().getString("permission-message").replaceAll("&" , "§");
        return INVALID_PERMS_MSG;
    }

    public static String getPlayersOnlyMsg() {
        PLAYERS_ONLY_MSG = MessageConfig.get().getString("players-only-message").replaceAll("&" , "§");
        return PLAYERS_ONLY_MSG;
    }

    public static String getPlayerTargetErrorMsg() {
        PLAYER_TARGET_ERROR_MSG = MessageConfig.get().getString("player-target-error").replaceAll("&" , "§");
        return PLAYER_TARGET_ERROR_MSG;
    }

    /**
     *
     * @param target The player your selecting
     * @param sender Who's looking for the Player
     * @return Returns a Player entity
     */
    public static Player getOnlinePlayer(String target, CommandSender sender) {
        Player output = Bukkit.getPlayerExact(target);
        if (output == null) {
            sender.sendMessage(MessageConfig.get().getString("player-target-error"));
            return null;
        }
        return output;
    }
}
