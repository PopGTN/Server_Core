package ca.joshuamc.serveressentials;


import ca.joshuamc.serveressentials.commands.*;
import ca.joshuamc.serveressentials.commands.SpawnCommand.SetSpawnCommand;
import ca.joshuamc.serveressentials.commands.SpawnCommand.SpawnCommand;
import ca.joshuamc.serveressentials.listeners.JoinAndLeaveMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


public final class ServerEssentials extends JavaPlugin implements Listener {
    public ArrayList<Player> vanishlist = new ArrayList<>();
    @Override
    public void onEnable() {
        //Default Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Message.yml Custom Config
        SpigotUtil.configDefaults(this);

        System.out.println("Server-Essentials as been Started!");
        registerEvents();
        registerCommands(this);

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic;
        System.out.println("Server-Essentials as been stopped!");

    }
    public void registerEvents() {
        PluginManager pm = Bukkit.getServer().getPluginManager();

        pm.registerEvents(new JoinAndLeaveMessage(this), this);


    }
    public void registerCommands(ServerEssentials plugin) {
        //SetSpawn
        plugin.getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        //Spawn
        plugin.getCommand("spawn").setExecutor(new SpawnCommand(this));

        //flight
        plugin.getCommand("fly").setExecutor(new FlightCommand(this));

        //invincible
//        serverEssentials.getCommand("invincible").setExecutor(new InvincibleCommand());

        //Vanish
        plugin.getCommand("vanish").setExecutor(new VanishCommand(this));

        //Shout
        //Gamemode Command
        plugin.getCommand("gamemode").setExecutor(new GamemodeCommand(this));

    }
}
