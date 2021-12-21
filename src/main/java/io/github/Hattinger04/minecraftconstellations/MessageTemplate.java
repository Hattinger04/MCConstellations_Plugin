package io.github.Hattinger04.minecraftconstellations;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import io.github.Hattinger04.minecraftconstellations.constellations.Constellations;
import io.github.Hattinger04.minecraftconstellations.constellations.EConstellations;
import net.md_5.bungee.api.ChatColor;

public class MessageTemplate {

	public static void sendMessageToPlayer(String message, Player player, ChatColor color) {
		player.sendMessage(color + message + ChatColor.RESET);
	}
	
	public static void sendMessageToPlayer(String message, Player player) {
		sendMessageToPlayer(message, player, ChatColor.RESET);
	}
	
	public static void sendMessageToConstellation(String message, EConstellations constellation) {
		for(Player player : Constellations.getPlayersFromColor(constellation)) {
			player.sendMessage(Constellations.getChatColorFromColor(constellation) + message + ChatColor.RESET);
		}
	}
	
	public static void sendMessageToAllPlayer(String message, ChatColor color) {
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			player.sendMessage(color + message + ChatColor.RESET);
		}
	}
	
	public static void sendMessageToAllPlayer(String message) {
		sendMessageToAllPlayer(message, ChatColor.RESET);
	}
}
