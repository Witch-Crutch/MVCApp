package ru.itis.witchCrutch.test;

import ru.itis.witchCrutch.jdbc.SimpleDataSource;
import ru.itis.witchCrutch.jdbc.repositories.UsersRepositoryJdbcImpl;
import ru.itis.witchCrutch.util.ConfigParser;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;

public class TestJDBS {
    public static void main(String[] args) throws SQLException {
        InitialContext cxt = null;
        try {
            cxt = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        DataSource ds = null;
        try {
            ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/postgres" );
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
