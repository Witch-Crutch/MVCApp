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

    private UsersService usersService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");
        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        this.usersService = new UsersServiceImpl(usersRepository);

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String password_again = req.getParameter("password_again");


        if (password.equals(password_again) && !usersService.userIsExist(name, password)) {
            //TODO: исправить на почту
            String hash = HashPassword.getHash(name, password);

            User user = User.builder().name(name).password(hash).build();
            usersService.addUser(user);
        }
    }
}
