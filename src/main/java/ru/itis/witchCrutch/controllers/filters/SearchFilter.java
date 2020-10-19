package ru.itis.witchCrutch.controllers.filters;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.itis.witchCrutch.models.Product;
import ru.itis.witchCrutch.services.ProductService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.SortedMap;

@WebFilter("/search")
public class SearchFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (req.getMethod().equals("POST")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String input = servletRequest.getParameter("input");
        String filter = servletRequest.getParameter("filter");

        input = input == null ? "" : input;

        ProductService productService = (ProductService) req.getServletContext().getAttribute("productService");
        List<Product> productList = null;

        if (filter != null) {
            if (filter.equals("price")) {
                productList = productService.getProductsByNameOrderByPrice(input);
            } else if (filter.equals("popular")) {
                productList = productService.getProductsByNameOrderByPopular(input);
            }
        } else {
            productList = productService.getProductsByName(input);
        }

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Product product : productList) {
            JSONObject productJson = new JSONObject();
            productJson.put("name", product.getName());
            productJson.put("price", product.getPrice());
            productJson.put("id", product.getId());
            productJson.put("image", product.getImage());
            jsonArray.add(productJson);
        }
        jsonObject.put("result", jsonArray);
        servletResponse.setContentType("application/json");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.getWriter().write(jsonObject.toString());
    }
}