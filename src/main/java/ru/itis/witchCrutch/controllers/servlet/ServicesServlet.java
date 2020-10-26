package ru.itis.witchCrutch.controllers.servlet;

import ru.itis.witchCrutch.services.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/services")
public class ServicesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = (ProductService) req.getServletContext().getAttribute("productService");

        req.setAttribute("products", productService.getAllProducts());

        req.getRequestDispatcher("/services.ftl").forward(req, resp);
    }
}
