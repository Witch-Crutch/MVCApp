package ru.itis.witchCrutch.controllers.servlet;

import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.services.interfaces.MessageService;
import ru.itis.witchCrutch.services.interfaces.UsersService;
import ru.itis.witchCrutch.util.FileToImage;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@MultipartConfig
@WebServlet("/chg_img")
public class ChangeImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/profile");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersService usersService = (UsersService) req.getServletContext().getAttribute("userService");

        MessageService messageService = (MessageService) req.getServletContext().getAttribute("messageService");

        User user = (User) req.getSession().getAttribute("user");

        InputStream file = null;
        InputStream file2 = null;
        if (req.getPart("file").getSize() > 0) {
            file = req.getPart("file").getInputStream();
            file2 = req.getPart("file").getInputStream();
        }

        if (file != null) {
            user.setProfileImg(file);
            usersService.updateUser(user);
            user.setImage(FileToImage.toImage(file2));
        }

        resp.sendRedirect("/profile");
    }
}
