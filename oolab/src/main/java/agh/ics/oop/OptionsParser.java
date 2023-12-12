package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {
        List<MoveDirection> validDirections = new LinkedList<>();
        for (String direct: args) {
            switch (direct) {
                case "f" -> validDirections.add(MoveDirection.FORWARD);
                case "b" -> validDirections.add(MoveDirection.BACKWARD);
                case "l" -> validDirections.add(MoveDirection.LEFT);
                case "r" -> validDirections.add(MoveDirection.RIGHT);
                default -> throw new IllegalArgumentException(direct.toString() + " is not legal move specification");
            }
        }
        return validDirections;
    }
}
