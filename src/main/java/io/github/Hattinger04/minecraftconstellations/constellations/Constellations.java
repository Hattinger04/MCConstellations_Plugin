package io.github.Hattinger04.minecraftconstellations.constellations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Constellations {

	/*
	 * Nothing tested!
	 */
	
	private static HashMap<EConstellations, ArrayList<Player>> constellations; 
	
	public static void initializeConstellations() {
		constellations = loadConstellations(); 
	}
	
	public static HashMap<EConstellations, ArrayList<Player>> loadConstellations() {
		 HashMap<EConstellations, ArrayList<Player>> constellation = new HashMap<EConstellations, ArrayList<Player>>();
		 for(EConstellations color : EConstellations.values()) {
			 constellation.put(color, new ArrayList<Player>()); 
		 }
		 // MySQL DB Zugriff => erstellen der HashMap
		 
		 // Changing players name color: 

		 return constellation; 
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
		setPlayerColor(player); 
		
		// Bissale a pfusch denk i aber so funkts
		for(EConstellations cons : EConstellations.values()) {
			if(cons != color) {
				if(constellations.get(cons).contains(player)) {
					constellations.get(cons).remove(player); 
				}
			}
		}

		for (Entry<EConstellations, ArrayList<Player>> entry : constellations.entrySet()) {
			System.out.println("Color: " + entry.getKey().toString());
			for(Player p : entry.getValue()) {
	    		System.out.println("\tPlayer: " + p.getDisplayName());
	    	}		
		}
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
	 * Returns boolean => exceptions are missing!
	 * 
	 * @param player
	 * @return
	 */
	public static boolean setPlayerColor(Player player) {
		EConstellations color = getColorFromPlayer(player); 
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
			chatColor = ChatColor.BLACK; 
			break; 
		}
		player.setDisplayName(chatColor + player.getName() + ChatColor.RESET);
		return true; 
	}
	
	public static EConstellations getColorFromString(String message) {
		try {
			return EConstellations.valueOf(message); 
		} catch(IllegalArgumentException ex) {
			return EConstellations.Nothing; 
		}
	}
}