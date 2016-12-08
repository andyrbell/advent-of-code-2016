import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by abell on 06/12/16.
 */
public class Day01 {

    static List<Move> parseMoves(String moves) {
        String[] moveTokens = moves.split(", ");

        return Arrays.stream(moveTokens)
                .map(token -> {
                    Direction direction = Direction.valueOf(token.charAt(0));
                    int blocks = Integer.parseInt(token.substring(1).trim());
                    return Move.of(direction, blocks);
                })
                .collect(Collectors.toList());
    }

    @Value(staticConstructor = "of")
    @EqualsAndHashCode(exclude = "orientation")
    static class Position {
        int x;
        int y;
        Orientation orientation;
    }

    @Value(staticConstructor = "of")
    static class Move {
        Direction direction;
        int blocks;
    }

    static Position ORIGIN = Position.of(0, 0, Orientation.NORTH);

    public enum Orientation {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    public enum Direction {
        LEFT('L'),
        RIGHT('R');

        private char direction;

        Direction(char direction) {
            this.direction = direction;
        }

        public static Direction valueOf(char direction) {
            for (Direction d : Direction.values()) {
                if (d.direction == direction) {
                    return d;
                }
            }
            throw new RuntimeException("Invalid direction: " + direction);
        }
    }

    static int rectilinearDistance(Position origin, Position destination) {
        return Math.abs(origin.getX() - destination.getX()) + Math.abs(origin.getY() - destination.getY());
    }

    static int rectilinearDistance(Position destination) {
        return rectilinearDistance(ORIGIN, destination);
    }

    static Position move(Position currentPosition, Move move) {

        Orientation newOrientation = null;
        int newX = 0;
        int newY = 0;

        switch (currentPosition.getOrientation()) {
            case NORTH:
                newY = currentPosition.getY();
                if (move.getDirection() == Direction.LEFT) {
                    newX = currentPosition.getX() - move.getBlocks();
                    newOrientation = Orientation.WEST;
                } else {
                    newX = currentPosition.getX() + move.getBlocks();
                    newOrientation = Orientation.EAST;
                }
                break;
            case EAST:
                newX = currentPosition.getX();
                if (move.getDirection() == Direction.LEFT) {
                    newY = currentPosition.getY() + move.getBlocks();
                    newOrientation = Orientation.NORTH;
                } else {
                    newY = currentPosition.getY() - move.getBlocks();
                    newOrientation = Orientation.SOUTH;
                }
                break;
            case SOUTH:
                newY = currentPosition.getY();
                if (move.getDirection() == Direction.LEFT) {
                    newX = currentPosition.getX() + move.getBlocks();
                    newOrientation = Orientation.EAST;
                } else {
                    newX = currentPosition.getX() - move.getBlocks();
                    newOrientation = Orientation.WEST;
                }
                break;
            case WEST:
                newX = currentPosition.getX();
                if (move.getDirection() == Direction.LEFT) {
                    newY = currentPosition.getY() - move.getBlocks();
                    newOrientation = Orientation.SOUTH;
                } else {
                    newY = currentPosition.getY() + move.getBlocks();
                    newOrientation = Orientation.NORTH;
                }
                break;
        }

        return Position.of(newX, newY, newOrientation);
    }

    static List<Position> steps(Position start, Position end) {
        List<Position> result = new ArrayList<>();

        Position current = start;

        while (!current.equals(end)) {
            if (current.getX() < end.getX()) {
                current = Position.of(current.getX() + 1, current.getY(), end.getOrientation());
            } else if (current.getX() > end.getX()) {
                current = Position.of(current.getX() - 1, current.getY(), end.getOrientation());
            } else if (current.getY() < end.getY()) {
                current = Position.of(current.getX(), current.getY() + 1, end.getOrientation());
            } else if (current.getY() > end.getY()) {
                current = Position.of(current.getX(), current.getY() - 1, end.getOrientation());
            }

            result.add(current);
        }

        return result;
    }

    static int numberOfBlockAway(String input) {
        List<Move> moves = parseMoves(input);

        Set<Position> visited = new HashSet<>();
        Position firstDuplicate = null;

        Position currentPosition = ORIGIN;

        visited.add(currentPosition);

        for (Move move : moves) {
            Position nextPosition = move(currentPosition, move);
            List<Position> steps = steps(currentPosition, nextPosition);

            for (Position step : steps) {
                if (!visited.add(step)) {
                    if (firstDuplicate == null) {
                        firstDuplicate = step;
                    }
                }
            }

            currentPosition = nextPosition;
        }

        System.out.println("Part two answer: " + (firstDuplicate == null ? null : rectilinearDistance(firstDuplicate)));

        return rectilinearDistance(currentPosition);
    }

    public static void main(String[] args) throws IOException {
        String input = new String(Files.readAllBytes(Paths.get("src/main/resources/Day01.txt")));

        System.out.println("input: " + input);
        System.out.println("Part one answer: " + numberOfBlockAway(input));
    }

}
