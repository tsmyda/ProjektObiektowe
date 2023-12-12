package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartował");
        ConsoleMapDisplay observer = new ConsoleMapDisplay();
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(0, 0), new Vector2d(2, 2), new Vector2d(3, 4));
        Simulation[] simulations = new Simulation[1000];

        for (int i=0; i<1000; i++) {
            AbstractWorldMap map;
            if (i%2==0) map = new GrassField(10);
            else map = new RectangularMap(10,10);
            map.addObserver(observer);
            simulations[i] = new Simulation(map, positions, directions);
        }
        SimulationEngine engine = new SimulationEngine(simulations);
        engine.runAsyncInThreadPool();
        try {
            engine.awaitSimulationsEnd();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("System zakończył działanie");
    }

    private static void run(MoveDirection[] directions) {
        for(MoveDirection argument: directions) {
            switch (argument) {
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    System.out.println("Zwierzak skręca w prawo");
                    break;
                case LEFT:
                    System.out.println("Zwierzak skręca w lewo");
                    break;
                default:
                    System.out.println("Nieznana komenda");
            }
        }
    }
}
