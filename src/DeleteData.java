import java.sql.*;

public class DeleteData {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "abstract-programmer";
        String password = "example-password";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            // Reading data
            Statement readStatement = connection.createStatement();
            ResultSet resultSet = readStatement.executeQuery("SELECT * FROM student");

            System.out.println("Before deletion:");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3));
            }

            // Deleting data
            int idToDelete = 0;
            PreparedStatement deleteStatement = connection.prepareStatement( "DELETE FROM student WHERE id = ?");


            deleteStatement.setInt(1, idToDelete);

            int rowsAffected = deleteStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted.");

            ResultSet afterDeleteResultSet = readStatement.executeQuery("SELECT * FROM student");

            System.out.println("After deletion:");
            while (afterDeleteResultSet.next()) {
                System.out.println(afterDeleteResultSet.getInt(1) + " " + afterDeleteResultSet.getString(2) + " " + afterDeleteResultSet.getInt(3));
            }

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
