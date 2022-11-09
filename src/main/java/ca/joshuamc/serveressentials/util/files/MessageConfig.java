package ca.joshuamc.serveressentials.util.files;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MessageConfig {
    private static File file;
    private static FileConfiguration customFile;

    //Finds or generates the custom config file
    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Server-Essentials").getDataFolder(), "Messages.yml");

        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e) {
                System.out.println("A error has occurred when trying to Create a new Messages.yml file");
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }
    public static FileConfiguration get(){
        return customFile;
    }
    public static void save(){
        try {
            customFile.save(file);
        }catch (IOException e){
            System.out.println("A error has occurred when trying to save the Messages.yml file");
        }
    }

    public static void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }
    public static void configDefaults(){
        ArrayList<String> header = new ArrayList<String>();
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
        header.add("Prefix's: %Sender%, %TargetedPlayer%, %command%");
        header.add("============================================================");
        header.add(" ");
        get().options().setHeader(header);

        //For all Commands
        get().addDefault("permission-message","§4You don't have Permission to use this command. If you feel like this is a mistake please contact the server's administrator.");
        get().addDefault("player-target-error","§4Player Couldn't be found");
        get().addDefault("players-only-message", "Players only");


        //flight Commands
        get().addDefault("flight-command.command-usage","Please Enter a players name! Example: %commmand%");
        get().addDefault("flight-command.onEnabled","Your flight has been Enabled by %Sender%");
        get().addDefault("flight-command.onDisabled","Your flight has been disabled by %Sender%");
        get().addDefault("flight-command.PlayerIsNotInSurvival","§4This Player %TargetedPlayer% is not in Survival Mode");
        get().addDefault("flight-command.YourNotInSurvival","§4You must be in Survival Mode");
        get().addDefault("flight-command.senders-message-enabled","%TargetedPlayer% flight has been Enabled");
        get().addDefault("flight-command.senders-message-disabled","%TargetedPlayer% flight has been disabled");


        //Invincible Commands
        get().addDefault("invincible.command-usage","Please Enter a players name! Example: %commmand%");
        get().addDefault("invincible.onEnabled","Your invincibility has been Enabled by %Sender%");
        get().addDefault("invincible.onDisabled","Your invincibility has been disabled by %Sender%");
        get().addDefault("invincible.PlayerIsNotInSurvival","§4This Player %TargetedPlayer% is not in Survival Mode");
        get().addDefault("invincible.YourNotInSurvival","§4Your is Not in Survival Mode");
        get().addDefault("invincible.senders-message-enabled","%TargetedPlayer% invincibility has been Enabled");
        get().addDefault("invincible.senders-message-disabled","%TargetedPlayer% invincibility has been disabled");


        //Vanish Command
        get().addDefault("vanish.command-usage","Please Enter a players name! Example: %commmand%");
        get().addDefault("vanish.onEnabled","Your Vanish has been Enabled by %Sender%");
        get().addDefault("vanish.onDisabled","Your Vanish has been disabled by %Sender%");
        get().addDefault("vanish.senders-message-enabled","%TargetedPlayer% Vanish has been Enabled");
        get().addDefault("vanish.senders-message-disabled","%TargetedPlayer% Vanish has been disabled");

        //Gamemode Command
        get().addDefault("gamemode.command-usage","Please Enter a players name! Example: %commmand%");
        get().addDefault("gamemode.command-usage","Please Enter a players name! Example: %commmand%");
        get().addDefault("gamemode.victims-message","%Sender% has Changed your gamemode to %gamemode%");
        get().addDefault("gamemode.senders-message-disabled","%TargetedPlayer% invincibility has been disabled");


    }
}

