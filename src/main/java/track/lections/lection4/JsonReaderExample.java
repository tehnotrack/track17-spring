package track.lections.lection4;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonReaderExample {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(new File("src/main/resources/config_example.json"), Person.class);
        System.out.println("Person age: " + person.getAge());
        System.out.println("Items in pocket: ");
        for (Item item: person.getPocket().getItems()) {
            System.out.println(item.getName());
        }
    }
}
