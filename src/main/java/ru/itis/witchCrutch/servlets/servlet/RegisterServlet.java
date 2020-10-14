package ru.itis.witchCrutch.servlets.servlet;

import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.repositories.UsersRepositoryJdbcImpl;
import ru.itis.witchCrutch.repositories.UsersRepository;
import ru.itis.witchCrutch.services.UsersService;
import ru.itis.witchCrutch.services.UsersServiceImpl;
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

        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");
        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);

        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String password_again = req.getParameter("password_again");

        if (password.equals(password_again) && !usersService.userIsExist(email)) {
            String hash = HashPassword.getHash(email, password);

            User user = User.builder().name(name).password(hash).lastname(lastname).email(email).build();
            usersService.addUser(user);

            req.getSession().setAttribute("email", email);
            req.getSession().setAttribute("password", hash);
            resp.sendRedirect("/profile");
        }
        else {
            resp.sendRedirect("/register");
        }
    }
}
