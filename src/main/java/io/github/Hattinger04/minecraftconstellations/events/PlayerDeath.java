/**
 * @author Simon Greiderer
 * 
 * Not tested!
 */
package io.github.Hattinger04.minecraftconstellations.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import io.github.Hattinger04.minecraftconstellations.constellations.Constellations;

public class PlayerDeath implements Listener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		
		Player killed = event.getEntity(); 
		Player killer = null; 
		if(killed.getKiller() instanceof Player) {
			killer = event.getEntity().getKiller(); 
			Constellations.addStatsToColor(Constellations.getColorFromPlayer(killer), 1); 
			Constellations.addStatsToColor(Constellations.getColorFromPlayer(killed), -1); 
			return; 
		}
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			if(Constellations.getColorFromPlayer(p) != Constellations.getColorFromPlayer(killed)) {
				p.sendMessage(event.getDeathMessage());
			}
		}
		event.setDeathMessage(null);
	}
}
