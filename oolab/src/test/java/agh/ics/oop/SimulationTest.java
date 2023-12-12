package agh.ics.oop;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SimulationTest {
    AbstractWorldMap map = new GrassField(5);
    @Test
    public void testOrientation1() {
        String[] moves = {"f", "b", "l"};
        List<MoveDirection> directions = OptionsParser.parse(moves);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(map, positions, directions);
        simulation.run();
        List<Animal> act_answer = simulation.getAnimals();
        Animal a1 = new Animal(new Vector2d(2,3));
        a1.move(map, MoveDirection.LEFT);
        Animal a2 = new Animal(new Vector2d(3,3));
        List <Animal> exp_answer = List.of(a1, a2);
        assertEquals(exp_answer.toString(), act_answer.toString());
    }
    @Test
    public void testOrientation2() {
        String[] moves = {"f", "f", "b", "l", "r"};
        List<MoveDirection> directions = OptionsParser.parse(moves);
        List<Vector2d> positions = List.of(new Vector2d(0,0), new Vector2d(3,1), new Vector2d(2,1), new Vector2d(0, 0));
        Simulation simulation = new Simulation(map, positions, directions);
        simulation.run();
        Animal a1 = new Animal(new Vector2d(0,1));
        a1.move(map, MoveDirection.LEFT);
        Animal a2 = new Animal(new Vector2d(3,2));
        a2.move(map, MoveDirection.RIGHT);
        Animal a3 = new Animal(new Vector2d(2,0));
        List <Animal> answer = List.of(a1, a2, a3);
        assertEquals(answer.toString(), simulation.getAnimals().toString());
    }
    @Test
    public void testRangeMap1() {
        String[] moves = {"b", "f"};
        List<MoveDirection> directions = OptionsParser.parse(moves);
        List<Vector2d> positions = List.of(new Vector2d(0,0), new Vector2d(4,4));
        Simulation simulation = new Simulation(map, positions, directions);
        simulation.run();
        Animal a1 = new Animal(new Vector2d(0,0));
        a1.move(map, MoveDirection.BACKWARD);
        Animal a2 = new Animal(new Vector2d(4,4));
        a1.move(map, MoveDirection.FORWARD);
        List <Animal> answer = List.of(a1, a2);
        assertEquals(answer.toString(), simulation.getAnimals().toString());
    }
    @Test
    public void testPlace() {
        String[] moves = {"f", "l", "f"};
        List<MoveDirection> directions = OptionsParser.parse(moves);
        List<Vector2d> positions = List.of(new Vector2d(0,0), new Vector2d(4,4));
        Simulation simulation = new Simulation(map, positions, directions);
        simulation.run();
        Animal a1 = new Animal(new Vector2d(0,0));
        Animal a2 = new Animal(new Vector2d(4,4));
        a1.move(map, MoveDirection.FORWARD);
        a2.move(map, MoveDirection.LEFT);
        a1.move(map, MoveDirection.FORWARD);
        List <Animal> answer = List.of(a1, a2);
        assertEquals(answer.toString(), simulation.getAnimals().toString());
    }
}
