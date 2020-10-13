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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/services")
public class ServicesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");

        ProductRepository productRepository = new ProductRepositoryJdbcImpl(dataSource);
        ProductService productService = new ProductServiceImpl(productRepository);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);

        BasketRepository basketRepository = new BasketRepositoryJdbcImpl(dataSource, usersService);
        BasketService basketService = new BasketServiceImpl(basketRepository);

        req.setAttribute("products", productService.getAllProducts());

        String id = req.getParameter("id");
        User user = (User) req.getServletContext().getAttribute("user");

        if (id != null) {
            if (user != null) {
                Basket basket = (Basket) req.getServletContext().getAttribute("basket");
                Product product =  productService.getProductById(Integer.parseInt(id));
                if (basket == null) {
                    basket = Basket.builder().products(new ArrayList<>()).user(user).build();
                    basketService.createBasket(basket);
                    req.getServletContext().setAttribute("basket", basket);
                }
                List<Product> userProducts = basket.getProducts();
                userProducts.add(product);
                basket.setProducts(userProducts);
                basketService.addProductInBasket(basket, productService.getProductById(Integer.parseInt(id)));
                req.getRequestDispatcher("/services.ftl").forward(req, resp);
            } else {
                resp.sendRedirect("/auth");
            }
        } else {
            req.getRequestDispatcher("/services.ftl").forward(req, resp);
        }
    }
}
