package univ.lorraine.GestionProdAPI.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import univ.lorraine.GestionProdAPI.service.MetricService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MetricFilter implements Filter {

    @Autowired
    private MetricService metricService;

    @Override
    public void init(FilterConfig config) throws ServletException {
        metricService = (MetricService) WebApplicationContextUtils
                .getRequiredWebApplicationContext(config.getServletContext())
                .getBean("metricService");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws java.io.IOException, ServletException {
        HttpServletRequest httpRequest = ((HttpServletRequest) request);
        String req = httpRequest.getMethod() + " " + httpRequest.getRequestURI();

        chain.doFilter(request, response);

        int status = ((HttpServletResponse) response).getStatus();
        System.out.println(req + " " + status);
        metricService.increaseCount(req, status);
    }
}
