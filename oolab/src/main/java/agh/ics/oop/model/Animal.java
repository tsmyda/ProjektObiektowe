package agh.ics.oop.model;

public class Animal implements WorldElement{
    private static final Vector2d DEFAULT_POSITION = new Vector2d(2,2);
    private static final MapDirection DEFAULT_ORIENTATION = MapDirection.NORTH;
    private static final Vector2d LEFT_EDGE = new Vector2d(0,0);
    private static final Vector2d RIGHT_EDGE = new Vector2d(4,4);
    private Vector2d position;
    private MapDirection orientation;
    public Animal(Vector2d position) {
        this.orientation = DEFAULT_ORIENTATION;
        this.position = position;
    }
    public String toString() {
        return switch (orientation) {
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
        };
    }
    public boolean isAt(Vector2d position) {
        return position.equals(this.position);
    }
    public void move(MoveValidator validator, MoveDirection direction) {
        Vector2d new_position = position;
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT ->  orientation = orientation.previous();
            case FORWARD -> new_position = position.add(orientation.toUnitVector());
            case BACKWARD -> new_position = position.subtract(orientation.toUnitVector());
        }
        if (validator.canMoveTo(new_position)) {
            this.position = new_position;
        }
    }
    public Vector2d getPosition() {
        return position;
    }
    public MapDirection getOrientation() {
        return orientation;
    }
}

