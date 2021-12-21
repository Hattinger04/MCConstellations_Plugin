/**
 * 
 * Nothing tested, unlikely to work! 
 * 
 * @author Simon Greiderer
 */

package io.github.Hattinger04.minecraftconstellations.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import io.github.Hattinger04.minecraftconstellations.MessageTemplate;
import io.github.Hattinger04.minecraftconstellations.MinecraftConstellations;
import net.md_5.bungee.api.ChatColor;

public class FightingSchedule {

	private String start = "19:00";
	private String end = "22:00";
	private Calendar startTime, endTime; 	
	private static boolean isFightingEnabled = false; 

	public FightingSchedule() {}

	public void setFightingTime() throws ParseException {
	    Date time1 = new SimpleDateFormat("HH:MM").parse(start);
	    startTime = Calendar.getInstance();
	    startTime.setTime(time1);
	    startTime.add(Calendar.DATE, 1);

	    Date time2 = new SimpleDateFormat("HH:MM").parse(end);
	    endTime = Calendar.getInstance();
	    endTime.setTime(time2);
	    endTime.add(Calendar.DATE, 1);

	}
	
	/**
	 * Vielleicht noch eine abfrage ob man sich im fight befindet
	 */
	public void run() {
		try {
			setFightingTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date x = Calendar.getInstance().getTime();
		if(x.after(startTime.getTime()) && x.before(endTime.getTime()) && !isFightingEnabled) {
			for(Player player : Bukkit.getServer().getOnlinePlayers()) {
				MessageTemplate.sendMessageToPlayer("Fighting is now enabled!", player, ChatColor.GREEN);
			}
			isFightingEnabled = !isFightingEnabled; 
		} else if(x.after(startTime.getTime()) && isFightingEnabled) {
			for(Player player : Bukkit.getServer().getOnlinePlayers()) {
				MessageTemplate.sendMessageToPlayer("Fighting is now disabled!", player, ChatColor.GREEN);
			}
			isFightingEnabled = !isFightingEnabled;
		}
	}
	
	public static boolean isFightingEnabled() {
		return isFightingEnabled;
	}
}
