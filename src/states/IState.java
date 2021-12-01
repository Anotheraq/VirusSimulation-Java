package states;

import person.Person;

public interface IState {
    void handle(final Person person);
    String getName();
}