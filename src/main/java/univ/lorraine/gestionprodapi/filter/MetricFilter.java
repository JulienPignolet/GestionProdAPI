package univ.lorraine.gestionprodapi.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import univ.lorraine.gestionprodapi.service.MetricService;

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
        String req = request.getMethod() + " " + request.getRequestURI();

        if(!req.equals("GET /favicon.ico") ){
            chain.doFilter(request, response);
            int status = response.getStatus();

            if( !req.equals("GET /graphe") && !req.equals("GET /metric-graph-data")){
                metricService.increaseCount(req, status);

            }
        }
    }
}
