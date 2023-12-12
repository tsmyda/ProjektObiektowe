package agh.ics.oop.model;

public enum MapDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;
    public String toString(){
        return switch(this) {
            case NORTH -> "^";
            case SOUTH -> "v";
            case EAST -> ">";
            case WEST -> "<";
        };
    }
    public MapDirection next() {
        MapDirection[] values = MapDirection.values();
        return values[(this.ordinal()+1)%values.length];
    }
    public MapDirection previous() {
        MapDirection[] values = MapDirection.values();
        return values[(this.ordinal()+3)%values.length];
    }
    public Vector2d toUnitVector() {
        return switch(this) {
            case NORTH -> new Vector2d(0,1);
            case EAST -> new Vector2d(1,0);
            case SOUTH -> new Vector2d(0,-1);
            case WEST -> new Vector2d(-1,0);
        };
    }
}
