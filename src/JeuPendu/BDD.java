package JeuPendu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDD {
    private static Connection cnx = null;

    static {
        try {
            cnx = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/uby_pendu?serverTimezone=UTC",
                    "root",
                    "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getCnx() {
        return cnx;
    }
}