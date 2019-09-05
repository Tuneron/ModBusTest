package com.company;

import java.sql.*;

public class DatabaseControll {

    public void WriteToSchema(int temperature)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("test1");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/firsttestschema?user=root&password=1234");
            if (connection != null) {
                Statement statement = connection.createStatement();
                statement.execute(String.format("INSERT INTO temperaturevalue (temperature) VALUES (%s);", temperature));
            }
            else
            {
                System.out.println("ERROR NO CONNECTION");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
