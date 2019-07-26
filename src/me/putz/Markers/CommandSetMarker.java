package me.putz.Markers;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.loot.LootTables;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandSetMarker implements CommandExecutor{
	
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	if (sender instanceof Player) {
    		Player pl = (Player) sender;
    		World w = pl.getWorld();
    		Location plLoc = pl.getLocation();
    		Location loc = new Location(w, ((int) plLoc.getBlockX()) + 0.5, ((int) plLoc.getBlockY()) + 0.51/2, ((int) plLoc.getBlockZ()) + 0.5);
    		if (args.length == 0) {
    			setMarkerAtLocation(w, loc, "");
    		} else if (args.length == 1) {
    			setMarkerAtLocation(w, loc, args[0]);
    		} else {
    			pl.sendMessage("Invalid arguments!");
    			return false; //invalid count of arguments, only 1 for name allowed
    		}
    		
    		
    		return true;
    	}
    	
    	
    	return false; //only players can set markers
    }
	
	
	public void setMarkerAtLocation(World w, Location loc, String name) {
		Slime marker = (Slime)w.spawnEntity(loc, EntityType.SLIME);
		marker.setAI(false);
		PotionEffect pe = new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1, false, false, false);
		marker.addPotionEffect(pe);
		marker.setInvulnerable(true);
		marker.setLootTable(LootTables.EMPTY.getLootTable());
		marker.setMetadata("ismarker", new FixedMetadataValue(Main.getInstance(), 0));
		marker.setSize(1);
		
		
		if (name != "") {
			marker.setCustomName(name);
			marker.setCustomNameVisible(true);
			marker.setMetadata("isnamed", new FixedMetadataValue(Main.getInstance(), 0));
		}
	}

	
}
