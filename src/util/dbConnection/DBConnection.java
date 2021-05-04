package util.dbConnection;

import util.utility.AlertPopUp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;

    /*
     * Singleton design pattern is implemented to avoid creation of multiple instances of DBConnetion
     */
    private DBConnection() {
    }

    public static Connection getDBConnection() {

        if (conn == null) {
            DBConnection.setConnection();
        }
        return conn;
    }

    private static void setConnection() {
        /*
         * local mysql database credentials
         */
        String url = "jdbc:mysql://localhost/";
        String dbname = "salesbuddy";
        String ssl = "?useSSL=false";
        String username = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url + dbname + ssl, username, password);
        } catch (SQLException | ClassNotFoundException exception) {
            AlertPopUp.connectionError(exception);
        }

    }
}
