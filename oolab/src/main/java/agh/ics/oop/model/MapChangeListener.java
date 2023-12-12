package agh.ics.oop.model;

import agh.ics.oop.World;

public interface MapChangeListener {
    void mapChanged(WorldMap worldMap, String message);
}
