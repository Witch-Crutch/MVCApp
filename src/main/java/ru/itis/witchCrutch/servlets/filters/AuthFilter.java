package ru.itis.witchCrutch.servlets.filters;

import ru.itis.witchCrutch.repositories.UsersRepository;
import ru.itis.witchCrutch.repositories.UsersRepositoryJdbcImpl;
import ru.itis.witchCrutch.services.UsersService;
import ru.itis.witchCrutch.services.UsersServiceImpl;
import ru.itis.witchCrutch.util.HashPassword;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns="/auth")
public class AuthFilter implements Filter {

    private UsersService usersService;

    @Override
    public void doFilter(ServletRequest reqS, ServletResponse respS, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) reqS;
        HttpServletResponse resp = (HttpServletResponse) respS;

        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");
        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        this.usersService = new UsersServiceImpl(usersRepository);

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        final boolean remember = req.getParameter("remember") != null;

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("email")) email = cookie.getValue();
            if (cookie.getName().equals("password")) password = cookie.getValue();
        }

        final HttpSession session = req.getSession();

        if (session != null && session.getAttribute("email") != null && session.getAttribute("password") != null) {
            redirectTo(req, resp, "/main");
        } else if (email != null && password != null && usersService.userIsExist(email)) {
            if (remember) {
                System.out.println("hello");
                resp.addCookie(new Cookie("email", email));
                resp.addCookie(new Cookie("password", password));
            }
            req.getSession().setAttribute("email", email);
            req.getSession().setAttribute("password", password);
            redirectTo(req, resp, "/main");
        } else {
            forwardTo(req, resp, "auth.ftl");
        }
    }

    private void redirectTo(HttpServletRequest req, HttpServletResponse resp, String context) {
        try {
            resp.sendRedirect(context);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    private void forwardTo(HttpServletRequest req, HttpServletResponse resp, String context) {
        try {
            req.getRequestDispatcher(context).forward(req, resp);
        } catch (IOException | ServletException e) {
            throw new IllegalArgumentException();
        }
    }
}
