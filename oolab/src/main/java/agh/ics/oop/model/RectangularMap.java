package agh.ics.oop.model;

import java.util.Map;

public class RectangularMap extends  AbstractWorldMap{
    public RectangularMap(int width, int height) {
        super(width, height);
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
        return new Boundary(lowerVisLeftCorner, upperVisRightCorner);
    }

}
