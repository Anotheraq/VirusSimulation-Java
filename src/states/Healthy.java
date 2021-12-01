package states;

import person.Person;

import java.util.Objects;
import java.util.Random;


import static states.Constants.PROBABILITY_OF_SYMPTOMS;

public class Healthy implements IState {

    private final String name = "healthy";

    @Override
    public void handle(final Person person) {
        if (!person.getState().equals(new HaveNoSymptoms()) || !person.getState().equals(new HaveNoSymptoms())) {
            if (new Random().nextInt(PROBABILITY_OF_SYMPTOMS) == 0) {
                person.setState(new HaveSymptoms());
            } else {
                person.setState(new HaveNoSymptoms());
            }
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IState that = (IState) o;
        return Objects.equals(this.getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
