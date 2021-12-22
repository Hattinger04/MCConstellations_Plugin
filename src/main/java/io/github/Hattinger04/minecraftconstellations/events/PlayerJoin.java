/**
 * @author Simon Greiderer
 */

package io.github.Hattinger04.minecraftconstellations.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import io.github.Hattinger04.minecraftconstellations.mysql.MySQLStats;
import io.github.Hattinger04.minecraftconstellations.time.FightingSchedule;
import io.github.Hattinger04.minecraftconstellations.MessageTemplate;
import io.github.Hattinger04.minecraftconstellations.constellations.Constellations;
import io.github.Hattinger04.minecraftconstellations.constellations.EConstellations;
import net.md_5.bungee.api.ChatColor;

public class PlayerJoin implements Listener{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if(Constellations.isInConstellation(event.getPlayer())) {
			Constellations.addPlayerToConstellation(MySQLStats.getConstellation(), event.getPlayer()); 
			Constellations.setPlayerColor(event.getPlayer()); 
			MessageTemplate.sendMessageToPlayer("Du bist in einer Constellation, gut gemacht.", event.getPlayer(), ChatColor.GREEN);
		} else {
			Constellations.addPlayerToConstellation(EConstellations.Nothing, event.getPlayer()); 
			MessageTemplate.sendMessageToPlayer("Du bist in noch keiner Constellation!!", event.getPlayer(), ChatColor.RED);

		}
		if(FightingSchedule.isFightingEnabled()) {
			MessageTemplate.sendMessageToPlayer("PvP ist zurzeit enabled", event.getPlayer());
		} else if(!FightingSchedule.isFightingEnabled()) {
			MessageTemplate.sendMessageToPlayer("PvP ist zurzeit disabled", event.getPlayer());
		}
		
	}
}
