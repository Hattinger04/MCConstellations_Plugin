// 
// Decompiled by Procyon v0.5.36
// 

package io.github.Hattinger04.minecraftconstellations.mysql;

import java.sql.SQLException;
import java.sql.ResultSet;
import io.github.Hattinger04.minecraftconstellations.MinecraftConstellations;

import org.bukkit.inventory.Inventory;
import java.util.HashMap;

public class MySQLStats
{
    static HashMap<Integer, String> rang;
    public static Inventory playerTop;
    
    static {
        MySQLStats.rang = new HashMap<Integer, String>();
    }
    
    
    public static boolean playerExists(final String uuid) {
        if (MySQLFIle.sql.getBoolean("MySQL")) {
            try {
                final ResultSet rs = MinecraftConstellations.mysql.query("SELECT * FROM SkyWarsStats WHERE UUID= '" + uuid + "'");
                return rs.next() && rs.getString("UUID") != null;
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public static void createPlayer(final String uuid) {
        if (MySQLFIle.sql.getBoolean("MySQL") && !playerExists(uuid)) {
        	MinecraftConstellations.mysql.update("INSERT INTO SkyWarsStats(UUID, KILLS, DEATHS, WIN, PLAY, COINS) VALUES ('" + uuid + "', '0', '0', '0', '0', '0');");
        }
    }
    
    public static Integer getKills(final String name) {
        Integer i = 0;
        if (MySQLFIle.sql.getBoolean("MySQL")) {
            if (playerExists(name)) {
                try {
                    final ResultSet rs = MinecraftConstellations.mysql.query("SELECT * FROM SkyWarsStats WHERE UUID= '" + name + "'");
                    if (!rs.next() || Integer.valueOf(rs.getInt("KILLS")) == null) {}
                    i = rs.getInt("KILLS");
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                createPlayer(name);
                getKills(name);
            }
        }
        return i;
    }
    
    public static void setKills(final String name, final Integer kills) {
        if (MySQLFIle.sql.getBoolean("MySQL")) {
            if (playerExists(name)) {
            	MinecraftConstellations.mysql.update("UPDATE SkyWarsStats SET KILLS= '" + kills + "' WHERE UUID= '" + name + "';");
            }
            else {
                createPlayer(name);
                setKills(name, kills);
            }
        }
    }
    
    public static void addKills(final String name, final Integer kills) {
        if (MySQLFIle.sql.getBoolean("MySQL")) {
            if (playerExists(name)) {
                setKills(name, getKills(name) + kills);
            }
            else {
                createPlayer(name);
                addKills(name, kills);
            }
        }
    }
    
    public static Integer getDeaths(final String name) {
        Integer i = 0;
        if (MySQLFIle.sql.getBoolean("MySQL")) {
            if (playerExists(name)) {
                try {
                    final ResultSet rs = MinecraftConstellations.mysql.query("SELECT * FROM SkyWarsStats WHERE UUID= '" + name + "'");
                    if (!rs.next() || Integer.valueOf(rs.getInt("DEATHS")) == null) {}
                    i = rs.getInt("DEATHS");
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                createPlayer(name);
                getDeaths(name);
            }
        }
        return i;
    }
    
    public static void setDeaths(final String name, final Integer deaths) {
        if (MySQLFIle.sql.getBoolean("MySQL")) {
            if (playerExists(name)) {
                MinecraftConstellations.mysql.update("UPDATE SkyWarsStats SET DEATHS= '" + deaths + "' WHERE UUID= '" + name + "';");
            }
            else {
                createPlayer(name);
                setDeaths(name, deaths);
            }
        }
    }
    
    public static void addDeaths(final String name, final Integer deaths) {
        if (MySQLFIle.sql.getBoolean("MySQL")) {
            if (playerExists(name)) {
                setDeaths(name, getDeaths(name) + deaths);
            }
            else {
                createPlayer(name);
                addDeaths(name, deaths);
            }
        }
    }
    
   
}
