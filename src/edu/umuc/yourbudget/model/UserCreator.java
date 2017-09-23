package edu.umuc.yourbudget.model;

import edu.umuc.yourbudget.database.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Christian on 9/22/17.
 */
public class UserCreator {

    private Connection connection;

    public UserCreator() {
        connection = SQLiteConnection.connect();
        if (connection == null) {
            System.out.println("Unable to connect to database.");
        }
    }

    /**
     * Creates a new user in the 'user' table of the database. Returns true if
     * successful, false if unsuccessful.
     * @param firstName
     * @param username
     * @param password
     * @return true if successful, false if unsuccessful
     */
    public boolean createUser(String firstName, String username, String password) {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO user(first_name, username, password) VALUES(?, ?, ?)";
        boolean isSuccessful = false;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.execute();
            System.out.println("User created successfully.");
            isSuccessful = true;
        } catch (Exception e) {
            System.out.println("Unable to insert new user.");
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return isSuccessful;
        }
    }

}
