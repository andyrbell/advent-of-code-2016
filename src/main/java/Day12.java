import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abell on 13/12/16.
 */
public class Day12 {

    static int evaluate(Map<String, Integer> registers, List<String> instructions) {

        int i = 0;
        int noOfInstructions = instructions.size();

        while (i < noOfInstructions) {

            String instruction = instructions.get(i);
            if (instruction.trim().length() == 0) {
                i++;
                continue;
            }
//            System.out.println(String.format("Evaluating: (%s/%s) a=%s b=%s c=%s d=%s %s", i, noOfInstructions, registers.get("a"), registers.get("b"), registers.get("c"), registers.get("d"), instruction));

            String[] tokens = instruction.split(" ");

            if ("cpy".equals(tokens[0])) {
                copy(registers, evaluate(registers, tokens[1]), tokens[2]);
                i++;
            } else if ("inc".equals(tokens[0])) {
                inc(registers, tokens[1]);
                i++;
            } else if ("dec".equals(tokens[0])) {
                dec(registers, tokens[1]);
                i++;
            } else if ("jnz".equals(tokens[0])) {
                String arg1 = tokens[1];
                String arg2 = tokens[2];
                if (evaluate(registers, arg1) != 0) {
                    i = i + evaluate(registers, arg2);
                } else {
                    i++;
                }
            }
        }

        return registers.get("a");
    }

    private static int evaluate(Map<String, Integer> registers, String arg) {
        int argValue;
        if (Character.isLetter(arg.codePointAt(0))) {
            argValue = registers.get(arg);
        } else {
            argValue = Integer.parseInt(arg);
        }
        return argValue;
    }

    private static void inc(Map<String, Integer> registers, String register) {
        Integer value = registers.get(register);
        registers.put(register, ++value);
    }

    private static void dec(Map<String, Integer> registers, String register) {
        Integer value = registers.get(register);
        registers.put(register, --value);
    }

    private static void copy(Map<String, Integer> registers, int value, String destinationRegister) {
        registers.put(destinationRegister, value);
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day12.txt"));

        Map<String, Integer> registers = new HashMap<>();
        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", 0);
        registers.put("d", 0);

        System.out.println("Part one answer: " + evaluate(registers, lines));

        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", 1);
        registers.put("d", 0);

        System.out.println("Part Two answer: " + evaluate(registers, lines));
    }
}
