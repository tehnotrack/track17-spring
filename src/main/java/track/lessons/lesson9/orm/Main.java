package track.lessons.lesson9.orm;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import track.lessons.lesson9.User;

/**
 *
 */
public class Main {


    public static void main(String[] args) {

        //Create Spring application context
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("db.config.xml");

        UserService productService = ctx.getBean(UserService.class);


        productService.add(new User(1, 34, "B"));
        productService.add(new User(2, 23, "A"));

        System.out.println("listAll: " + productService.listAll());


        ctx.close();

    }
}
