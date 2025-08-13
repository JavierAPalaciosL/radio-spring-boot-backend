package io.radioweather.radioweatherapi.infrastructure.filters;

import java.io.IOException;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CorsFilter implements javax.servlet.Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletResponse instanceof HttpServletResponse) {
            HttpServletResponse res = (HttpServletResponse) servletResponse;
            HttpServletRequest req = (HttpServletRequest) servletRequest;

            res.setHeader("Access-Control-Allow-Origin", "*");
            res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            res.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
            res.setHeader("Access-Control-Max-Age", "3600");

            if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
                res.setStatus(HttpServletResponse.SC_OK);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
