package ca.joshuamc.serveressentials.files;


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
        header.add("============================================================");
        header.add(" ");
        header.add("These one's below effect All commands");
        header.add(" ");
        header.add("============================================================");
        header.add("permission-message: The Message for when players Don't have permissons to use a command");
        header.add(" ");
        header.add("player-target-error: The Error message when Players Can't be found");
        header.add(" ");
        header.add("============================================================");
        header.add("Flight Command Message");
        header.add("Prefix's: %Sender%, %TargetedPlayer%, %command%");
        header.add("============================================================");
        header.add(" ");
        header.add("command-usage: Command Usage");
        header.add("flight-enabled: The Message that is sent to player that's fly was Enabled.");
        header.add("flight-disabled: The Message that is sent to player that's fly was Disabled.");
        header.add("flight-PlayerIsIn: the The Message that  is sent when the player is in Creative/spectator Mode.");
        header.add("flight-senders-message-enabled: Message from Command Sender when targeted player fly is enabled");
        header.add("flight-senders-message-disable: Message from Command Sender when targeted player fly is Disabled");
        header.add(" ");
        header.add("============================================================");
        get().options().setHeader(header);

        //For all Commands
        get().addDefault("permission-message","§4You don't have Permission to use this command. If you feel like this is a mistake please contact the server's administrator.");
        get().addDefault("player-target-error","§4Player Couldn't be found");

        //flight Commands

        get().addDefault("flight-command.command-usage","Please Enter a players name! Example: %commmand%");

        get().addDefault("flight-command.onEnabled","Your flight has been Enabled by %Sender%");

        get().addDefault("flight-command.onDisabled","Your flight has been disabled by %Sender%");

        get().addDefault("flight-command.PlayerIsNotInSurvival","§4This Player %TargetedPlayer% is not in Survival Mode");

        get().addDefault("flight-command.YourNotInSurvival","§4Your is Not in Survival Mode");

        get().addDefault("flight-command.senders-message-enabled","%TargetedPlayer% flight has been Enabled");

        get().addDefault("flight-command.senders-message-disabled","%TargetedPlayer% flight has been disabled");

    }

}
