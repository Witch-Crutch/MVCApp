package ru.itis.witchCrutch.controllers.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/auth", "/register"})
public class InFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String email = (String) req.getSession().getAttribute("email");
        String hash = (String) req.getSession().getAttribute("password");

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("email")) email = cookie.getValue();
                if (cookie.getName().equals("password")) hash = cookie.getValue();
            }
        }

        if (email != null && hash != null) {
            resp.sendRedirect("/profile");
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
