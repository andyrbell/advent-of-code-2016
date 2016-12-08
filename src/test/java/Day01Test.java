import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by abell on 06/12/16.
 */
public class Day01Test {
    public static final Day01.Position ORIGIN_SOUTH = Day01.Position.of(0, 0, Day01.Orientation.SOUTH);
    public static final Day01.Position ORIGIN_EAST = Day01.Position.of(0, 0, Day01.Orientation.EAST);
    public static final Day01.Position ORIGIN_WEST = Day01.Position.of(0, 0, Day01.Orientation.WEST);

//    For example:
//
//    Following R2, L3 leaves you 2 blocks East and 3 blocks North, or 5 blocks away.
//    R2, R2, R2 leaves you 2 blocks due South of your starting position, which is 2 blocks away.
//    R5, L5, R5, R3 leaves you 12 blocks away.

    @Test
    public void rectilinearDistanceExample1() throws Exception {
        Day01.Position destination = Day01.Position.of(2, 3, Day01.Orientation.NORTH);
        assertEquals(5, Day01.rectilinearDistance(destination));
    }
    @Test
    public void rectilinearDistanceExample2() throws Exception {
        Day01.Position destination = Day01.Position.of(0, -2, Day01.Orientation.WEST);
        assertEquals(2, Day01.rectilinearDistance(destination));
    }
    @Test
    public void rectilinearDistanceExample3() throws Exception {
        Day01.Position destination = Day01.Position.of(10, 2, Day01.Orientation.SOUTH);
        assertEquals(12, Day01.rectilinearDistance(destination));
    }

    @Test
    public void moveLeftFromOriginNorth() throws Exception {
        Day01.Position newPosition = Day01.move(Day01.ORIGIN, Day01.Move.of(Day01.Direction.LEFT, 5));

        assertEquals(-5, newPosition.getX());
        assertEquals(0, newPosition.getY());
        assertEquals(Day01.Orientation.WEST, newPosition.getOrientation());
    }

    @Test
    public void moveRightFromOriginNorth() throws Exception {
        Day01.Position newPosition = Day01.move(Day01.ORIGIN, Day01.Move.of(Day01.Direction.RIGHT, 5));

        assertEquals(5, newPosition.getX());
        assertEquals(0, newPosition.getY());
        assertEquals(Day01.Orientation.EAST, newPosition.getOrientation());
    }

    @Test
    public void moveLeftFromOriginSouth() throws Exception {
        Day01.Position newPosition = Day01.move(ORIGIN_SOUTH, Day01.Move.of(Day01.Direction.LEFT, 5));

        assertEquals(5, newPosition.getX());
        assertEquals(0, newPosition.getY());
        assertEquals(Day01.Orientation.EAST, newPosition.getOrientation());
    }

    @Test
    public void moveRightFromOriginSouth() throws Exception {
        Day01.Position newPosition = Day01.move(ORIGIN_SOUTH, Day01.Move.of(Day01.Direction.RIGHT, 5));

        assertEquals(-5, newPosition.getX());
        assertEquals(0, newPosition.getY());
        assertEquals(Day01.Orientation.WEST, newPosition.getOrientation());
    }

    @Test
    public void moveLeftFromOriginEast() throws Exception {
        Day01.Position newPosition = Day01.move(ORIGIN_EAST, Day01.Move.of(Day01.Direction.LEFT, 5));

        assertEquals(0, newPosition.getX());
        assertEquals(5, newPosition.getY());
        assertEquals(Day01.Orientation.NORTH, newPosition.getOrientation());
    }

    @Test
    public void moveRightFromOriginEast() throws Exception {
        Day01.Position newPosition = Day01.move(ORIGIN_EAST, Day01.Move.of(Day01.Direction.RIGHT, 5));

        assertEquals(0, newPosition.getX());
        assertEquals(-5, newPosition.getY());
        assertEquals(Day01.Orientation.SOUTH, newPosition.getOrientation());
    }

    @Test
    public void moveLeftFromOriginWest() throws Exception {
        Day01.Position newPosition = Day01.move(ORIGIN_WEST, Day01.Move.of(Day01.Direction.LEFT, 5));

        assertEquals(0, newPosition.getX());
        assertEquals(-5, newPosition.getY());
        assertEquals(Day01.Orientation.SOUTH, newPosition.getOrientation());
    }

    @Test
    public void moveRightFromOriginWest() throws Exception {
        Day01.Position newPosition = Day01.move(ORIGIN_WEST, Day01.Move.of(Day01.Direction.RIGHT, 5));

        assertEquals(0, newPosition.getX());
        assertEquals(5, newPosition.getY());
        assertEquals(Day01.Orientation.NORTH, newPosition.getOrientation());
    }

    @Test
    public void parseMoves() throws Exception {
        String moves = "R5, L5, R5, R3";

        List<Day01.Move> expectedMoves = new ArrayList<>();
        expectedMoves.add(Day01.Move.of(Day01.Direction.RIGHT, 5));
        expectedMoves.add(Day01.Move.of(Day01.Direction.LEFT, 5));
        expectedMoves.add(Day01.Move.of(Day01.Direction.RIGHT, 5));
        expectedMoves.add(Day01.Move.of(Day01.Direction.RIGHT, 3));

        List<Day01.Move> parsedMoves = Day01.parseMoves(moves);

        assertEquals(expectedMoves, parsedMoves);

    }

    @Test
    public void numberOfBlocksAway() throws Exception {
        String moves = "R5, L5, R5, R3";

        int numberOfBlockAway = Day01.numberOfBlockAway(moves);

        assertEquals(12, numberOfBlockAway);

    }


    @Test
    public void stepsBetween() throws Exception {
        Day01.Position start = Day01.ORIGIN;
        Day01.Position end = Day01.Position.of(5, 0, Day01.Orientation.EAST);

        List<Day01.Position> expectedSteps = new ArrayList<>();

        expectedSteps.add(Day01.Position.of(1, 0, Day01.Orientation.EAST));
        expectedSteps.add(Day01.Position.of(2, 0, Day01.Orientation.EAST));
        expectedSteps.add(Day01.Position.of(3, 0, Day01.Orientation.EAST));
        expectedSteps.add(Day01.Position.of(4, 0, Day01.Orientation.EAST));
        expectedSteps.add(Day01.Position.of(5, 0, Day01.Orientation.EAST));

        assertEquals(expectedSteps, Day01.steps(start, end));
    }
}
