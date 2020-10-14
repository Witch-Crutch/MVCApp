package ru.itis.witchCrutch.servlets.servlet;

import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.repositories.PurchaseRepository;
import ru.itis.witchCrutch.repositories.PurchaseRepositoryJdbcImpl;
import ru.itis.witchCrutch.services.PurchaseService;
import ru.itis.witchCrutch.services.PurchaseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");

        PurchaseRepository purchaseRepository = new PurchaseRepositoryJdbcImpl(dataSource);
        PurchaseService purchaseService = new PurchaseServiceImpl(purchaseRepository);

        User user = (User) req.getServletContext().getAttribute("user");

        req.setAttribute("purchase", purchaseRepository.userPurchase(user));
        req.getRequestDispatcher("/profile.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
