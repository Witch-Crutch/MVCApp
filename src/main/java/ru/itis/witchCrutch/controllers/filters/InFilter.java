package ru.itis.witchCrutch.controllers.filters;

import ru.itis.witchCrutch.models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/auth", "/register"})
public class InFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        User user = (User) req.getSession().getAttribute("user");

        if (user != null) {
            resp.sendRedirect("/profile");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
