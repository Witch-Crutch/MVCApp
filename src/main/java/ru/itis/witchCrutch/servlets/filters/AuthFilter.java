package ru.itis.witchCrutch.servlets.filters;

import ru.itis.witchCrutch.repositories.UsersRepository;
import ru.itis.witchCrutch.repositories.UsersRepositoryJdbcImpl;
import ru.itis.witchCrutch.services.UsersService;
import ru.itis.witchCrutch.services.UsersServiceImpl;
import ru.itis.witchCrutch.util.HashPassword;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
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

        final String name = req.getParameter("name");
        final String password = req.getParameter("password");

        final HttpSession session = req.getSession();
        //TODO: переводить на куку по желанию
        if (session != null && session.getAttribute("name") != null && session.getAttribute("password") != null) {
            redirectTo(req, resp, "/main");
        } else if (name != null && password != null && usersService.userIsExist(name, HashPassword.getHash(name, password))) {
            req.getSession().setAttribute("name", name);
            req.getSession().setAttribute("password", password);
            redirectTo(req, resp, "/main");
        } else {
            forwardTo(req, resp, "/WEB-INF/views/jsp/auth.jsp");
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
