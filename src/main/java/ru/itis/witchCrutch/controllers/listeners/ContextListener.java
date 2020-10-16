package ru.itis.witchCrutch.controllers.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.witchCrutch.repositories.*;
import ru.itis.witchCrutch.services.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        //    DB

        Properties properties = new Properties();
        //TODO: Заменить на относительный
        try {
            properties.load(new FileInputStream("C:\\Users\\User\\Desktop\\Project\\itis\\third_semester\\semedb.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(properties.getProperty("db.jdbc.url"));
        hikariConfig.setDriverClassName(properties.getProperty("db.jdbc.driver-class-name"));
        hikariConfig.setUsername(properties.getProperty("db.jdbc.username"));
        hikariConfig.setPassword(properties.getProperty("db.jdbc.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.jdbc.hikari.max-pool-size")));

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        // Repository

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);

        ProductRepository productRepository = new ProductRepositoryJdbcImpl(dataSource);
        ProductService productService = new ProductServiceImpl(productRepository);

        BasketRepository basketRepository = new BasketRepositoryJdbcImpl(dataSource, usersService);
        BasketService basketService = new BasketServiceImpl(basketRepository);

        MessageRepository messageRepository = new MessageRepositoryJdbcImpl(dataSource, usersService);
        MessageService messageService = new MessageServiceImpl(messageRepository);

        PurchaseRepository purchaseRepository = new PurchaseRepositoryJdbcImpl(dataSource, basketService, productService);
        PurchaseService purchaseService = new PurchaseServiceImpl(purchaseRepository);

        TelephoneRepository telephoneRepository = new TelephoneRepositoryJdbcImpl(dataSource);
        TelephoneService telephoneService = new TelephoneServiceImpl(telephoneRepository);

        sce.getServletContext().setAttribute("productService", productService);
        sce.getServletContext().setAttribute("basketService", basketService);
        sce.getServletContext().setAttribute("messageService", messageService);
        sce.getServletContext().setAttribute("purchaseService", purchaseService);
        sce.getServletContext().setAttribute("telephoneService", telephoneService);
        sce.getServletContext().setAttribute("userService", usersService);
        sce.getServletContext().setAttribute("datasource", dataSource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("datasource", null);
    }

}
