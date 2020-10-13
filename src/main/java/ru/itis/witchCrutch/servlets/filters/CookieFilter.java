package ru.itis.witchCrutch.servlets.filters;

import ru.itis.witchCrutch.models.Basket;
import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.repositories.BasketRepository;
import ru.itis.witchCrutch.repositories.BasketRepositoryJdbcImpl;
import ru.itis.witchCrutch.repositories.UsersRepository;
import ru.itis.witchCrutch.repositories.UsersRepositoryJdbcImpl;
import ru.itis.witchCrutch.services.BasketService;
import ru.itis.witchCrutch.services.BasketServiceImpl;
import ru.itis.witchCrutch.services.UsersService;
import ru.itis.witchCrutch.services.UsersServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.IOException;


@WebFilter({
        "/auth", "/main", "/register", "/profile", "/services", "/quit", "/basket", "/advantages",
        "/contact", "/stages"})
public class CookieFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);

        BasketRepository basketRepository = new BasketRepositoryJdbcImpl(dataSource, usersService);
        BasketService basketService = new BasketServiceImpl(basketRepository);

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
            Basket basket = basketService.getUserBasket(user);
            if (basket != null) {
                req.getServletContext().setAttribute("basket", basket);
            }
            if (user != null) req.getServletContext().setAttribute("user", user);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
