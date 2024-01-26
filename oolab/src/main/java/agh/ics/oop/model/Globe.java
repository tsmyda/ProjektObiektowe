package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;

import javafx.util.Pair;

public class Globe implements Map {
    private int height;
    private int width;
    private BehaviourType behaviour;
    private HashMap<Vector2d, Grass> grassOnMap = new HashMap<>();
    private HashMap<Vector2d, Integer> betterGrowth = new HashMap<Vector2d, Integer>();
    private ArrayList<Animal> animalsOnMap = new ArrayList<>();
    private ArrayList<Animal> diedAnimals = new ArrayList<>();
    private SimulationParameters parameters;
    private Stats stats;

    public Globe(SimulationParameters parameters) {
        this.height = parameters.getMapHeight();
        this.width = parameters.getMapWidth();
        this.behaviour = parameters.getBehaviourType();
        this.parameters = parameters;
        this.stats = new Stats(parameters, this);
        for (int i = 0; i < parameters.getAnimalNumber(); i++) {
            Animal animal = new Animal(parameters);
            placeAnimal(animal);
        }
        Random generator = new Random();
        int i = 0;
        while (i < parameters.getGrassNumber()) {
            int x = generator.nextInt(width - 1);
            int y = generator.nextInt(height - 1);
            if (!grassOnMap.containsKey(new Vector2d(x, y))) {
                placeGrass(new Grass(new Vector2d(x, y)));
                i++;
            }
        }
    }

    public void grassEat() {
        ArrayList<Animal> winnerToEat = new ArrayList<>();
        for (Vector2d position : grassOnMap.keySet()) {
            ArrayList<Animal> animalsOnSpot = new ArrayList<>();
            for (Animal animal : animalsOnMap) {
                if (animal.getPosition().equals(position)) {
                    animalsOnSpot.add(animal);
                }
            }
            if (!animalsOnSpot.isEmpty()) {
                animalsOnSpot.sort(Comparator.<Animal>comparingInt(el -> -el.energy).thenComparingInt(el -> -el.age).thenComparingInt(el -> -el.children));
                Animal winner = animalsOnSpot.get(0);
                winnerToEat.add(winner);
            }
        }
        for (Animal animal : winnerToEat) {
            animal.eat(parameters);
            grassOnMap.remove(animal.getPosition());
        }
    }

    public void breed() {
        for (Vector2d position : stats.aliveAnimalsOnSpot.keySet()) {
            if (stats.aliveAnimalsOnSpot.get(position) == 0 || stats.aliveAnimalsOnSpot.get(position) == 1) continue;
            ArrayList<Animal> animalsOnSpot = new ArrayList<>();
            for (Animal animal : animalsOnMap) {
                if (animal.getPosition().equals(position)) {
                    animalsOnSpot.add(animal);
                }
            }
            animalsOnSpot.sort(Comparator.<Animal>comparingInt(el -> -el.energy).thenComparingInt(el -> -el.age).thenComparingInt(el -> -el.children));
            ArrayList<Animal> newChildren = new ArrayList<>();
            for (int i = 1; i < stats.aliveAnimalsOnSpot.get(position); i += 2) {
                Animal mom = animalsOnSpot.get(i);
                Animal dad = animalsOnSpot.get(i - 1);
                if (mom.getEnergy() < parameters.getCopulationEnergy()) break;
                Genes childGenes = new Genes(mom, dad, parameters);
                mom.children += 1;
                dad.children += 1;
                mom.energy -= parameters.getCopulationEnergy();
                dad.energy -= parameters.getCopulationEnergy();
                Animal child = new Animal(childGenes.childGenes, parameters);
                mom.childrenList.add(child);
                dad.childrenList.add(child);
                newChildren.add(child);
            }
            for (int i = 0; i < newChildren.size(); i++) {
                placeAnimal(newChildren.get(i));
            }
        }
    }

    public void placeAnimal(Animal animal) {
        animalsOnMap.add(animal);
        stats.aliveAnimalsOnSpot.put(animal.getPosition(), stats.aliveAnimalsOnSpot.get(animal.getPosition()) + 1);
        if (stats.genomes.containsKey(animal.genomeToString())) {
            stats.genomes.put(animal.genomeToString(), stats.genomes.get(animal.genomeToString()) + 1);
        } else stats.genomes.put(animal.genomeToString(), 0);
    }

    protected void removeAnimal(Animal animal) {
        animalsOnMap.remove(animal);
    }

    public void placeGrass(Grass grass) {
        grassOnMap.put(grass.getPosition(), grass);
    }

    @Override
    public void move(Animal animal) {
        if (animal.energy >= parameters.getMoveEnergy()) {
            animal.energy -= parameters.getMoveEnergy();
            stats.aliveAnimalsOnSpot.put(animal.getPosition(), stats.aliveAnimalsOnSpot.get(animal.getPosition()) - 1);
            Vector2d newPosition = animal.getPosition().add(animal.genes[animal.activeGeneIndex].toUnitVector());
            if (newPosition.getY() < 0 || newPosition.getY() >= height) {
                animal.orientation = animal.orientation.opposite();
            } else if (newPosition.getX() < 0) {
                animal.position = new Vector2d(width - 1, animal.position.getY());
            } else if (newPosition.getX() >= width) {
                animal.position = new Vector2d(0, animal.position.getY());
            } else {
                animal.position = newPosition;
            }
            stats.aliveAnimalsOnSpot.put(animal.getPosition(), stats.aliveAnimalsOnSpot.get(animal.getPosition()) + 1);
            behaviour.nextGene(animal);
            animal.age += 1;
        } else {
            animal.isAlive = false;
            stats.diedAnimalsOnSpot.put(animal.getPosition(), stats.diedAnimalsOnSpot.get(animal.getPosition()) + 1);
            stats.aliveAnimalsOnSpot.put(animal.getPosition(), stats.aliveAnimalsOnSpot.get(animal.getPosition()) - 1);
            betterGrowth.put(animal.getPosition(), 3);
            diedAnimals.add(animal);
        }
    }

    public boolean isGrass(Vector2d spot) {
        return grassOnMap.containsKey(spot);
    }

    public boolean isAnimal(Vector2d spot) {
        return stats.aliveAnimalsOnSpot.get(spot) > 0;
    }

    public Animal getAnimalOnSpot(Vector2d spot) {
        for (Animal animal : animalsOnMap) {
            if (animal.getPosition().equals(spot)) {
                return animal;
            }
        }
        return null;
    }

    public ArrayList<Animal> getAnimalsOnMap() {
        return animalsOnMap;
    }

    public HashMap<Vector2d, Grass> getGrassOnMap() {
        return grassOnMap;
    }

    public Stats getStats() {
        return stats;
    }

    public ArrayList<Animal> getDiedAnimals() {
        return diedAnimals;
    }

    public HashMap<Vector2d, Integer> getBetterGrowth() {
        return betterGrowth;
    }

    public SimulationParameters getParameters() {
        return parameters;
    }
}
