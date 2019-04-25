package AgendaContacte.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton pentru conexiunea cu baza de date pentru proiect.
 *
 * @author AndreiR
 */
public class MySQLDatabaseConnection {

    private Connection con;
    private static MySQLDatabaseConnection dbConnect;

    public static MySQLDatabaseConnection getDbConnect() {
        if (dbConnect == null) {
            dbConnect = new MySQLDatabaseConnection();
        }
        return dbConnect;
    }

    public Connection getConnection() {
        return con;
    }

    private MySQLDatabaseConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiectfinaldb", "root", "root");
            System.out.println("Connection successful!");

        } catch (ClassNotFoundException ex) {
            System.out.println("Can't connect");
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Cant'connect");
            System.out.println(ex.getMessage());
        }
    }
}
