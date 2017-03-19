package track.lessons.lesson5generics;

/**
 * key - Integer
 * value - String
 */
public class HashMapImpl {
    public static final int DEFAULT_CAPACITY = 16;
    private Entry[] array;

    public HashMapImpl() {
        array = new Entry[DEFAULT_CAPACITY];
    }

    public void put(Integer key, String value) {
        int idx = key.hashCode() % array.length;
        Entry current = array[idx];
        if (current == null) {
            array[idx] = new Entry(key, value, null);
        } else {
            do {
                if (current.key.equals(key)) { // Подменяем значение
                    current.value = value;
                    return;
                }
                // Бежим по цепочке
                current = current.next;
            } while (current.next != null);

            // Добавляем в конец
            current.next = new Entry(key, value, null);
        }
    }

    public String get(Integer key) {
        int idx = key.hashCode() % array.length;
        Entry current = array[idx];
        if (current != null) {
            do {
                if (current.key.equals(key)) {
                    return current.value;
                }
                current = current.next;
            } while (current.next != null);
        }
        return null;
    }

    static class Entry {
        Integer key;
        String value;
        Entry next;

        public Entry(int key, String value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        HashMapImpl map = new HashMapImpl();


        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(1, "Z");

        System.out.println(map.get(1));
        System.out.println(map.get(4));
    }

}


