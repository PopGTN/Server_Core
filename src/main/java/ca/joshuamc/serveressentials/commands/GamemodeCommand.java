package ca.joshuamc.serveressentials.commands;

import ca.joshuamc.serveressentials.ServerEssentials;
import ca.joshuamc.serveressentials.SpigotConst;
import ca.joshuamc.serveressentials.SpigotUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor, SpigotConst {
    private ServerEssentials plugin;

    public GamemodeCommand(ServerEssentials plugin) {
        this.plugin = plugin;
        plugin.getCommand("gamemode").setDescription(SpigotUtil.getLangMsg("gamemode.description"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(label);
        switch (label.toLowerCase()) {
            case "gms":
            case "egms":
            case "survival":
                setgamemode(sender, args, 0, label);
                break;
            case "gmc":
            case "egmc":
            case "creative":
                setgamemode(sender, args, 1, label);
                break;
            case "gmsp":
            case "egmsp":
            case "spectator":
                setgamemode(sender, args, 3, label);
                break;
            case "gma":
            case "egma":
            case "adventure":
                setgamemode(sender, args, 2, label);
                break;
            case default:
                if (getGamemode(args[1]) != 4) {
                sender.sendMessage("Broken RN");
                } else {
                    SpigotUtil.sendLangMsg("gamemode.invalid-gamemode", sender);
                }

        }
        return true;
    }

    private void setgamemode(CommandSender sender, String[] args, int GM, String label) {

        if (!(sender instanceof BlockCommandSender) || !(sender instanceof ConsoleCommandSender)) {
            Player player = (Player) sender;
            if (args.length == 1 && player.hasPermission("se.gamemode.other")) {
                if (SpigotUtil.isPlayerOnline(args[1])) {
                    String gamemode = setGamemode(Bukkit.getPlayerExact(args[1]), GM);
                    Player[] players = {player, Bukkit.getPlayerExact(args[1])};
                  msgGamemodeChange(players, gamemode);
                } else {
                    SpigotUtil.sendLangMsg("gamemode.invalid-player", sender);
                }
            } else if (args.length == 0 && player.hasPermission("se.gamemode")){
                String gamemode = setGamemode(player, GM);
                Player[] players = {player};
                msgGamemodeChange(players, gamemode);
            } else {
                SpigotUtil.sendLangMsg("permission-message", sender);
            }
        } else {
            if (args.length == 1) {
                if (SpigotUtil.isPlayerOnline(args[1]) || SpigotUtil.isPlayerOnline(args[1])) {
                }
            } else {
                sender.sendMessage(SpigotUtil.getLangMsg("gamemode.invalid-player").replaceAll("%commmand%", "/" + label));
            }
        }
    }

    private void msgGamemodeChange(Player[] players, String gamemode) {
        if (players.length == 1){
            players[0].sendMessage(SpigotUtil.getLangMsg("gamemode.self-change").replaceAll("%Sender%", players[0].getName()).replaceAll("%Gamemode%", gamemode).replaceAll("%TargetedPlayer", players[0].getName()));
            SpigotUtil.MsgtoPplWithPerm("se.gamemode.others",players[0],SpigotUtil.getLangMsg("gamemode.self-change2").replaceAll("%Sender%", players[0].getName()).replaceAll("%Gamemode%", gamemode).replaceAll("%TargetedPlayer", players[0].getName()));
        }
        if (players.length == 2){
            players[1].sendMessage(SpigotUtil.getLangMsg("gamemode.victims-message").replaceAll("%Sender%", players[0].getName()).replaceAll("%Gamemode%", gamemode).replaceAll("%TargetedPlayer", players[1].getName()));
            SpigotUtil.MsgtoPplWithPerm("se.gamemode.others",players[2],SpigotUtil.getLangMsg("gamemode.gamemode.broadcast-msg").replaceAll("%Sender%", players[0].getName()).replaceAll("%Gamemode%", gamemode).replaceAll("%TargetedPlayer", players[1].getName()));
        }
    }

    private String setGamemode(Player player, int gamemode) {

        switch (gamemode) {
            case 0:
                player.setGameMode(GameMode.SURVIVAL);
                return "Survival";
            case 1:
                player.setGameMode(GameMode.CREATIVE);
                return "Creative";
            case 2:
                player.setGameMode(GameMode.ADVENTURE);
                return "Adventure";
            case 3:
                player.setGameMode(GameMode.SPECTATOR);
                return "Spectator";
        }
        return null;
    }

    private int getGamemode(String gamemode) {


        if (gamemode.equalsIgnoreCase("survival") || gamemode.equals("0") || gamemode.equalsIgnoreCase("s") || gamemode.equalsIgnoreCase("sur")) {
            return 0;
        } else if (gamemode.equalsIgnoreCase("creative") || gamemode.equals("1") || gamemode.equalsIgnoreCase("c") || gamemode.equalsIgnoreCase("cre")) {
            return 1;
        } else if (gamemode.equalsIgnoreCase("adventure") || gamemode.equals("2") || gamemode.equalsIgnoreCase("a") || gamemode.equalsIgnoreCase("adv")) {
            return 2;
        } else if (gamemode.equalsIgnoreCase("spectator") || gamemode.equals("3") || gamemode.equalsIgnoreCase("sp") || gamemode.equalsIgnoreCase("spec")) {
            return 3;
        }
        return 4;
    }

}

