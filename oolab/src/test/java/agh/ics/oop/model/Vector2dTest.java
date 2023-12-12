package agh.ics.oop.model;

import agh.ics.oop.model.Vector2d;
import org.junit.Test;

import static org.junit.Assert.*;

public class Vector2dTest {
    @Test
    public void testEquals() {
        Vector2d v1 = new Vector2d(3, 2);
        Vector2d v2 = new Vector2d(3, 2);
        Vector2d v3 = new Vector2d(2, 3);
        Integer number = 420;
        assertEquals(v1, v2);
        assertEquals(v1, v1);
        assertNotEquals(v1, v3);
        assertNotEquals(null, v1);
        assertNotEquals(v1, number);
    }
    @Test
    public void testToString() {
        Vector2d v1 = new Vector2d(3, 2);
        Vector2d v2 = new Vector2d(-3, 2);
        assertEquals(v1.toString(), "(3,2)");
        assertEquals(v2.toString(), "(-3,2)");
    }
    @Test
    public void testPrecedes() {
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d v2 = new Vector2d(2, 2);
        Vector2d v3 = new Vector2d(2, 3);
        assertTrue(v1.precedes(v2));
        assertTrue(v1.precedes(v3));
        assertTrue(v2.precedes(v3));
        assertTrue(v2.precedes(v2));
        assertFalse(v3.precedes(v1));
        assertFalse(v3.precedes(v2));
    }
    @Test
    public void testFollows() {
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d v2 = new Vector2d(2, 2);
        Vector2d v3 = new Vector2d(2, 3);
        assertTrue(v3.follows(v2));
        assertTrue(v3.follows(v1));
        assertTrue(v2.follows(v1));
        assertTrue(v1.follows(v1));
        assertFalse(v1.follows(v3));
        assertFalse(v1.follows(v2));
    }
    @Test
    public void testUpperRight() {
        Vector2d v1 = new Vector2d(2, 1);
        Vector2d v2 = new Vector2d(1, 2);
        Vector2d v3 = new Vector2d(-1, -3);
        assertEquals(v1.upperRight(v2), new Vector2d(2,2));
        assertEquals(v1.upperRight(v3), v1);
        assertEquals(v3.upperRight(v3), v3);
    }
    @Test
    public void testLowerLeft() {
        Vector2d v1 = new Vector2d(2, 1);
        Vector2d v2 = new Vector2d(1, 2);
        Vector2d v3 = new Vector2d(-1, -3);
        assertEquals(v1.lowerLeft(v2), new Vector2d(1,1));
        assertEquals(v1.lowerLeft(v3), v3);
        assertEquals(v3.lowerLeft(v3), v3);
    }
    @Test
    public void testAdd() {
        Vector2d v1 = new Vector2d(2,1);
        Vector2d v2 = new Vector2d(1, 2);
        Vector2d v3 = new Vector2d(-7, 4);
        Vector2d v4 = new Vector2d(0, 0);
        assertEquals(v1.add(v2), new Vector2d(3,3));
        assertEquals(v2.add(v3), new Vector2d(-6,6));
        assertEquals(v2.add(v4), v2);
        assertEquals(v2.add(v2), new Vector2d(2,4));
    }
    @Test
    public void testSubtract() {
        Vector2d v1 = new Vector2d(2,1);
        Vector2d v2 = new Vector2d(1, 2);
        Vector2d v3 = new Vector2d(-7, 4);
        Vector2d v4 = new Vector2d(0, 0);
        assertEquals(v1.subtract(v2), new Vector2d(1,-1));
        assertEquals(v2.subtract(v3), new Vector2d(8,-2));
        assertEquals(v2.subtract(v4), v2);
        assertEquals(v2.subtract(v2), v4);
    }
    @Test
    public void testOpposite() {
        Vector2d v1 = new Vector2d(-2,1);
        Vector2d v2 = new Vector2d(1, 2);
        Vector2d v3 = new Vector2d(0, 0);
        assertEquals(v1.opposite(), new Vector2d(2,-1));
        assertEquals(v2.opposite(), new Vector2d(-1,-2));
        assertEquals(v3.opposite(), v3);
    }
}
