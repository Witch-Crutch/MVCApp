package ru.itis.witchCrutch.controllers.servlet;

import ru.itis.witchCrutch.models.Message;
import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.services.interfaces.MessageService;
import ru.itis.witchCrutch.services.interfaces.UsersService;
import ru.itis.witchCrutch.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/chat")
@MultipartConfig
public class ChatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UsersService usersService = (UsersService) req.getServletContext().getAttribute("userService");

        String tomcatBase = System.getProperty("catalina.home");
        String uploadPath = String.format("%s\\webapps\\ROOT\\views\\uploads", tomcatBase);

        MessageService messageService = (MessageService) req.getServletContext().getAttribute("messageService");

        User user = (User) req.getSession().getAttribute("user");

        List<Message> messages = messageService.userMessage(user);
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/chat.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UsersService usersService = (UsersService) req.getServletContext().getAttribute("userService");

        MessageService messageService = (MessageService) req.getServletContext().getAttribute("messageService");

        User user = (User) req.getSession().getAttribute("user");

        Timestamp date = new Timestamp(System.currentTimeMillis());

        String text = req.getParameter("text") == null ? "" : req.getParameter("text");
        InputStream file = req.getPart("file").getInputStream();

        if (text != null || file != null) {
            Message message = Message.builder().message(text == null ? "" : text).file(file).sender(user).receiver(User.builder().id(Constants.ADMIN_ID).build()).build();
            messageService.uploadMessage(message);
            List<Message> messages = messageService.userMessage(user);

            req.setAttribute("messages", messages);
        }

        resp.sendRedirect("/chat");
    }
}
