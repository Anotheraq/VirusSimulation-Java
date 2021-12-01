package population;

import person.Id;
import person.Person;
import area.Area;
import states.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import static states.Constants.*;


public class GeneratePopulation {


    private static final Random random = new Random();

    public static Population generateNotResistPopulation(final int numerous, final Area area) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < numerous; i++) {
            people.add(getNotResistIndividual(area));
        }
        return new Population(people);
    }

    public static Person getNotResistIndividual(final Area area) {
        IState iState;
        if (random.nextInt(PROBABILITY_OF_ILL) != 0) {
            iState = new Healthy();
        } else if (random.nextInt(PROBABILITY_OF_SYMPTOMS) == 0) {
            iState = new HaveSymptoms();
        } else {
            iState = new HaveNoSymptoms();
        }

        return getIndividual(area, iState);
    }

    private static Person getIndividual(final Area area, final IState iState) {
        double x = 0;
        double y = 0;
        if (random.nextInt(PROBABILITY_OF_ENTRY) == 0) {
            x = random.nextDouble() * area.getWidth();
        } else {
            y = random.nextDouble() * area.getHeight();
        }
        LinkedHashMap<String, Double> distances = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> times = new LinkedHashMap<>();
        PersonData personData = new PersonData(distances, times);
        return new Person(Id.createID(), iState, personData, true, x, y);
    }
}
