package ru.itis.witchCrutch.controllers.servlet;

import ru.itis.witchCrutch.models.Telephone;
import ru.itis.witchCrutch.services.interfaces.TelephoneService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/contact")
public class ContactServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/contact.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String tel = req.getParameter("tel");

        TelephoneService telephoneService = (TelephoneService) req.getServletContext().getAttribute("telephoneService");

        if (tel != null) {
            telephoneService.saveTelephone(Telephone.builder().telephone(tel).build());
            resp.sendRedirect("/services");
        } else {
            resp.sendRedirect("/contact");
        }
    }
}
