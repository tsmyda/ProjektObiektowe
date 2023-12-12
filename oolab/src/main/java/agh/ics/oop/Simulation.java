package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.*;

public class Simulation implements Runnable {
    private final List<MoveDirection> moves;
    private final List<Animal> animals = new LinkedList<>();
    private final AbstractWorldMap map;
    public Simulation(AbstractWorldMap map, List<Vector2d> positions, List<MoveDirection> moves) {
        this.moves = moves;
        this.map = map;
        for (Vector2d position : positions) {
            Animal newAnimal = new Animal(position);
            try {
                map.place(newAnimal);
                animals.add(newAnimal);
            } catch (PositionAlreadyOccupiedExepction e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void run() {
        int currAnimalInd = 0;
        int n = animals.size();
        for (int i=0; i<moves.size(); i++) {
            Animal animal = animals.get(i % animals.size());
            map.move(animal, moves.get(i));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException();
            }
        }
    }
    public List<Animal> getAnimals() {
        return animals;
    }
}
