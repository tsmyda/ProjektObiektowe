package agh.ics.oop.model;

public enum Direction {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;
    public int toNumber(){ // enum ma metodę ordinal
        return switch(this) {
            case NORTH -> 0;
            case NORTHEAST -> 1;
            case EAST -> 2;
            case SOUTHEAST -> 3;
            case SOUTH -> 4;
            case SOUTHWEST -> 5;
            case WEST -> 6;
            case NORTHWEST -> 7;
        };
    }
    public static Direction numToDirection(int num){
        return values()[num];
    }
    public Direction next() {
        Direction[] values = Direction.values();
        return values[(this.ordinal()+1)%values.length];
    }
    public Direction previous() {
        Direction[] values = Direction.values();
        return values[(this.ordinal()+7)%values.length];
    }
    public Vector2d toUnitVector() {
        return switch(this) {
            case NORTH -> new Vector2d(0,1);  // bardzo by się opłacił parametr konstruktora
            case NORTHEAST -> new Vector2d(1,1);
            case EAST -> new Vector2d(1,0);
            case SOUTHEAST -> new Vector2d(1,-1);
            case SOUTH -> new Vector2d(0,-1);
            case SOUTHWEST -> new Vector2d(-1,-1);
            case WEST -> new Vector2d(-1,0);
            case NORTHWEST -> new Vector2d(-1,1);
        };
    }
    public Direction opposite() {
        Direction[] values = Direction.values();
        return values[(this.ordinal()+4)%values.length];
    }
}