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
import java.util.ArrayList;


@WebFilter({
        "/auth", "/main", "/register", "/profile", "/services", "/quit", "/basket", "/advantages",
        "/contact", "/stages", "/basketService", "/purchase", "/chat", "/message"})
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
        String hash = (String) req.getSession().getAttribute("password");


        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("email")) email = cookie.getValue();
                if (cookie.getName().equals("password")) hash = cookie.getValue();
            }
        }
        Basket basket;
        if (email != null && hash != null && usersService.userAuth(email, hash)) {
            User user = usersService.getUserByEmail(email);
            basket = basketService.getUserBasket(user);

            if (basket == null) {
                basket = Basket.builder().products(new ArrayList<>()).user(user).build();
                basketService.createBasket(basket);
            }

            req.getServletContext().setAttribute("basket", basket);
            if (user != null) req.getServletContext().setAttribute("user", user);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
