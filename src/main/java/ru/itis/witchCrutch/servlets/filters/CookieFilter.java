package ru.itis.witchCrutch.servlets.filters;

import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.repositories.UsersRepository;
import ru.itis.witchCrutch.repositories.UsersRepositoryJdbcImpl;
import ru.itis.witchCrutch.services.UsersService;
import ru.itis.witchCrutch.services.UsersServiceImpl;
import ru.itis.witchCrutch.util.HashPassword;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.IOException;

@WebFilter("/*")
public class CookieFilter implements Filter {

    private UsersService usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");
        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        this.usersService = new UsersServiceImpl(usersRepository);

        Cookie[] cookies = req.getCookies();

        String name = null;
        String password = null;

        for(Cookie cookie : cookies) {
            if (cookie.getName().equals("name")) name = cookie.getValue();
            if (cookie.getName().equals("password")) password = cookie.getValue();
        }
        if (name != null && password != null) {
            User user = usersService.getUserByNamePassword(name, HashPassword.getHash(name, password));

            if (user != null) req.getServletContext().setAttribute("user", user);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
