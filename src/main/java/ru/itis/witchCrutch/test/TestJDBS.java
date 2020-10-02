package ru.itis.witchCrutch.test;

import ru.itis.witchCrutch.jdbc.SimpleDataSource;
import ru.itis.witchCrutch.jdbc.repositories.UsersRepositoryJdbcImpl;
import ru.itis.witchCrutch.util.ConfigParser;

import java.sql.*;
import java.util.Map;

public class TestJDBS {
    public static void main(String[] args) throws SQLException {
        SimpleDataSource dataSource = new SimpleDataSource();
        Map<String, String> configDB = ConfigParser.parseDBConfig();
        Connection connection = dataSource.openConnection(
                configDB.get("URL"), configDB.get("USERNAME"), configDB.get("PASS")
        );

        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(connection);
        System.out.println(usersRepositoryJdbc.findAll());


        dataSource.closeConnection(connection);
    }
}
