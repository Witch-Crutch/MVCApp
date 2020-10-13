package ru.itis.witchCrutch.servlets.servlet;

import ru.itis.witchCrutch.repositories.UsersRepository;
import ru.itis.witchCrutch.repositories.UsersRepositoryJdbcImpl;
import ru.itis.witchCrutch.services.UsersService;
import ru.itis.witchCrutch.services.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");
        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        final boolean remember = req.getParameter("remember") != null;

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("email")) email = cookie.getValue();
            if (cookie.getName().equals("password")) password = cookie.getValue();
        }

        final HttpSession session = req.getSession();

        if (session != null && session.getAttribute("email") != null && session.getAttribute("password") != null) {
            resp.sendRedirect("/profile");
        } else if (email != null && password != null && usersService.userIsExist(email)) {
            if (remember) {
                resp.addCookie(new Cookie("email", email));
                resp.addCookie(new Cookie("password", password));
            }
            req.getSession().setAttribute("email", email);
            req.getSession().setAttribute("password", password);
            resp.sendRedirect("/profile");
        } else {
            resp.sendRedirect("/auth");
        }
    }
}