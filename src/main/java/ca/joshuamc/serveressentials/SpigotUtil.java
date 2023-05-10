package ca.joshuamc.serveressentials;

import org.bukkit.Bukkit;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpigotUtil implements SpigotConst {

    private static ServerEssentials plugin;
    private static ConfigUtil LANG_FILE;



    public static Player getOnlinePlayer(String target, CommandSender sender) {
        Player output = Bukkit.getPlayerExact(target);
        if (output == null) {
            sender.sendMessage(LANG_FILE.getConfig().getString("player-target-error"));
            return null;
        }
        return output;
    }

    public static List getlistOfOp(){
        List<Player> players = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.isOp()) {
                players.add(player);
            }
        }

        return players;
    }
    public static List whoHasPermission(String PermNode){
        List<Player> players = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission(PermNode)) {
                players.add(player);
            }
        }

        return players;
    }

    public static void MsgtoPplWithPerm(String PermNode, String msg){
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission(PermNode)) {
                player.sendMessage(msg);
            }
        }
    }

    public static void MsgtoPplWithPerm(String PermNode, Player target, String msg){
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission(PermNode) && !player.getName().equalsIgnoreCase(target.getName())) {
                player.sendMessage(msg);
            }
        }

    }
    public static String getLangMsg(String path){
    String output;
        if(PLUGIN.getConfig().getBoolean("plugin-prefix") == true){
            output = PLUGIN.getConfig().getString("prefix").replaceAll("&", "§") + " " + LANG_FILE.getConfig().getString(path).replaceAll("&", "§");
        } else {
            output = LANG_FILE.getConfig().getString(path).replaceAll("&", "§");
        }

        return output;
    }
    public static void sendLangMsg(String path, CommandSender sender){
        String output;
        if(plugin.getConfig().getBoolean("plugin-prefix")){
            output = plugin.getConfig().getString("prefix").replaceAll("&", "§") + " " + LANG_FILE.getConfig().getString(path).replaceAll("&", "§");
        } else {
            output = LANG_FILE.getConfig().getString(path).replaceAll("&", "§");
        }
        sender.sendMessage(output);
    }

    public static void sendLangMsg(String path, Player sender){
        String output;
        if(plugin.getConfig().getBoolean("plugin-prefix")){
            output = plugin.getConfig().getString("prefix").replaceAll("&", "§") + " " + LANG_FILE.getConfig().getString(path).replaceAll("&", "§");
        } else {
            output = LANG_FILE.getConfig().getString(path).replaceAll("&", "§");
        }
        sender.sendMessage(output);
    }
    public static boolean isPlayerOnline(String playername){
        Player target = Bukkit.getPlayerExact(playername);
        if (target == null) {
            return false;
        } else {
            return true;
        }
    }



    public static boolean isPlayer(CommandSender sender){
        return sender instanceof Player;
    }

    public static boolean isConsole(CommandSender sender){
        return sender instanceof ConsoleCommandSender;
    }

    public static boolean isCmdBlock(CommandSender sender){
        return sender instanceof BlockCommandSender;
    }

    public static void configDefaults(ServerEssentials plugin2){
        plugin = plugin2;
         LANG_FILE = new ConfigUtil(plugin.getDataFolder().getAbsolutePath() + "/" + "Messages.yml");

        LANG_FILE.getConfig().options().copyDefaults(true);

        ArrayList<String> header = new ArrayList<>();
        header.add("============================================================");
        header.add(" ");
        header.add(" ");
        header.add(" Customize the plugin's Message");
        header.add(" This is the documentation for all the Messages Below");
        header.add(" ");
        header.add(" ");
        header.add("============================================================");
        header.add("You can customize every message here for different Languages can be used");
        header.add("You can use color codes with & instead of put this § everytime.");
        header.add("Prefix's: %Sender%, %TargetedPlayer%, %Command%");
        header.add("============================================================");
        header.add(" ");
        LANG_FILE.getConfig().options().setHeader(header);

        //For all Commands
        LANG_FILE.getConfig().addDefault("prefix","(SERVER-CORE)");
        LANG_FILE.getConfig().addDefault("permission-message","§4You don't have Permission to use this command. If you feel like this is a mistake please contact the server's administrator.");
        LANG_FILE.getConfig().addDefault("player-target-error","§4Player Couldn't be found");
        LANG_FILE.getConfig().addDefault("players-only-message", "Players only");


        //flight Commands
        LANG_FILE.getConfig().addDefault("flight-command.description", "This will allow you to enable/Disable Flight");
        LANG_FILE.getConfig().addDefault("flight-command.command-usage","Please Enter a players name! Example: %Command%");
        LANG_FILE.getConfig().addDefault("flight-command.onEnabled","Your flight has been Enabled by %Sender%");
        LANG_FILE.getConfig().addDefault("flight-command.onDisabled","Your flight has been disabled by %Sender%");
        LANG_FILE.getConfig().addDefault("flight-command.PlayerIsNotInSurvival","§4This Player %TargetedPlayer% is not in Survival Mode");
        LANG_FILE.getConfig().addDefault("flight-command.YourNotInSurvival","§4You must be in Survival Mode");
        LANG_FILE.getConfig().addDefault("flight-command.senders-message-enabled","%TargetedPlayer% flight has been Enabled");
        LANG_FILE.getConfig().addDefault("flight-command.senders-message-disabled","%TargetedPlayer% flight has been disabled");


        //Invincible Commands
        LANG_FILE.getConfig().addDefault("invincible.description","Makes You invincible");
        LANG_FILE.getConfig().addDefault("invincible.command-usage","Please Enter a players name! Example: %Command%");
        LANG_FILE.getConfig().addDefault("invincible.onEnabled","Your invincibility has been Enabled by %Sender%");
        LANG_FILE.getConfig().addDefault("invincible.onDisabled","Your invincibility has been disabled by %Sender%");
        LANG_FILE.getConfig().addDefault("invincible.PlayerIsNotInSurvival","§4This Player %TargetedPlayer% is not in Survival Mode");
        LANG_FILE.getConfig().addDefault("invincible.YourNotInSurvival","§4Your is Not in Survival Mode");
        LANG_FILE.getConfig().addDefault("invincible.senders-message-enabled","%TargetedPlayer% invincibility has been Enabled");
        LANG_FILE.getConfig().addDefault("invincible.senders-message-disabled","%TargetedPlayer% invincibility has been disabled");


        //Vanish Command
        LANG_FILE.getConfig().addDefault("vanish.description","Toggles Vanish off or on");
        LANG_FILE.getConfig().addDefault("vanish.command-usage","Please Enter a players name! Example: %Command%");
        LANG_FILE.getConfig().addDefault("vanish.onEnabled","Your Vanish has been Enabled by %Sender%");
        LANG_FILE.getConfig().addDefault("vanish.onDisabled","Your Vanish has been disabled by %Sender%");
        LANG_FILE.getConfig().addDefault("vanish.senders-message-enabled","%TargetedPlayer% Vanish has been Enabled");
        LANG_FILE.getConfig().addDefault("vanish.senders-message-disabled","%TargetedPlayer% Vanish has been disabled");

        //Gamemode Command
        LANG_FILE.getConfig().addDefault("gamemode.description","Changes your Gamemode");
        LANG_FILE.getConfig().addDefault("gamemode.invalid-player","Player wasn't found");
        LANG_FILE.getConfig().addDefault("gamemode.invalid-gamemode","Invalid Gamemode");
        LANG_FILE.getConfig().addDefault("gamemode.victims-message","%Sender% has Changed your gamemode to %G  amemode%");
        LANG_FILE.getConfig().addDefault("gamemode.description","Change your's or other's gamemode");
        LANG_FILE.getConfig().addDefault("gamemode.self-change","Your gamemode has changed to %Gamemode%");
        LANG_FILE.getConfig().addDefault("gamemode.broadcast-msg","%sSender% has Changed %TargetedPlayer% gamemode  to %Gamemode%");
        LANG_FILE.getConfig().addDefault("gamemode.self-change2","%sSender% has Changed their gamemode to %Gamemode%");

        LANG_FILE.getConfig().options().copyDefaults(true);
        LANG_FILE.save();
    }
}
