import java.sql.*;
public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/signedUsers"; // mysql database
    private static final String USERNAME = "root";// mysql user
    private static final String PASSWORD = "1234";// mysql user password

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

