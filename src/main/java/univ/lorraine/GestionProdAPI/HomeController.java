package univ.lorraine.GestionProdAPI;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "Home", description = "Gestion des metrics", tags = "home")
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
    @ApiOperation(value = "Récupération de toutes les données liées aux metrics de l'application")
    @RequestMapping(value = "/status-metric", method = RequestMethod.GET)
    @ResponseBody
    public Map getStatusMetric() {
        return metricService.getFullMetric();
    }

    @ApiOperation(value = "Récupération des données pour faire un graphe")
    @RequestMapping(value = "/metric-graph-data", method = RequestMethod.GET)
    @ResponseBody
    public Object[][] getMetricData() {
        return metricService.getGraphData();
    }

    @ApiOperation(value = "Accès à la page du graphe des metrics")
    @GetMapping("/graphe")
    public String getGraphe() {
        return "metric";
    }
}
