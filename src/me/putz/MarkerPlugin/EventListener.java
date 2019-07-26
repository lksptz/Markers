package me.putz.MarkerPlugin;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EventListener implements Listener {
	
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity().hasMetadata("ismarker")) {
			event.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity().hasMetadata("ismarker")) {
			if (event.getDamager().hasMetadata("removemarker")) {
				event.getEntity().remove();
			} else {
				event.setCancelled(true);
			}
		}
	}
		
	
	@EventHandler
	public void onEntityDropItem(EntityDropItemEvent event) {
		Entity e = event.getEntity();
		if (e.hasMetadata("ismarker")) {
			event.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void onEntityClick(PlayerInteractEntityEvent event) {
		Player pl = event.getPlayer();
		Entity e = event.getRightClicked();
		if (pl.hasMetadata("removemarker") && e.hasMetadata("ismarker")) {
			e.remove();
		}
	}
	
	
	@EventHandler
	public void onEntityPotionEffect(EntityPotionEffectEvent event) {
		Entity e = event.getEntity();
		if (e.hasMetadata("ismarker") && event.getAction() == EntityPotionEffectEvent.Action.REMOVED) {
			if (e.hasMetadata("isnamed")) {
				new CommandSetMarker().setMarkerAtLocation(e.getWorld(), e.getLocation(), e.getName());
			} else  {
				new CommandSetMarker().setMarkerAtLocation(e.getWorld(), e.getLocation(), "");
			}
			e.remove();
		}
	}
	
	
}
