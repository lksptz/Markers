package me.putz.MarkerPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class CommandRemoveMarker implements CommandExecutor {
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	if (sender instanceof Player) {
    		Player pl = (Player) sender;
    		if (args.length == 0) {
    			if (pl.hasMetadata("removemarker")) {
        			pl.removeMetadata("removemarker", Main.getInstance());
        			pl.sendMessage(ChatColor.RED + "Disabled removing markers");
        		} else {
        			pl.setMetadata("removemarker", new FixedMetadataValue(Main.getInstance(), 0));
        			pl.sendMessage(ChatColor.GREEN + "Enabled removing markers");
        		}
    		} else if (args.length == 1) {
    			if (args[0].equals("off")) {
    				if (pl.hasMetadata("removemarker")) {
            			pl.removeMetadata("removemarker", Main.getInstance());
            		}
    				pl.sendMessage(ChatColor.RED + "Disabled removing markers");
    			} else if (args[0].equals("on")) {
    				if (!pl.hasMetadata("removemarker")) {
    					pl.setMetadata("removemarker", new FixedMetadataValue(Main.getInstance(), 0));
            		}
    				pl.sendMessage(ChatColor.GREEN + "Enabled removing markers");
    			} else {
    				return false; //invalid argument
    			}
    		} else {
    			return false; //invalid count of arguments
    		}
    	}
    	return true;
    }

}
