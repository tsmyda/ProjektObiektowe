package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int changes;
    public ConsoleMapDisplay() {
        this.changes = 0;
    }
    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        changes += 1;
        System.out.println("Zmiana nr "+changes+" "+message+" UUID mapy: "+ worldMap.getId());
        System.out.println(worldMap.toString());
    }
}
