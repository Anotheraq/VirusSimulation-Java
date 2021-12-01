package population;

import java.util.LinkedList;
import java.util.List;


import person.Person;

import states.*;


public class Population {
    private List<Person> population;

    public Population(List<Person> population){
        this.population = population;
    }

    public List<Person> getPopulation() {
        return population;
    }

    public void addIndividual(final Person person) {
        this.population.add(person);
    }


    public Person getIndividual(final String id) {
        for (Person person : population) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }

    public void deleteIfExited() {
        population.removeIf(individual -> !individual.getIsInRoom());
    }

    public List<Person> getInfected() {
        List<Person> people = new LinkedList<>();
        people.addAll(getHaveSymptoms());
        people.addAll(getHaveNotSymptoms());
        return people;
    }

    private List<Person> getHaveSymptoms() {
        return getIndividuals(new HaveSymptoms());
    }

    private List<Person> getHaveNotSymptoms() {
        return getIndividuals(new HaveNoSymptoms());
    }

    public List<Person> getNotInfected() {
        List<Person> people = new LinkedList<>();
        people.addAll(getResist());
        people.addAll(getHealthy());
        return people;
    }

    public List<Person> getResist() {
        return getIndividuals(new Resist());
    }

    private List<Person> getHealthy() {
        return getIndividuals(new Healthy());
    }

    public List<Person> getPossibleInfected() {
        return getHealthy();
    }

    private List<Person> getIndividuals(IState state) {
        List<Person> people = new LinkedList<>();
        for (Person person : population) {
            if (person.getState().equals(state)) {
                people.add(person);
            }
        }
        return people;
    }
}
