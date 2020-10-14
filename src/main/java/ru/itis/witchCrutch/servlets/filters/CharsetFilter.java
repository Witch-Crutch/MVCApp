package ru.itis.witchCrutch.servlets.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter({"/auth", "/main", "/register", "/profile", "/products", "/quit", "/chat"})
public class CharsetFilter implements Filter {
    private String encoding;

    public void init(FilterConfig config) {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) encoding = "UTF-8";
    }

    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=UTF-8");
        next.doFilter(request, response);
    }

    public void destroy() {
    }
}
