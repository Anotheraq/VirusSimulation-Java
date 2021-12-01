package population;

import java.util.Map;

public class PersonData {
    private Map<String, Double> distances;
    private Map<String, Integer> times;

    public PersonData(Map<String, Double> distances, Map<String, Integer> times){
        this.distances = distances;
        this.times = times;
    }

    public Map<String, Double> getDistances() {
        return distances;
    }

    public Map<String, Integer> getTimes() {
        return times;
    }
}
