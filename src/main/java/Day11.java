import lombok.Data;
import lombok.NonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abell on 02/01/17.
 */
public class Day11 {


    @Data
    static class Item {
        @NonNull
        private String type;
        @NonNull
        private int value;
    }

    @Data
    static class Generator extends Item {
    }

    @Data
    static class Microchip extends Item {
    }

    @Data
    static class Floor {
        @NonNull
        private int level;
        private List<Item> items = new ArrayList<>();

        public boolean valid() {
            return items.stream().mapToInt(Item::getValue).reduce(0, (a, b) -> a + b) == 0;
        }

        public boolean valid(Elevator elevator) {
            List<Item> floorAndElevatorItems = new ArrayList<>(items);
            floorAndElevatorItems.addAll(elevator.getItems());

            return floorAndElevatorItems.stream().mapToInt(Item::getValue).reduce(0, (a, b) -> a + b) == 0;
        }
    }

    @Data
    static class Elevator {
        @NonNull
        private int level;
        private List<Item> items = new ArrayList<>();
    }

    @Data
    static class State {
        private Floor[] floors = new Floor[4];
        private Elevator elevator = new Elevator(0);


    }

    public static int minimumSteps(List<String> instructions) {

        return 11;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day11Test.txt"));

        System.out.println("Part one answer: " + minimumSteps(lines));

    }
}
