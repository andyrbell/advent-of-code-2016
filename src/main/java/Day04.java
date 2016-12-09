import lombok.Value;

import static java.util.Map.Entry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by abell on 08/12/16.
 */
public class Day04 {

    static final Pattern pattern = Pattern.compile("([a-z\\-]*)(\\d*)\\[([a-z]{5})\\]");

    @Value(staticConstructor = "of")
    static class Room {
        private String name;
        private int sector;
        private String checksum;
    }

    static Room parseRoom(String line) {

        Matcher matcher = pattern.matcher(line);

        if (matcher.matches()) {
            String name = matcher.group(1);
            int sector = Integer.parseInt(matcher.group(2));
            String checksum = matcher.group(3);

            return Room.of(name, sector, checksum);
        }

        return null;
    }

    static boolean isValid(Room room) {
        return room != null && room.getChecksum().equals(checksum(room.getName()));
    }

    static Comparator<Entry<String, Integer>> countDescCharAsc = (o1, o2) -> {
        if (o1.getValue().compareTo(o2.getValue()) == 0) {
            return o1.getKey().compareTo(o2.getKey());
        }
        return -1 * o1.getValue().compareTo(o2.getValue());
    };

    static String checksum(String name) {
        Map<String, Integer> map = new HashMap<>();

        for (char c : name.replaceAll("-","").toCharArray()) {
            String s = String.valueOf(c);
            Integer count = map.get(s);
            if (count == null) {
                count = 0;
            }
            map.put(s, ++count);
        }
        return map.entrySet().stream()
                .sorted(countDescCharAsc)
                .limit(5)
                .map(Entry::getKey)
                .reduce("", (a, b) -> a + b);
    }

    static String decrypt(Room room) {
        return shift(room.getName(), room.getSector());
    }

    static String shift(String name, int rotation) {
        char[] chars = name.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '-') {
                chars[i] = ' ';
                continue;
            }
            int newValue = ((int)chars[i]) + rotation % 26;
            if (newValue > 122) {
                newValue -= 26;
            }
            chars[i] = (char) newValue;
        }

        return String.valueOf(chars).trim();
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day04.txt"));

        Integer sectorTotal = lines.stream()
                .map(Day04::parseRoom)
                .filter(Day04::isValid)
                .map(Day04.Room::getSector)
                .reduce(0, (a, b) -> a + b);

        System.out.println("Part one answer: " + sectorTotal);

        Integer sectorId = lines.stream()
                .map(Day04::parseRoom)
                .filter(Day04::isValid)
                .collect(Collectors.toMap(Day04::decrypt, Room::getSector))
                .get("northpole object storage");

        System.out.println("Part two answer: " + sectorId);
    }
}
