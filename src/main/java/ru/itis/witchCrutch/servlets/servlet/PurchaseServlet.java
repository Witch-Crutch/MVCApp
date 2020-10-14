package ru.itis.witchCrutch.servlets.servlet;

import ru.itis.witchCrutch.models.Basket;
import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.models.Purchase;
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
import java.util.Date;
import java.util.List;

@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");

        User user = (User) req.getServletContext().getAttribute("user");

        PurchaseRepository purchaseRepository = new PurchaseRepositoryJdbcImpl(dataSource);
        PurchaseService purchaseService = new PurchaseServiceImpl(purchaseRepository);

        Basket basket = (Basket) req.getServletContext().getAttribute("basket");
        List<Product> products = basket.getProducts();
        if (!products.isEmpty()) {
            Purchase purchase = Purchase.builder().basketId(basket.getId()).products(products).customer(user).build();
            purchaseService.addPurchase(purchase);
        }

        resp.sendRedirect("/profile");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
