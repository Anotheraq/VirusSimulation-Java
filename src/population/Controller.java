package population;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import person.Person;

public class Controller {


    public void prepareSimulation(final Population population) {
        for (int j = 0; j < population.getInfected().size(); j++) {
            Person person = population.getInfected().get(j);
            List<Person> comparedPeople = new LinkedList<>(population.getPossibleInfected());
            Map<String, Double> distances = person.getDistances(comparedPeople);
            Map<String, Integer> times = person.getTimes(comparedPeople);

            person.setPersonData(new PersonData(distances, times));
        }
    }
}
