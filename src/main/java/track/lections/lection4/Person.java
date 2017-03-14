package track.lections.lection4;

public class Person {
    private Pocket pocket;
    private int age;

    //Обязательно нужен пустой конструктор для сериализации из json
    public Person() {
    }

    public Pocket getPocket() {
        return pocket;
    }

    public void setPocket(Pocket pocket) {
        this.pocket = pocket;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
