/**
 * @author Simon Greiderer
 */

package io.github.Hattinger04.minecraftconstellations.constellations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Constellations {
	
	private static HashMap<EConstellations, ArrayList<Player>> constellations; 
	private static HashMap<EConstellations, Integer> constellationsStats; 
	
	/**
	 * To initialize / load the constellations
	 * => has to be called first when using constellations
	 */
	public static void initializeConstellations() {
		constellations = loadConstellations(); 
		constellationsStats = loadConstellationStats(); 
	}
	
	
	private static HashMap<EConstellations, ArrayList<Player>> loadConstellations() {
		 HashMap<EConstellations, ArrayList<Player>> constellation = new HashMap<EConstellations, ArrayList<Player>>();
		 for(EConstellations color : EConstellations.values()) {
			 constellation.put(color, new ArrayList<Player>()); 
		 }
		 // MySQL DB Zugriff => laden der Daten (mit addPlayertoConstellation)
		 
		 return constellation; 
	}
	private static HashMap<EConstellations, Integer> loadConstellationStats() {
		 HashMap<EConstellations, Integer> constellationStats = new HashMap<EConstellations, Integer>();
		 for(EConstellations color : EConstellations.values()) {
			 constellationStats.put(color, 100); 
		 }
		 // MySQL DB Zugriff => laden der Daten
		 
		 return constellationStats; 
	}
	
	public static boolean isInConstellation(Player player) {
		for(EConstellations color : EConstellations.values()) {
			if(getPlayersFromColor(color) == player) {
				return true; 
			}
		 }
		return false; 
	}
	
	/**
	 * Returns boolean => exceptions are missing!
	 * 
	 * @param color
	 * @param player
	 * @return
	 */
	public static EConstellations addPlayerToConstellation(EConstellations color, Player player) {
		ArrayList<Player> playerList = constellations.get(color); 
		playerList.add(player); 
		constellations.remove(color); 
		constellations.put(color, playerList); 
		
		// Bissale a pfusch denk i aber so funkts
		for(EConstellations cons : EConstellations.values()) {
			if(cons != color) {
				if(constellations.get(cons).contains(player)) {
					constellations.get(cons).remove(player); 
				}
			}
		}
		setPlayerColor(player); 

		return color; 
	}
	
	/**
	 * Returns boolean => exceptions are missing!
	 * 
	 * @param color
	 * @param player
	 * @return
	 */
	public static boolean removePlayerFromConstellation(EConstellations color, Player player) {
		ArrayList<Player> playerList = constellations.get(color); 
		if(playerList.contains(player)) {
			playerList.remove(player); 
			constellations.remove(color); 
			constellations.put(color, playerList); 
			setPlayerColor(player); 
			return true; 
		}
		return false; 
	}
	
	public static EConstellations getColorFromPlayer(Player player) {
	    for (Entry<EConstellations, ArrayList<Player>> entry : constellations.entrySet()) {
	    	for(Player p : entry.getValue()) {
	    		if (Objects.equals(player, p)) {
		        	return entry.getKey();
		        }
	    	}
	    }
	    return EConstellations.Nothing; 
	}
	
	public static ArrayList<Player> getPlayersFromColor(EConstellations color) {
		return constellations.get(color); 
	}
	
	/**
	 * Sets players color name tag.
	 * Returns boolean => exceptions are missing!
	 * 
	 * @param player
	 * @return
	 */
	public static boolean setPlayerColor(Player player) {
		ChatColor chatColor = getChatColorFromColor(getColorFromPlayer(player)); 
		player.setPlayerListName(chatColor + player.getName());
		player.setDisplayName(chatColor + player.getName());
		System.out.println("Spielerfarbe geändert!");
		return true; 
	}
	
	public static EConstellations getColorFromString(String message) {
		try {
			return EConstellations.valueOf(message); 
		} catch(IllegalArgumentException ex) {
			return EConstellations.Nothing; 
		}
	}
	
	public static int getStatsFromColor(EConstellations color) {
		return constellationsStats.get(color);
	}
	
	/**
	 * Returns boolean => exceptions are missing!
	 * 
	 * @param color
	 * @param stats
	 * @return
	 */
	public static boolean addStatsToColor(EConstellations color, int stats) {
		constellationsStats.replace(color, constellationsStats.get(color) + stats); 
		return true; 
	}
	
	/**
	 * Returns boolean => exceptions are missing!
	 * 
	 * @param color
	 * @return
	 */
	public static boolean resetStatsFromColor(EConstellations color) {
		constellationsStats.replace(color, 0);
		return true; 
	}
	
	public static ChatColor getChatColorFromColor(EConstellations color) {
		ChatColor chatColor;
		switch(color) {
		case Red: 
			chatColor = ChatColor.RED; 
			break; 
		case Blue: 
			chatColor = ChatColor.BLUE; 
			break; 
		case Yellow: 
			chatColor = ChatColor.YELLOW; 
			break; 
		case Green: 
			chatColor = ChatColor.GREEN; 
			break;
		default:
			chatColor = ChatColor.RESET; 
			break; 
		}
		return chatColor; 
	}
	
	public static ChatColor getChatColorFromPlayer(Player player) {
		return getChatColorFromColor(getColorFromPlayer(player)); 
	}
}