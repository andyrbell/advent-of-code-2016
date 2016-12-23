import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by abell on 22/12/16.
 */
public class Day10 {

    private static Pattern value = Pattern.compile("value (\\d+) goes to bot (\\d+)");
    private static Pattern rule = Pattern.compile("bot (\\d+) gives low to (.*) and high to (.*)");

    interface Receivable {
        void receive(int value);
    }

    @Data
    static class Output implements Receivable {

        private List<Integer> values = new ArrayList<>();

        @Override
        public void receive(int value) {
            values.add(value);
        }
    }

    @Data
    @ToString(exclude = { "high", "low" })
    static class Bot implements Receivable {

        @NonNull
        private String name;
        @NonNull
        private State state;
        private Receivable high;
        private Receivable low;
        private Queue<Integer> values = new ArrayDeque<>();


        void addRule(Receivable high, Receivable low) {
            this.high = high;
            this.low = low;
            update();
        }

        @Override
        public void receive(int value) {
            values.add(value);
            update();
        }

        private void update() {
            while (low != null && values.size() > 1) {
                int value1 = values.poll();
                int value2 = values.poll();

                if (value1 > value2) {
                    high.receive(value1);
                    low.receive(value2);
                } else {
                    high.receive(value2);
                    low.receive(value1);
                }

                state.addComparison(name, value1, value2);
            }
        }
    }

    @Data
    static class State {

        Map<String, Receivable> state = new TreeMap<>();
        Map<String, String> comparisons = new HashMap<>();

        Receivable getReceivable(String key) {
            Receivable receivable = state.get(key);

            if (receivable == null) {
                if (key.startsWith("bot")) {
                    receivable = new Bot(key, this);
                } else {
                    receivable = new Output();
                }
                state.put(key, receivable);
            }

            return receivable;
        }

        void addComparison(String botKey, int value1, int value2) {
            String key = value1 > value2 ? String.format("%d:%d", value2, value1) : String.format("%d:%d", value1, value2);
            comparisons.put(key, botKey);
        }

        String getComparison(int value1, int value2) {
            String key = value1 > value2 ? String.format("%d:%d", value2, value1) : String.format("%d:%d", value1, value2);
            return comparisons.get(key);
        }

    }

    static State evaluate(List<String> instructions) {
        State state = new State();

        for (String instruction : instructions) {
            Matcher matcher = value.matcher(instruction);

            if (matcher.matches()) {
                int v = Integer.parseInt(matcher.group(1));
                int botNo = Integer.parseInt(matcher.group(2));

                String botKey = "bot " + botNo;
                state.getReceivable(botKey).receive(v);

                continue;
            }

            matcher = rule.matcher(instruction);

            if (matcher.matches()) {
                int botNo = Integer.parseInt(matcher.group(1));
                String botKey = "bot " + botNo;
                String lowKey = matcher.group(2);
                String highKey = matcher.group(3);

                Receivable high = state.getReceivable(highKey);
                Receivable low = state.getReceivable(lowKey);

                ((Bot) state.getReceivable(botKey)).addRule(high, low);

                continue;
            }

            throw new RuntimeException("No match: " + instruction);
        }

        return state;

    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day10.txt"));

        State state = evaluate(lines);

        System.out.println("Part one answer: " + state.getComparison(61, 17));

        int part2 = state.getState().entrySet().stream()
                .filter(e -> e.getKey().matches("output [012]"))
                .map(e -> ((Output)e.getValue()).getValues().get(0))
                .reduce(1, (a, b) -> a * b);

        System.out.println("Part two answer: " + part2);
    }
}
