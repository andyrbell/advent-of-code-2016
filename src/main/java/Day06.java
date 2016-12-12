import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by abell on 09/12/16.
 */
public class Day06 {
    static String correct(List<String> lines, Comparator<Map.Entry<String, Integer>> comparator) {

        Map<Integer, Map<String, Integer>> mapOfPositionToMapOfCharacterFrequency = new TreeMap<>();


        for (String line : lines) {
            char[] chars = line.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                if (!mapOfPositionToMapOfCharacterFrequency.containsKey(i)) {
                    mapOfPositionToMapOfCharacterFrequency.put(i, new HashMap<>());
                }
                Map<String, Integer> characterFrequency = mapOfPositionToMapOfCharacterFrequency.get(i);

                String s = String.valueOf(chars[i]);
                Integer count = characterFrequency.get(s);

                if (count == null) {
                    count = 0;
                }

                characterFrequency.put(s, ++count);
            }
        }

        StringBuilder sb = new StringBuilder(8);

        for (Map.Entry<Integer, Map<String, Integer>> entry : mapOfPositionToMapOfCharacterFrequency.entrySet()) {
            Integer position = entry.getKey();
            Map<String, Integer> freq = entry.getValue();
            sb.replace(position, position + 1, freq.entrySet().stream().sorted(comparator).limit(1).map(Map.Entry::getKey).findFirst().get());
        }
        return sb.toString();
    }


    static Comparator<Map.Entry<String, Integer>> countDesc = (o1, o2) -> -1 * o1.getValue().compareTo(o2.getValue());
    static Comparator<Map.Entry<String, Integer>> countAsc = Comparator.comparing(Map.Entry::getValue);

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day06.txt"));

        String result = Day06.correct(lines, countDesc);

        System.out.println("Part one answer: " + result);

        String result2 = Day06.correct(lines, countAsc);

        System.out.println("Part two answer: " + result2);

    }

}
