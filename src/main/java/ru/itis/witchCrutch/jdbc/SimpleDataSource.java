package ru.itis.witchCrutch.jdbc;

import ru.itis.witchCrutch.util.ConfigParser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class SimpleDataSource {

    public Connection createConnection(){
        Map<String, String> configDB = ConfigParser.parseDBConfig();
        return openConnection(configDB.get("URL"), configDB.get("USERNAME"), configDB.get("PASS"));
    }

    public Connection openConnection(String url, String username, String pass) {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
        try {
            return DriverManager.getConnection(url, username, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }
}
