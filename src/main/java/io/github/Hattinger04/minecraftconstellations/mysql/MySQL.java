// 
// Decompiled by Procyon v0.5.36
// 

package io.github.Hattinger04.minecraftconstellations.mysql;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class MySQL
{
    private String HOST;
    private String DATABASE;
    private String USER;
    private String PASSWORD;
    private Connection con;
    
    public MySQL(final String host, final String database, final String user, final String password) {
    	this.HOST = host;
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;
        this.connect();
    }
    
    public void connect() {
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://" + this.HOST + ":3306/" + this.DATABASE + "?autoReconnect=true", this.USER, this.PASSWORD);
            System.out.println("[MySQL] Die Verbindung zur MySQL wurde hergestellt!");
        }
        catch (SQLException e) {
            System.out.println("[MySQL] Die Verbindung zur MySQL ist fehlgeschlagen! Fehler: " + e.getMessage());
        }
    }
    
    public void close() {
        try {
            if (this.con != null) {
                this.con.close();
                System.out.println("[MySQL] Die Verbindung zur MySQL wurde Erfolgreich beendet!");
            }
        }
        catch (SQLException e) {
            System.out.println("[MySQL] Fehler beim beenden der Verbindung zur MySQL! Fehler: " + e.getMessage());
        }
    }
    
    public void update(final String qry) {
        try {
            final Statement st = this.con.createStatement();
            st.executeUpdate(qry);
            st.close();
        }
        catch (SQLException e) {
            this.connect();
            System.err.println(e);
        }
    }
    
    public ResultSet query(final String qry) {
        ResultSet rs = null;
        try {
            final Statement st = this.con.createStatement();
            rs = st.executeQuery(qry);
        }
        catch (SQLException e) {
            this.connect();
            System.err.println(e);
        }
        return rs;
    }
}
