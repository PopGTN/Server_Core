package Testlab;


import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public final class ServerEssentials extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        ExampleEvent exampleEvent = new ExampleEvent("Msrules123"); // Initialize your Event
        Bukkit.getPluginManager().callEvent(exampleEvent); // This fires the event and allows any listener to listen to the event
        if (!exampleEvent.isCancelled()) {
            Bukkit.getPlayer("Msrules123").sendMessage(exampleEvent.getPlayerName()); // Use your event's data
        }
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Server-Essentials as been stopped!");

    }
    public void registerEvents() {

    }
}
