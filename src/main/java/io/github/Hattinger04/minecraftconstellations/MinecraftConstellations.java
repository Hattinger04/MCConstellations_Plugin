/**
 * @author Simon Greiderer
 */
package io.github.Hattinger04.minecraftconstellations;


import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.Hattinger04.minecraftconstellations.commands.Commands;
import io.github.Hattinger04.minecraftconstellations.constellations.Constellations;
import io.github.Hattinger04.minecraftconstellations.events.PlayerDeath;
import io.github.Hattinger04.minecraftconstellations.events.PlayerHit;
import io.github.Hattinger04.minecraftconstellations.events.PlayerJoin;
import io.github.Hattinger04.minecraftconstellations.events.Soup;
import io.github.Hattinger04.minecraftconstellations.mysql.MySQL;
import io.github.Hattinger04.minecraftconstellations.time.FightingSchedule;

public final class MinecraftConstellations extends JavaPlugin {

	public static MySQL mysql;
	public static MinecraftConstellations plugin;

	public static MinecraftConstellations getplugin() {
		return MinecraftConstellations.plugin;
	}

	@Override
	public void onEnable() {
		getLogger().info("Server is now starting...");
//		MySQLFIle.setDefaultMySQL();
//        MySQLFIle.loadMySQLSettings();
//        if (MySQLFIle.sql.getBoolean("MySQL")) {
//            MySQLFIle.ConnectMySQL();
//        }
//		getLogger().info("Connection to database has been successful!");
		this.registerCommands();
		getLogger().info("Commands are successfully implemented!");
		this.registerEvents();
		getLogger().info("Events are successfully implemented!");
		Constellations.initializeConstellations();
		getLogger().info("Constellaltions are successfully implemented!");
		checkTask();

	}

	@Override
	public void onDisable() {
		getLogger().info("Server is now closing...");
	}

	private void registerCommands() {
		this.getCommand("help").setExecutor(new Commands(this));
		this.getCommand("website").setExecutor(new Commands(this));
		this.getCommand("constellation").setExecutor(new Commands(this));

	}

	private void registerEvents() {
		final PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents((Listener) new PlayerJoin(), (Plugin) this);
		//pm.registerEvents((Listener) new Soup(), (Plugin) this);
		pm.registerEvents((Listener) new PlayerDeath(), (Plugin) this);
		pm.registerEvents((Listener) new PlayerHit(), (Plugin) this);
	}

	/**
	 * Task is called every 30 seconds 
	 */
	public void checkTask() {
		FightingSchedule fightingSchedule = new FightingSchedule(); 
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				fightingSchedule.run(); 
				getLogger().info("Updating...");
			}
		}, 20L * 30, 20L * 30);
	}
}
