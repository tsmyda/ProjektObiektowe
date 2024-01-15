package agh.ics.oop.model;

import java.lang.reflect.Parameter;
import java.util.ArrayList;

public interface GrassGrowth {
    public ArrayList<Vector2d> fertileSoil = new ArrayList<>();
    public void grow(Globe map, SimulationParameters parameters);
    public boolean isFertileSoil(Vector2d spot);
}
