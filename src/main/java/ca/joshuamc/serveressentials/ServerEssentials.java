package ca.joshuamc.serveressentials;


import ca.joshuamc.serveressentials.commands.FlightCommand;
import ca.joshuamc.serveressentials.events.JoinAndLeaveMessage;
import ca.joshuamc.serveressentials.util.files.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

//        List<String> messages = new List<String>();
//        supplierNames.add("#Message the player would receive if they done got permission to use the commands");
//        supplierNames.add("");
//        supplierNames.add("sup3");
public final class ServerEssentials extends JavaPlugin implements Listener {
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
        // Plugin shutdown logic
        System.out.println("Server-Essentials as been stopped!");

    }
    public void registerEvents() {
        PluginManager pm = Bukkit.getServer().getPluginManager();

        pm.registerEvents(new JoinAndLeaveMessage(), this);
    }
    public static void registerCommands(ServerEssentials serverEssentials) {


        //flight Commmand
        serverEssentials.getCommand("fly").setExecutor(new FlightCommand());

        //vanish



        //gamemode
    }
}
