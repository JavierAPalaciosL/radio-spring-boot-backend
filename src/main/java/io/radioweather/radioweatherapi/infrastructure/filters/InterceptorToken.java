package io.radioweather.radioweatherapi.infrastructure.filters;
import io.radioweather.radioweatherapi.infrastructure.utils.JWTEngine;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class InterceptorToken implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String authenticationHeader = request.getHeader("Authorization");

        if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = authenticationHeader.substring("Bearer ".length());
        String subject = "";

        try {
            subject = JWTEngine.validateToken(token).getSubject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subject, null, null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(servletRequest, servletResponse);

    }

}
