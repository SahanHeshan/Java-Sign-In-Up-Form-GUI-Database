import java.net.URL;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    // email valid pattern checker
    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // email duplication checker
    public static boolean isDuplicateEmail(String email) {
        boolean isDuplicate = false;
        String selectQuery = "SELECT COUNT(*) FROM `user` WHERE `Email` = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
             preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    isDuplicate = count > 0;
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return isDuplicate;
    }
}
