package track.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 *
 */
public class Util {

    public static final int SHIFT = 3;

    public static String readFile(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(fileName);

        final StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(url.getPath()))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            System.out.println("Failed to read file: " + e);
        }
        return builder.toString();
    }

    public static String encrypt(String text) {
        String data = text.toLowerCase().trim();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == ' ') {
                continue;
            }
            builder.append(shift(data.charAt(i), SHIFT));
        }
        return builder.toString();
    }

    private static char shift(char ch, int shift) {
        char tmp = (char) (ch + shift);
        if (tmp > 'z') {
            tmp = (char) ('a' + tmp - 'z' - 1);
        }
        return tmp;
    }
}
