import java.sql.*;

public class WriteData {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "abstract-programmer";
        String password = "example-password";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);


            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from student");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3));
            }

            for (int i = 1; i <= 1000000; ++i) {

                PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO student (id, name, age) VALUES (?, ?, ?)");

                //inserting data
                insertStatement.setInt(1, i);
                insertStatement.setString(2, "William");
                insertStatement.setInt(3, 35);

                int rowsAffected = insertStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) inserted.");
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
