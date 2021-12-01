package person;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import population.PersonData;
import population.Population;
import states.HaveNoSymptoms;
import states.IState;
import vector.IVector;
import vector.Vector2D;
import area.Area;
import static states.Constants.*;




public class Person {

    private String id;
    private IState state;
    private PersonData personData;

    private Boolean isInRoom;

    private double positionX;
    private double positionY;

    public Person(String id, IState state, PersonData personData, Boolean isInRoom, double positionX, double positionY){
        this.id = id;
        this.state = state;
        this.personData = personData;
        this.isInRoom = isInRoom;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    @Override
    public String toString() {
        return state.getName() + "-" + positionX + ";" + positionY;
    }

    public void setPosition(final IVector iVector) {
        double[] components = iVector.getComponents();
        this.positionX += components[0];
        this.positionY += components[1];
    }
    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public void setPersonData(PersonData personData) {
        this.personData = personData;
    }

    public void setIsInRoom(Boolean inRoom) {
        isInRoom = inRoom;
    }

    public void setState(final IState state) {
        this.state = state;
    }

    public IState getState() {
        return state;
    }

    public Boolean getIsInRoom() {
        return isInRoom;
    }

    public PersonData getPersonData() {
        return personData;
    }

    public String getId() {
        return id;
    }

    public double getPositionY() {
        return positionY;
    }

    public double getPositionX() {
        return positionX;
    }



    public void generatePosition(final Area area, final Random random) {
        double x = random.nextDouble() * MAX_DISTANCE;
        double y = random.nextDouble() * (MAX_DISTANCE - x);
        if (random.nextInt(PROBABILITY_OF_NEGATIVE) == 0) {
            x *= -1;
            y *= -1;
        }

        IVector iVector = new Vector2D(x, y);
        this.setPosition(iVector);
        if (getPositionX() > area.getWidth()) {
            if (random.nextInt(PROBABILITY_OF_RETURN) == 0) {
                this.setPositionX(getPositionX() - 1);
            } else {
                this.setIsInRoom(false);
            }
        } else if (getPositionX() < 0) {
            if (random.nextInt(PROBABILITY_OF_RETURN) == 0) {
                this.setPositionX(getPositionX() + 1);
            } else {
                this.setIsInRoom(false);
            }
        } else if (getPositionY() > area.getHeight()) {
            if (random.nextInt(PROBABILITY_OF_RETURN) == 0) {
                this.setPositionY(getPositionY() - 1);
            } else {
                this.setIsInRoom(false);
            }
        } else if (getPositionY() < 0) {
            if (random.nextInt(PROBABILITY_OF_RETURN) == 0) {
                this.setPositionY(getPositionY() + 1);
            } else {
                this.setIsInRoom(false);
            }
        }
    }



    public void handle() {
        this.state.handle(this);
    }

    public void handle(final Person person) {
        if (person.getState().equals(new HaveNoSymptoms())) {
            if (new Random().nextInt(PROBABILITY_OF_INFECT) == 0) {
                this.handle();
            }
        } else {
            this.handle();
        }
    }

    public double getDistance(final Person person) {
        return Math.sqrt(Math.pow(person.getPositionX() - this.getPositionX(), 2) + Math.pow(person.
                getPositionY() - this.getPositionY(), 2));
    }

    public Map<String, Double> getDistances(final List<Person> people) {
        Map<String, Double> distances = personData.getDistances();
        for (Person person : people) {
            distances.put(person.getId(), this.getDistance(person));
        }
        return distances;
    }

    public Map<String, Integer> getTimes(final List<Person> people) {
        Map<String, Integer> times = personData.getTimes();
        Map<String, Double> distances = personData.getDistances();
        for (Person person : people) {
            if (times.get(person.getId()) == null) {
                times.put(person.getId(), 0);
            } else if (distances.get(person.getId()) <= 2) {
                int currentTime = times.get(person.getId());
                times.put(person.getId(), ++currentTime);
            } else {
                times.put(person.getId(), 0);
            }
        }
        return times;
    }

    public void clearParams(final Population population) {
        Map<String, Integer> times = getPersonData().getTimes();
        Map<String, Double> distances = getPersonData().getDistances();
        times.keySet().removeIf(s -> population.getIndividual(s) == null);
        distances.keySet().removeIf(s -> population.getIndividual(s) == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person that = (Person) o;
        return Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
