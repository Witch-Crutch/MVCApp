package ru.itis.witchCrutch.controllers.servlet;

import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.services.interfaces.UsersService;
import ru.itis.witchCrutch.util.HashPassword;
import ru.itis.witchCrutch.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private String error;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error", error);
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

        if (Validator.validRegister(name, lastname, email, password, password_again)) {
            name = name.trim();
            lastname = lastname.trim();
            email = email.trim();
            password = password.trim();
            String hash = HashPassword.getHash(email, password);
            if (usersService.userIsExist(email)) {
                this.error = "Такой пользователь уже существует";
                resp.sendRedirect("/register");
            } else {
                User user = User.builder().name(name).password(hash).lastname(lastname).email(email).build();
                usersService.addUser(user);
                req.getSession().setAttribute("user", usersService.getUserByEmailPassword(email, hash));
                this.error = null;
                resp.sendRedirect("/profile");
            }
        } else {
            this.error = "Проверьте правильность заполненных полей";
            resp.sendRedirect("/register");
        }
    }
}
