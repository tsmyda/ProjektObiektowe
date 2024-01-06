package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Globe {
    private int height;
    private int width;
    private BehaviourType behaviour;
    private HashMap<Vector2d, Grass> grassOnMap = new HashMap<>();
    private HashMap<Vector2d, Integer> betterGrowth = new HashMap<Vector2d, Integer>();
    private ArrayList<Animal> animalsOnMap = new ArrayList<>();
    public Globe(int animalsNumber, int grassNumber, BehaviourType NormalBehaviour) {
        height = 10; //CONFIG
        width = 10; //CONFIG
        behaviour = NormalBehaviour; //CONFIG
        for (int i=0; i<animalsNumber; i++) {
            Animal animal = new Animal();
            placeAnimal(animal);
        }
        Random generator = new Random();
        int i = 0;
        while (i<grassNumber) {
            int x = generator.nextInt(width-1);
            int y = generator.nextInt(height-1);
            if (!grassOnMap.containsKey(new Vector2d(x, y))) {
                placeGrass(new Grass(new Vector2d(x, y)));
                i++;
            }
        }
    }

    public void placeAnimal(Animal animal) {
        animalsOnMap.add(animal);
    }
    public void placeGrass(Grass grass) {
        grassOnMap.put(grass.getPosition(), grass);
    }

    public void move(Animal animal) {
        if (animal.energy >= 10) {
            animal.energy -= 10;
            Vector2d newPosition = animal.getPosition().add(animal.genes[animal.activeGeneIndex].toUnitVector());
            if (newPosition.getY() < 0 || newPosition.getY() >= height) {
                animal.orientation = animal.orientation.opposite();
            }
            else if (newPosition.getX() < 0) {
                animal.position = new Vector2d(width-1, animal.position.getY());
            }
            else if (newPosition.getX() >= width) {
                animal.position = new Vector2d(0, animal.position.getY());
            }
            else {
                animal.position = newPosition;
            }
            behaviour.nextGene(animal);
        }
        else {
            animal.isAlive = false;
            betterGrowth.put(animal.getPosition(), 3);
            animalsOnMap.remove(animal);
        }
    }
}
