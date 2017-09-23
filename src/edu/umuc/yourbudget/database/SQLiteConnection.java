package edu.umuc.yourbudget.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Christian on 9/22/17.
 */
public class SQLiteConnection {

    public static Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:YourBudget.sqlite");
            return conn;
        } catch (Exception e) {
            System.out.println("Unable to connect to database.");
            System.out.println(e);
            return null;
        }
    }

}