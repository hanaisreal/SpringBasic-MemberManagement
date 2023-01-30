package hello.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter  //lombok에서 자동으로 setter를 만들어준다.
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok lombok = new HelloLombok();
        lombok.setName("spring");
        System.out.println(lombok.getName());
    }
}
