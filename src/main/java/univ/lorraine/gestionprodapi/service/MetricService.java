package univ.lorraine.gestionprodapi.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class MetricService  {

    private ConcurrentMap<String, ConcurrentHashMap<Integer, Integer>> metricMap;
    private ConcurrentMap<Integer, Integer> statusMetric;
    private ConcurrentMap<String, ConcurrentHashMap<Integer, Integer>> timeMap;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public MetricService() {
        super();
        metricMap = new ConcurrentHashMap<>();
        statusMetric = new ConcurrentHashMap<>();
        timeMap = new ConcurrentHashMap<>();
    }

    // API

    public void increaseCount(final String request, final int status) {
        increaseMainMetric(request, status);
        increaseStatusMetric(status);
        updateTimeMap(status);
    }

    public String get404Page(){
        StringBuilder s = new StringBuilder();
        for (Map.Entry<String,ConcurrentHashMap<Integer, Integer>> entry : metricMap.entrySet()){

            s.append("Nom de la page : ").append(entry.getKey());
            s.append("<br>");
            entry.getValue().forEach((code, number) ->
                s.append("Code : ").append(code).append(" apparu ").append(number).append(" fois.")
            );

            s.append("<br>");
            s.append("<br>");
        }

        return s.toString();
    }

    public Map getFullMetric() {
        return metricMap;
    }

    public Map getStatusMetric() {
        return statusMetric;
    }

    public Object[][] getGraphData() {
        final int colCount = statusMetric.keySet().size() + 1;
        final Set<Integer> allStatus = statusMetric.keySet();
        final int rowCount = timeMap.keySet().size() + 1;

        final Object[][] result = new Object[rowCount][colCount];
        result[0][0] = "Time";

        int j = 1;
        for (final int status : allStatus) {
            result[0][j] = String.valueOf(status);
            j++;
        }
        int i = 1;
        ConcurrentMap<Integer, Integer> tempMap;
        for (final Map.Entry<String, ConcurrentHashMap<Integer, Integer>> entry : timeMap.entrySet()) {
            result[i][0] = entry.getKey();
            tempMap = entry.getValue();
            for (j = 1; j < colCount; j++) {
                result[i][j] = tempMap.get(Integer.parseInt((String)result[0][j]));
                if (result[i][j] == null) {
                    result[i][j] = 0;
                }
            }
            i++;
        }

        return result;
    }

    // NON-API

    private void increaseMainMetric(final String request, final int status) {
        ConcurrentHashMap<Integer, Integer> statusMap = metricMap.get(request);
        if (statusMap == null) {
            statusMap = new ConcurrentHashMap<>();
        }

        Integer count = statusMap.get(status);
        if (count == null) {
            count = 1;
        } else {
            count++;
        }
        statusMap.put(status, count);
        metricMap.put(request, statusMap);
    }

    private void increaseStatusMetric(final int status) {
        statusMetric.merge(status, 1, (a, b) -> a + b);
    }

    private void updateTimeMap(final int status) {
        final String time = dateFormat.format(new Date());
        ConcurrentHashMap<Integer, Integer> statusMap = timeMap.get(time);
        if (statusMap == null) {
            statusMap = new ConcurrentHashMap<>();
        }

        Integer count = statusMap.get(status);
        if (count == null) {
            count = 1;
        } else {
            count++;
        }
        statusMap.put(status, count);
        timeMap.put(time, statusMap);
    }


}