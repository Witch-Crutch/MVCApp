package ru.itis.witchCrutch.servlets.servlet;

import ru.itis.witchCrutch.jdbc.repositories.UsersRepositoryJdbcImpl;
import ru.itis.witchCrutch.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String password_again = req.getParameter("password_again");

        @SuppressWarnings("unchecked")
        final AtomicReference<UsersRepositoryJdbcImpl> userRepository = (AtomicReference<UsersRepositoryJdbcImpl>) req.getServletContext().getAttribute("userRepository");

        if (password.equals(password_again) && !userRepository.get().userIsExist(name, password)) {

            byte[] cryptPassword = null;
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                //TODO: исправить на почту
                messageDigest.update(name.getBytes(StandardCharsets.UTF_8));
                messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
                cryptPassword = messageDigest.digest();
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalArgumentException();
            }

            User user = new User(name, "", "", new String(cryptPassword));
            userRepository.get().addUser(user);

        }
    }
}
