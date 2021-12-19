package io.github.Hattinger04.minecraftconstellations;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.Hattinger04.minecraftconstellations.commands.Commands;
import io.github.Hattinger04.minecraftconstellations.mysql.MySQL;
import io.github.Hattinger04.minecraftconstellations.mysql.MySQLFIle;

public final class MinecraftConstellations extends JavaPlugin{

    public static MySQL mysql;
    public static MinecraftConstellations plugin;

    public static MinecraftConstellations getplugin() {
        return MinecraftConstellations.plugin;
    }
	
    @Override
    public void onEnable() {
		getLogger().info("Server is now starting...");
		this.registerCommands();
		getLogger().info("Commands are successfully implemented!");
		MySQLFIle.setDefaultMySQL();
        MySQLFIle.loadMySQLSettings();
        if (MySQLFIle.sql.getBoolean("MySQL")) {
            MySQLFIle.ConnectMySQL();
        }
		getLogger().info("Connection to database has been successful!");

    }
    @Override
    public void onDisable() {
		getLogger().info("Server is now closing...");
    }
	private void registerCommands() {
    	this.getCommand("help").setExecutor(new Commands(this));
    }
}
