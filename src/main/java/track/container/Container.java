package track.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import track.container.config.Bean;

/**
 * Основной класс контейнера
 * У него определено 2 публичных метода, можете дописывать свои методы и конструкторы
 */
public class Container {

    Map<String, Object> map = new HashMap<>();

    // Реализуйте этот конструктор, используется в тестах!
    public Container(List<Bean> beans) throws Exception {

    }

    public static void main(String[] args) throws Exception {

    }

    /**
     *  Вернуть объект по имени бина из конфига
     *  Например, Car car = (Car) container.getById("carBean")
     */
    public Object getById(String id) {
        return null;
    }

    /**
     * Вернуть объект по имени класса
     * Например, Car car = (Car) container.getByClass("track.container.beans.Car")
     */
    public Object getByClass(String className) {
        return null;
    }
}
