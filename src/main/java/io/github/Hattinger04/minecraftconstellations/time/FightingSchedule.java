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

public class FightingSchedule {

	private String start = "19:00";
	private String end = "22:00";
	private Calendar startTime, endTime; 	
	private boolean isFightingEnabled = false; 

	public FightingSchedule() {}

	public void setFightingTime() throws ParseException {
	    Date time1 = new SimpleDateFormat("HH:mm").parse(start);
	    startTime = Calendar.getInstance();
	    startTime.setTime(time1);
	    startTime.add(Calendar.DATE, 1);

	    Date time2 = new SimpleDateFormat("HH:mm").parse(end);
	    endTime = Calendar.getInstance();
	    endTime.setTime(time2);
	    endTime.add(Calendar.DATE, 1);
	}
	
	/**
	 * Vielleicht noch eine abfrage ob man sich im fight befindet
	 */
	public void run() {
		Date x = Calendar.getInstance().getTime();

		if(x.after(startTime.getTime()) && x.before(startTime.getTime()) && !isFightingEnabled) {
			for(Player player : Bukkit.getServer().getOnlinePlayers()) {
				player.sendMessage("Fighting is now enabled!");
			}
			isFightingEnabled = !isFightingEnabled; 
		} else if(x.after(startTime.getTime()) && isFightingEnabled) {
			for(Player player : Bukkit.getServer().getOnlinePlayers()) {
				player.sendMessage("Fighting is now disabled!");
			}
			isFightingEnabled = !isFightingEnabled;
		}
	}
}
