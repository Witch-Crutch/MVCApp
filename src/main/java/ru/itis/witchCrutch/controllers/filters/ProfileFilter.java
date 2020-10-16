package ru.itis.witchCrutch.controllers.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/profile", "/basket", "/basketService", "/purchase", "/chat"})
public class ProfileFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String email = (String) req.getSession().getAttribute("email");
        String password = (String) req.getSession().getAttribute("password");

        for(Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("email")) email = cookie.getValue();
            if (cookie.getName().equals("password")) password = cookie.getValue();
        }

        if (email != null && password != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            resp.sendRedirect("/main");
        }
    }
}
