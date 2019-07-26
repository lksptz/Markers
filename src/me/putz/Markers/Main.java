package me.putz.Markers;

import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	static Plugin instance;
	
	
	public static Plugin getInstance() {
		return instance;
	}
	
	
    // Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	instance = this;
    	this.getCommand("setmarker").setExecutor(new CommandSetMarker());
    	this.getCommand("removemarker").setExecutor(new CommandRemoveMarker());
    	getServer().getPluginManager().registerEvents(new EventListener(), this);
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    	for (Entity e : getServer().getWorld("world").getEntities()) {
    		if (e.hasMetadata("ismarker")) {
    			e.remove();
    		}
    	}
    }
}
