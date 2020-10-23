package ru.itis.witchCrutch.controllers.servlet;

import ru.itis.witchCrutch.util.CookieActions;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/quit")
public class QuitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();

        CookieActions.deleteCookies(req, resp, "email", "password");

        req.getSession().setAttribute("user", null);
        req.getSession().setAttribute("basket", null);

        resp.sendRedirect("/main");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
