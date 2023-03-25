package ca.joshuamc.serveressentials;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpigotTools {

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

}
