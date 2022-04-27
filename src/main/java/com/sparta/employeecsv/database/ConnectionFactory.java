package com.sparta.employeecsv.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static Connection connection = null;

    public static Connection getConnection()    {

        if (connection == null) {

            try {
                Properties dbProps = new Properties();
                //load the sql database properties
                dbProps.load(new FileReader("sql.properties"));

                //initialise connection to the sql database using properties file
                connection = DriverManager.getConnection(
                        dbProps.getProperty("db.url"),
                        dbProps.getProperty("db.username"),
                        dbProps.getProperty("db.password"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return connection;
    }

    public static void closeConnection()    {
        if (connection != null) {
            try {
                //close the connection to the database
                connection.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connection = null;
    }
}
