package univ.lorraine.GestionProdAPI.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import univ.lorraine.GestionProdAPI.service.MetricService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MetricFilter extends OncePerRequestFilter {

    @Autowired
    private MetricService metricService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = ((HttpServletRequest) request);
        String req = httpRequest.getMethod() + " " + httpRequest.getRequestURI();

        if(!req.equals("GET /favicon.ico")){
            chain.doFilter(request, response);
            int status = ((HttpServletResponse) response).getStatus();
            System.out.println("1 requete : " + req + " " + status);

            metricService.increaseCount(req, status);
        }
    }
}
