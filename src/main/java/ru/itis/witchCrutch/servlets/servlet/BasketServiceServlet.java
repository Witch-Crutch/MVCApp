package ru.itis.witchCrutch.servlets.servlet;

import ru.itis.witchCrutch.models.Basket;
import ru.itis.witchCrutch.models.Product;
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

@WebServlet("/basketService")
public class BasketServiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");

        ProductRepository productRepository = new ProductRepositoryJdbcImpl(dataSource);
        ProductService productService = new ProductServiceImpl(productRepository);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);

        BasketRepository basketRepository = new BasketRepositoryJdbcImpl(dataSource, usersService);
        BasketService basketService = new BasketServiceImpl(basketRepository);

        User user = (User) req.getServletContext().getAttribute("user");
        Basket basket = (Basket) req.getServletContext().getAttribute("basket");
        System.out.println("basket: " + basket);

        String add = req.getParameter("add");
        String delete = req.getParameter("delete");
        try {
            if (add != null) {
                addProduct(basketService, basket, productService, Integer.parseInt(add));
                resp.sendRedirect("/services");
            } else if (delete != null) {
                deleteProduct(basketService, basket, productService, Integer.parseInt(delete));
                resp.sendRedirect("/basket");
            }
        } catch (NumberFormatException ignored){ }
        if (add == null && delete == null) {
            resp.sendRedirect("/main");
        }
    }

    private void addProduct(BasketService basketService, Basket basket, ProductService productService, int id) {
        Product product = productService.getProductById(id);

        basketService.addProductInBasket(basket, productService.getProductById(id));
    }

    private void deleteProduct(BasketService basketService, Basket basket, ProductService productService, int id) {
        basketService.deleteProductFromBasket(basket, productService.getProductById(id));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
