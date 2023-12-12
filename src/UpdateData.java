import java.sql.*;

public class UpdateData {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "abstract-programmer";
        String password = "example-password";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);


            Statement readStatement = connection.createStatement();
            ResultSet resultSet = readStatement.executeQuery("SELECT * FROM student");

            System.out.println("Before update:");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3));
            }

            int idToUpdate = 4;
            String newName = "UpdatedName";
            int newAge = 30;

            PreparedStatement updateStatement = connection.prepareStatement("UPDATE student SET name = ?, age = ? WHERE id = ?");

            //updating data
            updateStatement.setInt(3, 4);
            updateStatement.setString(1, "Jones");
            updateStatement.setInt(2, 45);


            int rowsAffected = updateStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");


            ResultSet afterUpdateResultSet = readStatement.executeQuery("SELECT * FROM student");

            System.out.println("After update:");
            while (afterUpdateResultSet.next()) {
                System.out.println(afterUpdateResultSet.getInt(1) + " " + afterUpdateResultSet.getString(2) + " " + afterUpdateResultSet.getInt(3));
            }

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
