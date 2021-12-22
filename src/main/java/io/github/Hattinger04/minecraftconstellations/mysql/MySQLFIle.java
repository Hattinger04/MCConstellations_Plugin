// 
// Decompiled by Procyon v0.5.36
// 

package io.github.Hattinger04.minecraftconstellations.mysql;

import org.bukkit.plugin.Plugin;

import io.github.Hattinger04.minecraftconstellations.MinecraftConstellations;

import org.bukkit.Bukkit;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;

public class MySQLFIle {
	public static String Host;
	public static String Database;
	public static String User;
	public static String Passwort;
	public static File file;
	public static FileConfiguration sql;

	static {
		MySQLFIle.Host = "localhost";
		MySQLFIle.Database = "constellations";
		MySQLFIle.User = "root";
		MySQLFIle.Passwort = "";
		MySQLFIle.file = new File("plugins/SkyWars", "MySQL.yml");
		MySQLFIle.sql = (FileConfiguration) YamlConfiguration.loadConfiguration(MySQLFIle.file);
	}

	public static void setDefaultMySQL() {
		MySQLFIle.sql.addDefault("MySQL", (Object) true);
		MySQLFIle.sql.addDefault("Host", (String) "localhost");
		MySQLFIle.sql.addDefault("Database", (String) "constellations");
		MySQLFIle.sql.addDefault("User", (String) "root");
		MySQLFIle.sql.addDefault("Passwort", (String) "");
		MySQLFIle.sql.options().copyDefaults(true);
		try {
			MySQLFIle.sql.save(MySQLFIle.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadMySQLSettings() {
		MySQLFIle.Host = MySQLFIle.sql.getString("Host");
		MySQLFIle.Database = MySQLFIle.sql.getString("Database");
		MySQLFIle.User = MySQLFIle.sql.getString("User");
		MySQLFIle.Passwort = MySQLFIle.sql.getString("Passwort");
	}

	public static void ConnectMySQL() {
		(MinecraftConstellations.mysql = new MySQL(MySQLFIle.Host, MySQLFIle.Database, MySQLFIle.User, MySQLFIle.Passwort)).update(
				"CREATE TABLE IF NOT EXISTS ConstellationStats(UUID varchar(64), KILLS int, DEATHS int);");
		Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) MinecraftConstellations.getplugin(), (Runnable) new Runnable() {
			@Override
			public void run() {
				MinecraftConstellations.mysql.update(
						"CREATE TABLE IF NOT EXISTS ConstellationStats(UUID varchar(64), KILLS int, DEATHS int);");
			}
		}, 0L, 216000L);
	}
}
