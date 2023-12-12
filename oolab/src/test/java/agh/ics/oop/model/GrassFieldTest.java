package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    @Test
    public void GrassFieldTest1() {
        int grass = 10;
        Random seed = new Random(123);
        String[] moves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> directions_list = OptionsParser.parse(moves);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        List<Vector2d> positions_list = List.of(positions);
        GrassField grassField = new GrassField(grass, seed);
        Simulation simulation = new Simulation(grassField, positions_list, directions_list);
        simulation.run();
        assertTrue(grassField.objectAt(new Vector2d(6,9)) instanceof Grass);
        assertFalse(grassField.objectAt(new Vector2d(2,0)) instanceof Grass);
        assertTrue(grassField.canMoveTo(new Vector2d(0,0)));
        assertFalse(grassField.canMoveTo(new Vector2d(2,0)));
        assertTrue(grassField.canMoveTo(new Vector2d(6,9)));
    }
    @Test
    public void GrassFieldTest2() {
        int grass = 10;
        Random seed = new Random(2137);
        Vector2d[] positions = {new Vector2d(0,0), new Vector2d(2, 2), new Vector2d(3, 4)};
        GrassField grassField = new GrassField(grass, seed);
        String[] moves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<Vector2d> positions_list = List.of(positions);
        List<MoveDirection> directions_list = OptionsParser.parse(moves);
        Simulation simulation = new Simulation(grassField, positions_list, directions_list);
        simulation.run();
        List<Animal> animals = simulation.getAnimals();
        assertTrue(grassField.objectAt(new Vector2d(0,4)) instanceof Animal);
        assertFalse(grassField.objectAt(new Vector2d(2,0)) instanceof Animal);
        assertEquals(animals.get(0), grassField.objectAt(new Vector2d(0,4)));
        assertNull(grassField.objectAt(new Vector2d(0,1)));
        assertTrue(grassField.isOccupied(new Vector2d(0,0)));
        assertTrue(grassField.objectAt(new Vector2d(0,0)) instanceof Grass);
        assertTrue(grassField.isOccupied(new Vector2d(7,4)));
        assertFalse(grassField.isOccupied(new Vector2d(0,9)));
    }
}
