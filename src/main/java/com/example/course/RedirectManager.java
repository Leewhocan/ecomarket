package com.example.course;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RedirectManager implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI();


        if (path.startsWith("/api") ||
                path.matches(".*.html") ||
                path.matches(".*.js") ||
                path.matches(".*.css") ||
                path.matches(".*.ico")
        ) {
            chain.doFilter(request, response);
            return;
        }
        request.getRequestDispatcher("/").forward(request, response);
    }
}
