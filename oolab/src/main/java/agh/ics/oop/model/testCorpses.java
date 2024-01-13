package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class testCorpses implements GrassGrowth{
    @Override
    public void grow(Globe map, SimulationParameters parameters) {
        ArrayList<Vector2d> groby = new ArrayList<>(map.getStats().getDiedAnimalsOnSpot().keySet());
        ArrayList<Vector2d> spots = new ArrayList<>();
        for (Vector2d spot: groby) {
            if (!map.isGrass(spot)) {
                spots.add(spot);
             }
        }
        spots.sort(new Comparator<Vector2d>() {
            @Override
            public int compare(Vector2d o1, Vector2d o2) {
                return map.getStats().getDiedAnimalsOnSpot().get(o2)-map.getStats().getDiedAnimalsOnSpot().get(o1);
            }
        });
        int grassNum = 0;
        Random generator = new Random();
        while (grassNum < parameters.getDailyGrassGrowth()) {
            if (generator.nextInt(10)<2) {

            }
        }
    }
}
