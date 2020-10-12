package ru.itis.witchCrutch.servlets.servlet;

import ru.itis.witchCrutch.models.Telephone;
import ru.itis.witchCrutch.repositories.TelephoneRepository;
import ru.itis.witchCrutch.repositories.TelephoneRepositoryJdbcImpl;
import ru.itis.witchCrutch.repositories.UsersRepository;
import ru.itis.witchCrutch.repositories.UsersRepositoryJdbcImpl;
import ru.itis.witchCrutch.services.TelephoneService;
import ru.itis.witchCrutch.services.TelephoneServiceImpl;
import ru.itis.witchCrutch.services.UsersService;
import ru.itis.witchCrutch.services.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/contact")
public class ContactServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/contact.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: регулярка ?
        String tel = req.getParameter("tel");
        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");
        TelephoneRepository telephoneRepository = new TelephoneRepositoryJdbcImpl(dataSource);
        TelephoneService telephoneService = new TelephoneServiceImpl(telephoneRepository);

        if (tel != null) {
            telephoneService.saveTelephone(Telephone.builder().telephone(tel).build());
            resp.sendRedirect("/services");
        } else {
            resp.sendRedirect("/contact");
        }
    }
}
