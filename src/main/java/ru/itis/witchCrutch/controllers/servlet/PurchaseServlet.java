package ru.itis.witchCrutch.controllers.servlet;

import ru.itis.witchCrutch.models.Basket;
import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.models.Purchase;
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

@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");

        User user = (User) req.getSession().getAttribute("user");

        ProductService productService = (ProductService) req.getServletContext().getAttribute("productService");

        UsersService usersService = (UsersService) req.getServletContext().getAttribute("userService");

        BasketService basketService = (BasketService) req.getServletContext().getAttribute("basketService");

        PurchaseService purchaseService = (PurchaseService) req.getServletContext().getAttribute("purchaseService");

        Basket basket = (Basket) req.getSession().getAttribute("basket");
        if (basket != null) {
            List<Product> products = basket.getProducts();
            if (!products.isEmpty()) {
                Purchase purchase = Purchase.builder().basketId(basket.getId()).products(products).customer(user).build();
                purchaseService.addPurchase(purchase);
            } else {
                resp.sendRedirect("/basket?error=empty");
                return;
            }
        } else {
            resp.sendRedirect("/basket");
            return;
        }

        resp.sendRedirect("/profile");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
