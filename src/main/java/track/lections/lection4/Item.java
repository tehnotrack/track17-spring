package track.lections.lection4;

public class Item {
    private String name;
    private int count;
    private String color;

    //Обязательно нужен пустой конструктор для сериализации из json
    public Item() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
