package ru.itis.witchCrutch.controllers.servlet;

import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.services.interfaces.BasketService;
import ru.itis.witchCrutch.services.interfaces.ProductService;
import ru.itis.witchCrutch.services.interfaces.PurchaseService;
import ru.itis.witchCrutch.services.interfaces.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");

        ProductService productService = (ProductService) req.getServletContext().getAttribute("productService");

        UsersService usersService = (UsersService) req.getServletContext().getAttribute("userService");

        BasketService basketService = (BasketService) req.getServletContext().getAttribute("basketService");

        PurchaseService purchaseService = (PurchaseService) req.getServletContext().getAttribute("purchaseService");

        User user = (User) req.getSession().getAttribute("user");
        List<Product> purchases = purchaseService.getUserPurchase(user);

        if (purchaseService.getUserPurchase(user) != null)
            req.setAttribute("purchase", purchases);

        req.getRequestDispatcher("/profile.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
