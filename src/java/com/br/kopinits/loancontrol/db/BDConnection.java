package com.br.kopinits.loancontrol.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BDConnection {

    private static java.sql.Connection con = null;
    private static String url;
    private static String serverName;
    private static String portNumber;
    private static String databaseName;
    private static String userName;
    private static String password;
    private static String connectionString;
    public static boolean postgress = true;

    private static String getConnectionUrl() {
        if (!postgress)
            return url + "@" + serverName + ":" + portNumber + ":" + databaseName;
        else
            return url + serverName + ":" + portNumber + "/" + databaseName;
    }

    public static Connection getConnection() {
        try {
            readProperties();
            Class.forName(connectionString);
            con = java.sql.DriverManager.getConnection(getConnectionUrl(), userName, password);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(CLASSE_NAME).log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            Logger.getLogger(CLASSE_NAME).log(Level.SEVERE, e.getMessage());
        }
        return con;
    }
    public static final String CLASSE_NAME = "org.br.assdjk.db.BDConnection";

    public String displayDbProperties() {
        DatabaseMetaData dm = null;
        ResultSet rs = null;
        StringBuffer buffer = new StringBuffer();
        try {
            con = getConnection();

            if (con != null) {
                dm = con.getMetaData();
                System.out.println("Driver Information");
                buffer.append("Driver Information");
                System.out.println("\tDriver Name: " + dm.getDriverName());
                buffer.append("<br>Driver Name: " + dm.getDriverName());
                System.out.println("\tDriver Version: " + dm.getDriverVersion());
                buffer.append("<br>Driver Version: " + dm.getDriverVersion());
                System.out.println("\nDatabase Information ");
                buffer.append("<br>Database Information ");
                System.out.println("\tDatabase Name: " + dm.getDatabaseProductName());
                buffer.append("<br>Database Name: " + dm.getDatabaseProductName());
                System.out.println("\tDatabase Version: " + dm.getDatabaseProductVersion());
                buffer.append("<br>Database Version: " + dm.getDatabaseProductVersion());
                System.out.println("Avalilable Catalogs ");
                buffer.append("<br>Avalilable Catalogs ");
                rs = dm.getCatalogs();

                while (rs.next()) {
                    System.out.println("\tcatalog: " + rs.getString(1));
                    buffer.append("<br>catalog: " + rs.getString(1));
                }
                rs.close();
                rs = null;
                closeConnection();
            } else {
                System.out.println("Error: No active Connection");
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
        }
        dm = null;
        return buffer.toString();
    }

    /**
     * -
     */
    public static void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
            con = null;
        } catch (SQLException e) {
            Logger.getLogger(CLASSE_NAME).log(Level.SEVERE, e.getMessage());
        }
    }

    public static void rollback() {
        try {
            if (con != null && !con.isClosed()) {
                con.rollback();
                closeConnection();
            }
        } catch (SQLException e) {
            Logger.getLogger(CLASSE_NAME).log(Level.SEVERE, e.getMessage());
        }
    }

    public static void readProperties() {
        Properties properties = new Properties();
        try {
            InputStream in = new BDConnection().getClass().getResourceAsStream("configuracao/database.properties");
            properties.load(in);
            url = properties.getProperty("url");
            serverName = properties.getProperty("serverName");
            portNumber = properties.getProperty("portNumber");
            databaseName = properties.getProperty("databaseName");
            userName = properties.getProperty("userName");
            password = properties.getProperty("password");
            connectionString = properties.getProperty("connectionString");
            String tipobanco = properties.getProperty("tipobanco");
            if (tipobanco.equals("ORACLE"))
                postgress = false;
            else
                postgress = true;
        } catch (IOException e) {
            Logger.getLogger(CLASSE_NAME).log(Level.SEVERE, e.getMessage());
        }
    }
}
