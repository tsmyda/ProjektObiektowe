package agh.ics.oop;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.model.MoveDirection;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class OptionsParserTest {
    @Test
    public void testParse() {
        String[] array1 = {"f", "x","rr", " ", "b", "l ", "l", "r"};
        String[] array2 = {"B", "F","D"};
        String[] array3 = {"hello world", "b", "x","f ", "@", "l"};
        String[] array4= {"f", "b", "l", "r"};
        String[] array5= {};
        MoveDirection[] arrayAns4 = {MoveDirection.FORWARD,MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT};
        MoveDirection[] arrayAns5 = {};
        List<MoveDirection> listAns4 = Arrays.stream(arrayAns4).toList();
        List<MoveDirection> listAns5 = Arrays.stream(arrayAns5).toList();
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(array1));
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(array2));
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(array3));
        assertEquals(listAns4, OptionsParser.parse(array4));
        assertEquals(listAns5, OptionsParser.parse(array5));

    }
}
