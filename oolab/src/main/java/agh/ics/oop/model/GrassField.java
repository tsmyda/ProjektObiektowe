package agh.ics.oop.model;

import java.util.*;


public class GrassField extends AbstractWorldMap {
    private final int grassNumber;
    private final Map<Vector2d, Grass> grass = new HashMap<>();
    public GrassField(int n) {
        this(n, new Random());
    }
    public GrassField(int n, Random seed) {
        super();
        this.grassNumber = n;
        int counter = 0;
        int range = (int) Math.sqrt(10*n);
        while (counter < this.grassNumber) {
            int x = seed.nextInt(range);
            int y = seed.nextInt(range);
            if (!grass.containsKey(new Vector2d(x, y))) {
                grass.put(new Vector2d(x, y), new Grass(new Vector2d(x, y)));
                counter += 1;
            }
        }
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return (!isOccupied(position) || objectAt(position) instanceof Grass) && position.follows(lowerLeftCorner) && position.precedes(upperRightCorner);
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position) || grass.containsKey(position);
    }

    @Override
    public Boundary getCurrentBounds() {
        Vector2d lowerVisLeftCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d upperVisRightCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Map.Entry<Vector2d, Animal> set : animals.entrySet()) {
            Vector2d position = set.getKey();
            lowerVisLeftCorner = lowerVisLeftCorner.lowerLeft(position);
            upperVisRightCorner = upperVisRightCorner.upperRight(position);
        }

        for (Map.Entry<Vector2d, Grass> set : grass.entrySet()) {
            Vector2d position = set.getKey();
            lowerVisLeftCorner = lowerVisLeftCorner.lowerLeft(position);
            upperVisRightCorner = upperVisRightCorner.upperRight(position);
        }
        return new Boundary(lowerVisLeftCorner, upperVisRightCorner);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        Animal animal = (Animal) super.objectAt(position);
        if (animal != null) {
            return animal;
        }
        return grass.get(position);
    }
    @Override
    public Map<Vector2d, WorldElement> getElements() {
        Map<Vector2d, WorldElement> elements = new HashMap<>();
        for (Map.Entry<Vector2d, Grass> set : grass.entrySet()) {
            Vector2d position = set.getKey();
            elements.put(position, set.getValue());
        }
        elements.putAll(super.getElements());
        return elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrassField that = (GrassField) o;
        return grassNumber == that.grassNumber && Objects.equals(grass, that.grass) && Objects.equals(lowerLeftCorner, that.lowerLeftCorner) && Objects.equals(upperRightCorner, that.upperRightCorner) && Objects.equals(visualizer, that.visualizer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grassNumber, grass, lowerLeftCorner, upperRightCorner, visualizer);
    }
}
