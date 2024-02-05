import java.sql.*;
public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/signedUsers"; //sample database
    private static final String USERNAME = "root";//sample database
    private static final String PASSWORD = "mysql@123";//sample database

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}

