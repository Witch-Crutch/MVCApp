package ru.itis.witchCrutch.servlets.servlet;

import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.repositories.*;
import ru.itis.witchCrutch.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);

        BasketRepository basketRepository = new BasketRepositoryJdbcImpl(dataSource, usersService);
        BasketService basketService = new BasketServiceImpl(basketRepository);

        ProductRepository productRepository = new ProductRepositoryJdbcImpl(dataSource);
        ProductService productService = new ProductServiceImpl(productRepository);

        PurchaseRepository purchaseRepository = new PurchaseRepositoryJdbcImpl(dataSource, basketService, productService);
        PurchaseService purchaseService = new PurchaseServiceImpl(purchaseRepository);

        User user = (User) req.getServletContext().getAttribute("user");
        if (purchaseRepository.userPurchase(user) != null)
            req.setAttribute("purchase", purchaseRepository.userPurchase(user));
        req.getRequestDispatcher("/profile.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
