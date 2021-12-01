import person.Person;

import area.Area;
import points.Points;
import population.Controller;
import population.GeneratePopulation;
import population.Population;

import java.util.Map;
import java.util.Random;
import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        Area area = new Area(40, 25);
        Population population = GeneratePopulation.generateNotResistPopulation(30, area);

        Controller controller = new Controller();
        int counter = 0;
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(850, 850);
        f.setLocation(200, 200);
        f.setResizable(false);
        f.setVisible(true);
        Points painter = new Points(population.getPopulation().toString());

        controller.prepareSimulation(population);
        while (true) {
            Thread.sleep(40);
            counter++;
            for (int i = 0; i < 25; i++) {
                population.getInfected().forEach(Person::handle);
                painter.updateList(population.getPopulation().toString());
                f.add(painter);
                f.revalidate();
                f.repaint();
                for (int j = 0; j < population.getInfected().size(); j++) {
                    Person person = population.getInfected().get(j);
                    person.clearParams(population);
                    person.getDistances(population.getNotInfected());
                    person.getTimes(population.getNotInfected());

                    Map<String, Integer> times = person.getPersonData().getTimes();

                    for (String key : times.keySet()) {
                        if (times.get(key) >= 75) {
                            population.getIndividual(key).handle(person);
                        }
                    }
                }
                population.getPopulation().forEach(individual1 -> individual1.generatePosition(area, random));
                population.deleteIfExited();
                if (counter % 7 == 0) {
                    population.addIndividual(GeneratePopulation.getNotResistIndividual(area));
                }
            }
            System.out.print(counter + " - ");
            System.out.println("Infected: " + population.getInfected().size() + " " + "\tNot infected without resist: " + (population.getNotInfected().size() - population.getResist().size()) + " " + "\tWith resist: " + population.getResist().size());
            if (counter == 10000) {
                break;
            }
            if (population.getInfected().size() == 0) {
                break;
            }
        }
        System.out.println("FIN - Infected: " + population.getInfected().size() + " " + "\tNot infected without resist: " + (population.getNotInfected().size() - population.getResist().size()) + " " + "\tWith resist: " + population.getResist().size());
    }
}
