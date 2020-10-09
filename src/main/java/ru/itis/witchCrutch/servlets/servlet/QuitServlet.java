package ru.itis.witchCrutch.servlets.servlet;

import ru.itis.witchCrutch.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/quit")
public class QuitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("name") || cookies[i].getName().equals("password")) {
                cookies[i].setValue("");
                cookies[i].setPath("/");
                cookies[i].setMaxAge(0);
                resp.addCookie(cookies[i]);
            }
        }

        req.getServletContext().setAttribute("user", null);
        req.getSession().setAttribute("name", null);
        req.getSession().setAttribute("password", null);

        resp.sendRedirect("/main");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
