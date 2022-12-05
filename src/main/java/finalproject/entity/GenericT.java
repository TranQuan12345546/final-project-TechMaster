package finalproject.entity;

import java.util.Scanner;

public class GenericT <T> {

    private T content;

    public GenericT(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public void input(Scanner sc) {
        System.out.println("Please enter content: ");
        Question question = new Question();
        GenericT<Question> a = new GenericT<>(question);
    }
}
