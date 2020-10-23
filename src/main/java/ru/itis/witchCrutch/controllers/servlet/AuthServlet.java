package ru.itis.witchCrutch.controllers.servlet;

import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.services.interfaces.UsersService;
import ru.itis.witchCrutch.util.CookieActions;
import ru.itis.witchCrutch.util.HashPassword;
import ru.itis.witchCrutch.util.Validator;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UsersService usersService = (UsersService) req.getServletContext().getAttribute("userService");

        User user;

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        final boolean remember = req.getParameter("remember") != null;

        if (Validator.validAuth(email, password)) {
            String hash = HashPassword.getHash(email, password);
            user = usersService.getUserByEmailPassword(email, hash);
            if (user != null) {
                req.getSession().setAttribute("user", user);
                if (remember) {
                    Cookie emailCookie = new Cookie("email", email);
                    emailCookie.setMaxAge(60 * 60 * 24 * 365);
                    Cookie hashCookie = new Cookie("password", hash);
                    hashCookie.setMaxAge(60 * 60 * 24 * 365);
                    resp.addCookie(emailCookie);
                    resp.addCookie(hashCookie);
                }
                resp.sendRedirect("/profile");
            } else {
                resp.sendRedirect("/auth");
            }
        } else {
            resp.sendRedirect("/auth");
        }
    }
}
