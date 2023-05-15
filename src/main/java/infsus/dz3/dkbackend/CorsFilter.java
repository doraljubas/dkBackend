package infsus.dz3.dkbackend;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", " Content-Type");
        response.setHeader("Access-Control-Max-Age", "3600");
        if (!"OPTIONS".equals(request.getMethod())) {
            chain.doFilter(req, res);
        } else {
            // return OK status for OPTIONS request
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // initialization
    }

    @Override
    public void destroy() {
        // destruction
    }
}
