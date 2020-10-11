package ru.itis.witchCrutch.servlets.servlet;

import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.repositories.ProductRepository;
import ru.itis.witchCrutch.repositories.ProductRepositoryJdbcImpl;
import ru.itis.witchCrutch.services.ProductService;
import ru.itis.witchCrutch.services.ProductServiceImpl;
import ru.itis.witchCrutch.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");
        ProductRepository productRepository = new ProductRepositoryJdbcImpl(dataSource);
        productService = new ProductServiceImpl(productRepository);

        req.setAttribute("products", productService.getAllProducts());

        req.getRequestDispatcher("/products.ftl").forward(req, resp);
    }
}
