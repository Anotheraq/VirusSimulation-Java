package states;

import java.util.Objects;
import java.util.Random;
import static states.Constants.*;


import person.Person;

public class HaveSymptoms implements IState {
    private final String name = "haveSymptoms";
    private int illnessCounter = 0;

    @Override
    public void handle(final Person person) {
        int mod = this.getIllnessCounter() / 25;
        if (mod >= 20 && mod < 30) {
            if (new Random().nextInt(PROBABILITY_OF_RECOVER) == 0) {
                person.setState(new Resist());
            }
        } else if (mod >= 30) {
            person.setState(new Resist());
        }
        incIllnessCounter();
    }

    public int getIllnessCounter() {
        return this.illnessCounter;
    }

    public void incIllnessCounter() {
        this.illnessCounter++;
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
