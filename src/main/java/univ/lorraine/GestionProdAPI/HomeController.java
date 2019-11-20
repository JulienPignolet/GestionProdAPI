package univ.lorraine.GestionProdAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import univ.lorraine.GestionProdAPI.service.MetricService;

import java.util.Map;

@Controller
@RequestMapping(("/"))
public class HomeController {

    private final MetricService metricService;

    @Autowired
    public HomeController(MetricService metricService) {
        this.metricService = metricService;
    }

    // API METRICS
    /**
     @RequestMapping(value = "/metric-graph-data", method = RequestMethod.GET)
     @ResponseBody
     public Object[][] getMetricData() {
     return metricService.getGraphData();
     }
     **/
    @RequestMapping(value = "/status-metric", method = RequestMethod.GET)
    @ResponseBody
    public Map getStatusMetric() {
        return metricService.getFullMetric();
    }

    @RequestMapping(value = "/metric-graph-data", method = RequestMethod.GET)
    @ResponseBody
    public Object[][] getMetricData() {
        return metricService.getGraphData();
    }

    @GetMapping("/graphe")
    public String getGraphe() {
        return "metric";
    }
}
