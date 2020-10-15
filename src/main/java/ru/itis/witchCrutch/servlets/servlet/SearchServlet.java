package ru.itis.witchCrutch.servlets.servlet;

import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.repositories.ProductRepository;
import ru.itis.witchCrutch.repositories.ProductRepositoryJdbcImpl;
import ru.itis.witchCrutch.services.ProductService;
import ru.itis.witchCrutch.services.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: проверка на sql injection ?
        String input = req.getParameter("input");
        boolean popular = req.getParameter("popular") != null;
        boolean price = req.getParameter("price") != null;

        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");
        ProductRepository productRepository = new ProductRepositoryJdbcImpl(dataSource);
        ProductService productService = new ProductServiceImpl(productRepository);

        if (input == null) {
            input = " ";
        }
        input = input.toLowerCase();
        List<Product> products;
        if (price) {
            products = productService.getProductsByNameOrderByPrice(input);
        } else if (popular) {
            products = productService.getProductsByNameOrderByPopular(input);
        } else {
            products = productService.getProductsByName(input);
        }
        req.setAttribute("products", products);
        req.getRequestDispatcher("/services.ftl").forward(req, resp);
    }
}
