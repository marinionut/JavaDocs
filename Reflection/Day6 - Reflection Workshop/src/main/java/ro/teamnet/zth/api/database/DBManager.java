package ro.teamnet.zth.api.database;

import ro.teamnet.zth.api.database.DProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Ionutz on 29.04.2015.
 */
public class DBManager {
    private DBManager() {
        throw new UnsupportedOperationException();
    }

    final static String CONNECTION_STRING= "jdbc:mysql://" + DProperties.IP + "/" + DProperties.SCHEMA;

    private static void registerDriver() {
        try {
            Class.forName(DProperties.DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection myConnection = null;
        registerDriver();
        try {
            myConnection = DriverManager.getConnection(CONNECTION_STRING, DProperties.USER, DProperties.PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            myConnection = null;
        }
        return myConnection;
    }

    public static boolean checkConnection(Connection connection) {
        try {
            connection.createStatement().execute("SELECT 1 FROM dual;");
            return true;
        } catch(SQLException e) {
            System.out.print("Error");
            e.printStackTrace();
            return false;
        }
    }

}
