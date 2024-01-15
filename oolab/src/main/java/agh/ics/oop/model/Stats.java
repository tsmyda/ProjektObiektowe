package agh.ics.oop.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.util.Pair;
public class Stats {
    private Globe map;
    protected HashMap<Vector2d, Integer> aliveAnimalsOnSpot = new HashMap<>();
    protected HashMap<Vector2d, Integer> diedAnimalsOnSpot = new HashMap<>();
    protected HashMap<String, Integer> genomes=new HashMap<String, Integer>();

    public Stats(SimulationParameters parameters, Globe map) {
        this.map = map;
        for (int x = 0; x < parameters.getMapWidth(); x++) {
            for (int y = 0; y < parameters.getMapHeight(); y++) {
                diedAnimalsOnSpot.put(new Vector2d(x, y), 0);
                aliveAnimalsOnSpot.put(new Vector2d(x, y), 0);
            }
        }
    }
    public int getEmptySpots() {
        AtomicInteger empty = new AtomicInteger();
        aliveAnimalsOnSpot.forEach((key, value) -> {
            if (value==0) {
                empty.addAndGet(1);
            }
        });
        return empty.get();
    }
    public int getAnimalsNum() {
        return map.getAnimalsOnMap().size();
    }
    public int getGrassNum() {
        return map.getGrassOnMap().size();
    }

    public int getAverageEnergy(){
        if (map.getAnimalsOnMap().size() == 0){
            return 0;
        }
        int sum = 0;
        for(Animal animal : map.getAnimalsOnMap()){
            sum += animal.energy;
        }

        return sum / map.getAnimalsOnMap().size();
    }
    public int getAverageDeathDay(){
        if (map.getDiedAnimals().isEmpty()) return 0;
        int sum = 0;
        for (Animal animal : map.getDiedAnimals()){
            sum += animal.deathDay;
        }

        return sum / map.getDiedAnimals().size();
    }
    public String getMostCommonGene(){
        if (!genomes.isEmpty()){
            Integer maxVal= Collections.max(genomes.values());
            for (String genes: genomes.keySet()){
                if (genomes.get(genes).equals(maxVal)){
                    return genes;
                }
            }
        }
        return "";
    }
    public Integer getCountOfSpring(Animal animal){
        int offspringCount = 0;

        for (Animal child : animal.getChildrenList()) {
            if(child.getVisited() == 0) {
                child.visited = 1;
                offspringCount += 1 + getCountOfSpring(child);
            }
        }
        return offspringCount;
    }
    public void reset(Animal animal){
        animal.visited = 0;
        for (Animal child : animal.getChildrenList()) {
            reset(child);
        }
    }
    public HashMap<Vector2d, Integer> getDiedAnimalsOnSpot() {
        return diedAnimalsOnSpot;
    }
}
