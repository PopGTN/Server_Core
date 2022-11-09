package ca.joshuamc.serveressentials.commands;

import ca.joshuamc.serveressentials.CommandConfig;
import ca.joshuamc.serveressentials.util.files.MessageConfig;
import org.bukkit.GameMode;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    /*MessageConfig.get().getString("variable name").replaceAll("&" , "§")*/

    public final String VICTIMS_MESSAGE = MessageConfig.get().getString("gamemode.victims-message").replaceAll("&" , "§");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //basic check for the type of Sender
        boolean isplayer = sender instanceof Player;
        boolean isConsole = sender instanceof ConsoleCommandSender;
        boolean isCmdBlock = sender instanceof BlockCommandSender;

        if (isplayer) {
            Player player = (Player) sender;
            if (!player.hasPermission("se.gamemode")) {
                sender.sendMessage(CommandConfig.getInvalidPermsMsg());

                return true;
            } else {
                String gamemode = setGamemode(player, args[0]);
                if (!gamemode.equals(null)) {
                    player.sendMessage("Your game has changed to " + gamemode);
                } else{
                    player.sendMessage("Invalid Gamemode!");
                }
            }
        } /*else if(args.length ==2) {
            ;
            return true;
        }*/ else if(!isplayer) {
            sender.sendMessage(MessageConfig.get().getString("gamemode.command-usage").replaceAll("&" , "§"));
        }
        return true;
    }

    private String setGamemode(Player player, String gamemode) {


        if (gamemode.equalsIgnoreCase("survival") || gamemode.equals("0") || gamemode.equalsIgnoreCase("s")) {

            player.setGameMode(GameMode.SURVIVAL);

            return "Survival";
        } else if (gamemode.equalsIgnoreCase("creative") || gamemode.equals("1") || gamemode.equalsIgnoreCase("c")) {

            player.setGameMode(GameMode.CREATIVE);

            return "Creative";
        } else if (gamemode.equalsIgnoreCase("adventure") || gamemode.equals("2") || gamemode.equalsIgnoreCase("a")) {

            player.setGameMode(GameMode.ADVENTURE);

            return "Adventure";
        } else if (gamemode.equalsIgnoreCase("spectator") || gamemode.equals("3") || gamemode.equalsIgnoreCase("sp")) {

            player.setGameMode(GameMode.SPECTATOR);

            return "Spectator";
        } else if (gamemode.equalsIgnoreCase("default") || gamemode.equals("4") || gamemode.equalsIgnoreCase("d")) {
            player.setGameMode(GameMode.SURVIVAL);
            return "Default";
        }
        return null;
    }

}


