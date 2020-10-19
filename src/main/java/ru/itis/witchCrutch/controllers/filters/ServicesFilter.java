package ru.itis.witchCrutch.controllers.filters;

import org.json.simple.JSONArray;
import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.services.ProductService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@WebFilter("/service")
public class ServicesFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        ProductService productService = (ProductService) req.getServletContext().getAttribute("productService");
        List<Product> productList = productService.getAllProducts();
        JSONArray products = new JSONArray();
        for (Product product : productList) {
            products.add(product.getName());
        }
        servletResponse.setContentType("application/json");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.getWriter().write(products.toString());
    }
}
