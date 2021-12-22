/**
 * @author Simon Greiderer
 */

package io.github.Hattinger04.minecraftconstellations.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import io.github.Hattinger04.minecraftconstellations.MessageTemplate;
import net.md_5.bungee.api.ChatColor;

public class FightingSchedule {

	private int hour, minute; 
	Date date;
	Date dateCompareOne = parseDate("13:00");
	Date dateCompareTwo = parseDate("16:05");

	private static boolean isFightingEnabled = false;

	public FightingSchedule() {
		setFightingTime();
	}

	public void setFightingTime() {
		 Calendar now = Calendar.getInstance();
		 hour = now.get(Calendar.HOUR_OF_DAY); 
		 minute = now.get(Calendar.MINUTE);
		 date = parseDate(hour + ":" + minute); 
	}

	private Date parseDate(String date) {
		final String inputFormat = "HH:mm";
		SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.GERMANY);
		try {
			return inputParser.parse(date);
		} catch (java.text.ParseException e) {
			return new Date(0);
		}
	}

	/**
	 * Vielleicht noch eine abfrage ob man sich im fight befindet
	 */
	public void run() {
		setFightingTime();
		if (dateCompareOne.before(date) && dateCompareTwo.after(date) && !isFightingEnabled) {
			for (Player player : Bukkit.getServer().getOnlinePlayers()) {
				MessageTemplate.sendMessageToPlayer("Fighting is now enabled!", player, ChatColor.GREEN);
			}
			isFightingEnabled = !isFightingEnabled;
		} else if (dateCompareOne.after(date) && dateCompareTwo.after(date) && isFightingEnabled) {
			for (Player player : Bukkit.getServer().getOnlinePlayers()) {
				MessageTemplate.sendMessageToPlayer("Fighting is now disabled!", player, ChatColor.GREEN);
			}
			isFightingEnabled = !isFightingEnabled;
		} else if (dateCompareOne.before(date) && dateCompareTwo.before(date) && isFightingEnabled) {
			for (Player player : Bukkit.getServer().getOnlinePlayers()) {
				MessageTemplate.sendMessageToPlayer("Fighting is now disabled!", player, ChatColor.GREEN);
			}
			isFightingEnabled = !isFightingEnabled;
		}
	}

	public static boolean isFightingEnabled() {
		return isFightingEnabled;
	}
}