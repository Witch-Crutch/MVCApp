package ru.itis.witchCrutch.servlets;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import lombok.SneakyThrows;
import ru.itis.witchCrutch.util.ConfigParser;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.util.Map;

@WebListener
public class ContextListener implements ServletContextListener {

    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        //    DB

        HikariConfig hikariConfig = new HikariConfig();
        Map<String, String> configDB = ConfigParser.parseDBConfig();

        hikariConfig.setJdbcUrl(configDB.get("URL"));
        hikariConfig.setDriverClassName(configDB.get("DRIVER"));
        hikariConfig.setUsername(configDB.get("USERNAME"));
        hikariConfig.setPassword(configDB.get("PASS"));
        hikariConfig.setMaximumPoolSize(10);

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        sce.getServletContext().setAttribute("datasource", dataSource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("datasource", null);
    }

}
