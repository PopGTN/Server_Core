package ca.joshuamc.serveressentials;


import ca.joshuamc.serveressentials.commands.*;
import ca.joshuamc.serveressentials.listeners.JoinAndLeaveMessage;
import ca.joshuamc.serveressentials.util.files.MessageConfig;
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
        MessageConfig.setup();
        MessageConfig.configDefaults();
        MessageConfig.get().options().copyDefaults(true);
        MessageConfig.save();

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
    public void registerCommands(ServerEssentials serverEssentials) {


        //flight
        serverEssentials.getCommand("fly").setExecutor(new FlightCommand());

        //invincible
//        serverEssentials.getCommand("invincible").setExecutor(new InvincibleCommand());

        //Vanish
        serverEssentials.getCommand("vanish").setExecutor(new VanishCommand(this));

        //Shout
        serverEssentials.getCommand("shout").setExecutor(new ShoutCommand());

        //Gamemode Command
        serverEssentials.getCommand("gamemode").setExecutor(new GamemodeCommand());


        serverEssentials.getCommand("8ball").setExecutor(new EightBall());
    }
}
