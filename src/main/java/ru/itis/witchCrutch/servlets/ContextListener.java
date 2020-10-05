package ru.itis.witchCrutch.servlets;

import ru.itis.witchCrutch.jdbc.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;


public class ContextListener implements ServletContextListener {

    private AtomicReference<UsersRepositoryJdbcImpl> userRepository;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        userRepository = new AtomicReference<>(new UsersRepositoryJdbcImpl());
        sce.getServletContext().setAttribute("userRepository", userRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        userRepository.get().close();
        sce.getServletContext().setAttribute("userRepository", null);
    }
}
