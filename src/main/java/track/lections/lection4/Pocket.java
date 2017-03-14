package track.lections.lection4;

import java.util.List;

public class Pocket {
    private List<Item> items;

    //Обязательно нужен пустой конструктор для сериализации из json
    public Pocket(){}

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
