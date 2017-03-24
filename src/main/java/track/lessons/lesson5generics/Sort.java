package track.lessons.lesson5generics;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *  Использование Comparator
 */
public class Sort {

    public static void main(String[] args) {
        List<String> strs = Arrays.asList("bc", "a", "def", "ghij");
        strs.sort(new MyComparator());

        System.out.println(strs);


//        // String -> String
//        Map<String, String> map = new LinkedHashMap<>();
//
//        map.put("one", "A");
//        map.put("three", "C");
//        map.put("x", "X");
//        map.put("two", "B");
//
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println(entry);
//        }

    }

    static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    }
}
