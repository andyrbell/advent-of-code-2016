import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by abell on 09/12/16.
 */
public class Day05 {

    static String md5(String input) {

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] digest = md.digest(input.getBytes());
        return new HexBinaryAdapter().marshal(digest).toLowerCase();
    }

    static String password(String input) {

        StringBuilder sb = new StringBuilder();

        int i = 0;

        while (sb.length() < 8) {

            String md5 = md5(input + i);

            if (md5.startsWith("00000")) {
                sb.append(md5.charAt(5));
            }

            i++;
        }

        return sb.toString();
    }

    static String password2(String input) {

        char[] chars = new char[8];
        Set<Integer> indices = new HashSet<>();

        int i = 0;

        while (indices.size() < 8) {
            String md5 = md5(input + i);

            if (md5.startsWith("00000")) {
                char idx = md5.charAt(5);
                if (Character.isDigit(idx)) {
                    int index = Integer.parseInt(String.valueOf(idx));
                    if (index >= 0 && index < chars.length && !indices.contains(index)) {
                        chars[index] = md5.charAt(6);
                        indices.add(index);
                    }
                }
            }

            i++;

        }

        return new String(chars);
    }

    public static void main(String[] args) {
        String input = "uqwqemis";
        System.out.println("Part one answer: " + password(input));
        System.out.println("Part two answer: " + password2(input));
    }
}
