package ru.itis.witchCrutch.controllers.servlet;

import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.services.interfaces.UsersService;
import ru.itis.witchCrutch.util.CookieActions;
import ru.itis.witchCrutch.util.HashPassword;

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

        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");

        UsersService usersService = (UsersService) req.getServletContext().getAttribute("userService");

        String email = "";
        String password = "";

        User user = (User) req.getSession().getAttribute("user");

        if (user != null) {
            resp.sendRedirect("/profile");
        }

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("email")) email = cookie.getValue();
            if (cookie.getName().equals("password")) password = cookie.getValue();
        }

        if (!email.equals("") && !password.equals("")) {
            user = usersService.getUserByEmailPassword(email, password);
            if (user != null) {
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("/profile");
            } else {
                CookieActions.deleteCookies(req, resp, "email", "password");
            }
        }

        email = req.getParameter("email");
        password = req.getParameter("password");
        final boolean remember = req.getParameter("remember") != null;

        if (email != null && password != null) {
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
        }
    }
}
