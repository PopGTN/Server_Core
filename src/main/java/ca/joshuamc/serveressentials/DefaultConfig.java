package ca.joshuamc.serveressentials;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class DefaultConfig {

    //config File Getters & Setters
    private static String WELCOME_MESSAGE;
    private static String SERVER_NAME;
    //switch for one time Welcome Message
    private static Boolean WELCOME_MESSAGE_SWITCH;
    private static String JOIN_MESSAGE;
    private static String LEAVE_MESSAGE;
    private static String INVALID_PERMISSION;

    private static boolean EIGHTBALL_BQUESTION;
    private static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Server-Essentials");


    public static String getWelcomeMessage() {
        WELCOME_MESSAGE = plugin.getConfig().getString("welcome-message").replaceAll("&","§");
        return WELCOME_MESSAGE;
    }

    public static String getJoinMessage() {
        JOIN_MESSAGE = plugin.getConfig().getString("join-message").replaceAll("&","§");
        return JOIN_MESSAGE;
    }

    public static Boolean getWelcomeMessageSwitch() {
        WELCOME_MESSAGE_SWITCH = plugin.getConfig().getBoolean("First-time-only");
        return WELCOME_MESSAGE_SWITCH;
    }

    public static String getLeaveMessage() {
        LEAVE_MESSAGE = plugin.getConfig().getString("leave-message").replaceAll("&","§");
        return LEAVE_MESSAGE;
    }

    public static String getServerName () {
        SERVER_NAME = plugin.getConfig().getString("server-name").replaceAll("&","§");
        return SERVER_NAME;
    }

    public static String getInvalidPermission(ServerEssentials plugin) {
        INVALID_PERMISSION = plugin.getConfig().getString("permission-message").replaceAll("&","§");
        return INVALID_PERMISSION;
    }
    public static boolean getEightBallQuestion() {
        EIGHTBALL_BQUESTION = plugin.getConfig().getBoolean("broadcast-question");
        return EIGHTBALL_BQUESTION;
    }
}
