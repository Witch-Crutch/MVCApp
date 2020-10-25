package ru.itis.witchCrutch.controllers.filters;

import ru.itis.witchCrutch.models.Basket;
import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.services.interfaces.BasketService;
import ru.itis.witchCrutch.services.interfaces.UsersService;
import ru.itis.witchCrutch.util.CookieActions;
import ru.itis.witchCrutch.util.FileToImage;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        UsersService usersService = (UsersService) req.getServletContext().getAttribute("userService");

        BasketService basketService = (BasketService) req.getServletContext().getAttribute("basketService");

        User user = (User) req.getSession().getAttribute("user");

        if (user != null) {
            filterChain.doFilter(servletRequest, servletResponse);
            setBasket(user, basketService, req);
            return;
        }

        String email = "";
        String password = "";

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("email")) {
                email = cookie.getValue();
            } else if (cookie.getName().equals("password")) {
                password = cookie.getValue();
            }
        }
        if (!email.equals("") && !password.equals("")) {
            user = usersService.getUserByEmailPassword(email, password);
            if (user != null) {
                if (user.getProfileImg() != null) {
                    user.setImage(FileToImage.toImage(user.getProfileImg()));
                }
                req.getSession().setAttribute("user", user);
                setBasket(user, basketService, req);
            } else {
                CookieActions.deleteCookies(req, resp, "email", "password");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void setBasket(User user, BasketService basketService, HttpServletRequest req) {
        Basket basket = basketService.getUserBasket(user);
        if (basket == null) {
            basket = Basket.builder().products(new ArrayList<>()).user(user).build();
            basketService.createBasket(basket);
        }

        req.getSession().setAttribute("basket", basket);
    }
}
