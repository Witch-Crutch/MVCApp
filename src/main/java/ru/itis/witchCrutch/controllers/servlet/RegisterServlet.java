package ru.itis.witchCrutch.controllers.servlet;

import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.services.interfaces.UsersService;
import ru.itis.witchCrutch.util.HashPassword;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UsersService usersService = (UsersService) req.getServletContext().getAttribute("userService");

        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String password_again = req.getParameter("password_again");

        // TODO добавить валидатор
        if (name != null && lastname != null && email != null && password != null && password_again != null && password.equals(password_again)) {
            String hash = HashPassword.getHash(email, password);
            if (usersService.userIsExist(email)) {
                resp.sendRedirect("/register");
            } else {
                User user = User.builder().name(name).password(hash).lastname(lastname).email(email).build();
                usersService.addUser(user);
                req.getSession().setAttribute("user", usersService.getUserByEmailPassword(email, hash));
                resp.sendRedirect("/profile");
            }
        }
    }
}
