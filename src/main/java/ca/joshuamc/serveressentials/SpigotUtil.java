package ca.joshuamc.serveressentials;

import org.bukkit.Bukkit;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SpigotUtil {

    private static ConfigUtil langFile;
    /*= new ConfigUtil(Bukkit.getServer().getPluginManager().getPlugin("Server-Essentials").getDataFolder().getAbsolutePath() + "/" +  "Messages.yml");*/

    private static Plugin plugin;


    public static Player getOnlinePlayer(String target, CommandSender sender) {
        Player output = Bukkit.getPlayerExact(target);
        if (output == null) {
            sender.sendMessage(langFile.getConfig().getString("player-target-error"));
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

    /**
     * This will message all the players with a certain permisson
     * @param PermNode
     * @param msg
     */
    public static void MsgtoPplWithPerm(String PermNode, String msg) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission(PermNode)) {
                player.sendMessage(msg);
            }
        }
    }

    /**
     * This Will Message everyone with the perm. Except the targeted Player
     * @param PermNode
     * @param target
     * @param msg
     */
    public static void MsgtoPplWithPerm(String PermNode, Player target, String msg){
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission(PermNode) && !player.getName().equalsIgnoreCase(target.getName())) {
                player.sendMessage(msg);
            }
        }

    }
    public static String getLangMsg(String path){
    String output;
        if(langFile.getConfig().getBoolean("show-prefix") == true){
            output = plugin.getConfig().getString("prefix").replaceAll("&", "§") + " " + langFile.getConfig().getString(path).replaceAll("&", "§");
        } else {
            output = langFile.getConfig().getString(path).replaceAll("&", "§");
        }

        return output;
    }
    public static boolean getMsgBoolean(String path){
        return plugin.getConfig().getBoolean(path);
    }
    public static void sendLangMsg(String path, CommandSender sender){
        String output;
        if(langFile.getConfig().getBoolean("show-prefix")){
            output = plugin.getConfig().getString("prefix").replaceAll("&", "§") + " " + langFile.getConfig().getString(path).replaceAll("&", "§");
        } else {
            output = langFile.getConfig().getString(path).replaceAll("&", "§");
        }
        sender.sendMessage(output);
    }

    public static void sendLangMsg(String path, Player sender) {
        String output;
        if(langFile.getConfig().getBoolean("show-prefix")){
            output = plugin.getConfig().getString("prefix").replaceAll("&", "§") + " " + langFile.getConfig().getString(path).replaceAll("&", "§");
        } else {
            output = langFile.getConfig().getString(path).replaceAll("&", "§");
        }
        sender.sendMessage(output);
    }
    public static boolean isPlayerOnline(String playername) throws Exception{
        Player target = Bukkit.getPlayerExact(playername);
        if (target == null) {
            return false;
        } else {
            return true;
        }
    }

    public static ArrayList<String> getOnlinePlayerNames(){
        List<Player> listOfPlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
        ArrayList<String> ListOfPlayersNames = new ArrayList<String>();
        for (Player player : listOfPlayers) {
            ListOfPlayersNames.add(player.getName());
        }
        return ListOfPlayersNames;
    }

    public static String getConfigString(String path) {
        return plugin.getConfig().getString(path).replaceAll("&","§");
    }

    public static boolean getConfigBoolean(String path) throws Exception {
        return plugin.getConfig().getBoolean(path);
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

    public static void onStartUp(ServerEssentials plugin2){
        plugin = plugin2;
        langFile = new ConfigUtil(plugin.getDataFolder().getAbsolutePath() + "/" + "Messages.yml");

        langFile.getConfig().options().copyDefaults(true);

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
        langFile.getConfig().options().setHeader(header);

        //For all Commands
        langFile.getConfig().addDefault("prefix","(SERVER-CORE)");
        langFile.getConfig().addDefault("show-prefix",true);
        langFile.getConfig().addDefault("permission-message","§4You don't have Permission to use this command. If you feel like this is a mistake please contact the server's administrator.");
        langFile.getConfig().addDefault("player-target-error","§4Player Couldn't be found");
        langFile.getConfig().addDefault("players-only-message", "Players only");


        //flight Commands
        langFile.getConfig().addDefault("flight-command.description", "This will allow you to enable/Disable Flight");
        langFile.getConfig().addDefault("flight-command.command-usage","Please Enter a players name! Example: %Command%");
        langFile.getConfig().addDefault("flight-command.onEnabled","Your flight has been Enabled by %Sender%");
        langFile.getConfig().addDefault("flight-command.onDisabled","Your flight has been disabled by %Sender%");
        langFile.getConfig().addDefault("flight-command.PlayerIsNotInSurvival","§4This Player %TargetedPlayer% is not in Survival Mode");
        langFile.getConfig().addDefault("flight-command.YourNotInSurvival","§4You must be in Survival Mode");
        langFile.getConfig().addDefault("flight-command.senders-message-enabled","%TargetedPlayer% flight has been Enabled");
        langFile.getConfig().addDefault("flight-command.senders-message-disabled","%TargetedPlayer% flight has been disabled");


        //Invincible Commands
        langFile.getConfig().addDefault("invincible.description","Makes You invincible");
        langFile.getConfig().addDefault("invincible.command-usage","Please Enter a players name! Example: %Command%");
        langFile.getConfig().addDefault("invincible.onEnabled","Your invincibility has been Enabled by %Sender%");
        langFile.getConfig().addDefault("invincible.onDisabled","Your invincibility has been disabled by %Sender%");
        langFile.getConfig().addDefault("invincible.PlayerIsNotInSurvival","§4This Player %TargetedPlayer% is not in Survival Mode");
        langFile.getConfig().addDefault("invincible.YourNotInSurvival","§4Your is Not in Survival Mode");
        langFile.getConfig().addDefault("invincible.senders-message-enabled","%TargetedPlayer% invincibility has been Enabled");
        langFile.getConfig().addDefault("invincible.senders-message-disabled","%TargetedPlayer% invincibility has been disabled");


        //Vanish Command
        langFile.getConfig().addDefault("vanish.description","Toggles Vanish off or on");
        langFile.getConfig().addDefault("vanish.command-usage","Please Enter a players name! Example: %Command%");
        langFile.getConfig().addDefault("vanish.onEnabled","Your Vanish has been Enabled by %Sender%");
        langFile.getConfig().addDefault("vanish.onDisabled","Your Vanish has been disabled by %Sender%");
        langFile.getConfig().addDefault("vanish.senders-message-enabled","%Target% Vanish has been Enabled");
        langFile.getConfig().addDefault("vanish.senders-message-disabled","%Target% Vanish has been disabled");

        //Gamemode Command
        langFile.getConfig().addDefault("gamemode.description","Changes your Gamemode");
        langFile.getConfig().addDefault("gamemode.invalid-player","Player wasn't found");
        langFile.getConfig().addDefault("gamemode.invalid-gamemode","Invalid Gamemode");
        langFile.getConfig().addDefault("gamemode.victims-message","%Sender% has Changed your gamemode to %Gamemode%");
        langFile.getConfig().addDefault("gamemode.description","Change your's or other's gamemode");
        langFile.getConfig().addDefault("gamemode.self-change","Your gamemode has changed to %Gamemode%");
        langFile.getConfig().addDefault("gamemode.broadcast-msg","%sSender% has Changed %Target% gamemode  to %Gamemode%");
        langFile.getConfig().addDefault("gamemode.self-change2","%sSender% has Changed their gamemode to %Gamemode%");

        langFile.getConfig().options().copyDefaults(true);
        langFile.save();
    }
}
