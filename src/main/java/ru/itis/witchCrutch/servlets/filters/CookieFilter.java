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

//TODO: может другой контекст ?
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

        String email = (String) req.getSession().getAttribute("email");
        String password = (String) req.getSession().getAttribute("password");

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("email")) email = cookie.getValue();
                if (cookie.getName().equals("password")) password = cookie.getValue();
            }
        }
        if (email != null && password != null) {
            User user = usersService.getUserByEmail(email);

            if (user != null) req.getServletContext().setAttribute("user", user);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
