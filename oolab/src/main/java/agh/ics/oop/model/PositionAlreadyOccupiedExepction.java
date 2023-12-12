package agh.ics.oop.model;

public class PositionAlreadyOccupiedExepction extends Exception{
    public PositionAlreadyOccupiedExepction(Vector2d position) {
        super("Position " + position.toString() + " is already occupied");
    }
}
