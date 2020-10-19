package ru.itis.witchCrutch.controllers.listeners;

import ru.itis.witchCrutch.dataSource.SimpleDataSource;
import ru.itis.witchCrutch.dataSource.SimpleDataSourceConfig;
import ru.itis.witchCrutch.repositories.*;
import ru.itis.witchCrutch.services.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        //    DB

        Properties properties = new Properties();
        try {
            properties.load(sce.getServletContext().getResourceAsStream("/WEB-INF/properties/db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        SimpleDataSourceConfig simpleConfig = new SimpleDataSourceConfig();

        simpleConfig.setJdbcUrl(properties.getProperty("db.jdbc.url"));
        simpleConfig.setDriverClassName(properties.getProperty("db.jdbc.driver-class-name"));
        simpleConfig.setUsername(properties.getProperty("db.jdbc.username"));
        simpleConfig.setPassword(properties.getProperty("db.jdbc.password"));

        //HikariDataSource dataSource = new HikariDataSource(simpleConfig);

        // Repository

        SimpleDataSource dataSource = new SimpleDataSource(simpleConfig);


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
