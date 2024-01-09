package agh.ics.oop.model;

import java.util.HashMap;

public class Stats {
    private Globe map;
    protected HashMap<Vector2d, Integer> aliveAnimalsOnSpot;
    protected HashMap<Vector2d, Integer> diedAnimalsOnSpot;

    public Stats(Parameters parameters) {
        this.map = map;
        for (int x = 0; x < parameters.getMapWidth(); x++) {
            for (int y = 0; y < parameters.getMapHeight(); y++) {
                diedAnimalsOnSpot.put(new Vector2d(x, y), 0);
                aliveAnimalsOnSpot.put(new Vector2d(x, y), 0);
            }
        }
    }

}
