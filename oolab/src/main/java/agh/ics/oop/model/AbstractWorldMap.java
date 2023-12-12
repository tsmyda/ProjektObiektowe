package agh.ics.oop.model;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected int width;
    protected int height;
    private UUID id;
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    protected Vector2d lowerLeftCorner = new Vector2d(0,0);
    protected Vector2d upperRightCorner;
    protected MapVisualizer visualizer = new MapVisualizer(this);
    protected List<MapChangeListener> observers = new LinkedList<>();
    public AbstractWorldMap() {
        height = Integer.MAX_VALUE;
        width = Integer.MAX_VALUE;
        upperRightCorner = new Vector2d(width-1, height-1);
        id = UUID.randomUUID();
    }

    public AbstractWorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        upperRightCorner = new Vector2d(width-1, height-1);
        id = UUID.randomUUID();
    }
    @Override
    public void place(Animal animal) throws PositionAlreadyOccupiedExepction {
        Vector2d pos = animal.getPosition();
        if (canMoveTo(pos)) {
            animals.put(pos, animal);
            this.mapChanged("Dodano nowego zwierzaka na pozycje: " + pos.toString());
        }
        else{
            throw new PositionAlreadyOccupiedExepction(pos);
        }
    }
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) && position.follows(lowerLeftCorner) && position.precedes(upperRightCorner);
    }
    public void move(Animal animal, MoveDirection direction) {
        animals.remove(animal.getPosition());
        animal.move(this, direction);
        animals.put(animal.getPosition(), animal);
        if (direction==MoveDirection.FORWARD || direction==MoveDirection.BACKWARD) {
            this.mapChanged("Zwierzak poruszył się na pozycje: " + animal.getPosition().toString());
        }
        else {
            this.mapChanged("Zwierzak obrócił się w kierunku: "+direction.toString()+ " na pozycji " + animal.getPosition().toString());
        }
    }
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }
    abstract public Boundary getCurrentBounds();
    public String toString() {
        Boundary bounds = getCurrentBounds();
        return visualizer.draw(bounds.lowerLeft(),bounds.upperRight());
    }
    public Map<Vector2d, WorldElement> getElements() {
        Map<Vector2d, WorldElement> elements = new HashMap<>();
        for (Map.Entry<Vector2d, Animal> set : animals.entrySet()) {
            Vector2d position = set.getKey();
            elements.put(position, set.getValue());
        }
        return elements;
    }
    public void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }
    public void removeObserver(MapChangeListener observer) {
        observers.remove(observer);
    }
    private void mapChanged(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }
    public UUID getId() {
        return id;
    }
}
