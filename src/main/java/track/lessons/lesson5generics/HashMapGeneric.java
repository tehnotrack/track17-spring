package track.lessons.lesson5generics;

import java.util.List;

/**
 *
 */
public class HashMapGeneric<K, V> {

    public static final int DEFAULT_CAPACITY = 16;
    private Entry<K, V>[] array;

    public HashMapGeneric() {
        array = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
    }

    public void put(K key, V value) {
        int idx = key.hashCode() % array.length;
        Entry<K, V> current = array[idx];
        if (current == null) {
            array[idx] = new Entry<>(key, value, null);
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
            current.next = new Entry<>(key, value, null);
        }
    }

    public V get(K key) {
        int idx = key.hashCode() % array.length;
        Entry<K, V> current = array[idx];
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

    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        HashMapGeneric<Integer, String> map = new HashMapGeneric<>();

        HashMapGeneric<String, List<Integer>> map2 = new HashMapGeneric<>();
    }

}


