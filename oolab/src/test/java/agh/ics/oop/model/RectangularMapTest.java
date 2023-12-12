package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    @Test
    public void RectangularMapTest1() {
        String[] moves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> directions_list = OptionsParser.parse(moves);
        AbstractWorldMap map = new RectangularMap(4, 8);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        List<Vector2d> positions_list = List.of(positions);
        Simulation simulation = new Simulation(map, positions_list, directions_list);
        simulation.run();
        List<Animal> animals = simulation.getAnimals();
        assertTrue(map.isOccupied(new Vector2d(2,0)));
        assertTrue(animals.get(0).isAt(new Vector2d(2, 0)));
        assertEquals(animals.get(1), map.objectAt(new Vector2d(3,7)));
        assertTrue(map.canMoveTo(new Vector2d(1,7)));
        assertFalse(map.canMoveTo(new Vector2d(10,10)));
    }
    @Test
    public void RectangularMapTest2() {
        String[] moves = {"f", "l", "f", "r", "f", "f", "f", "f", "f"};
        List<MoveDirection> directions_list = OptionsParser.parse(moves);
        AbstractWorldMap map = new RectangularMap(10, 10);
        Vector2d[] positions = {new Vector2d(1, 4), new Vector2d(3, 5), new Vector2d(2, 2)};
        List<Vector2d> positions_list = List.of(positions);
        Simulation simulation = new Simulation(map, positions_list, directions_list);
        simulation.run();
        List<Animal> animals = simulation.getAnimals();
        assertFalse(map.isOccupied(new Vector2d(2,0)));
        assertTrue(animals.get(0).isAt(new Vector2d(1, 5)));
        assertTrue(map.objectAt(new Vector2d(1,5)) instanceof Animal);
        assertEquals(animals.get(2), map.objectAt(new Vector2d(2,4)));
        assertTrue(map.canMoveTo(new Vector2d(1,7)));
        assertFalse(map.canMoveTo(new Vector2d(10,10)));
    }
}
