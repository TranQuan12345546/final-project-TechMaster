package secret.entity;

import java.util.Scanner;

public class Answer implements Inputable{
    public static int ID_ANSWER = 1;

    private final int id;
    private String content;

    public Answer() {
        this.id = ID_ANSWER;
        ID_ANSWER++;
    }

    public Answer(String content) {
        this.id = ID_ANSWER++;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Answer: " +
                "'" + content + '\'';
    }

    @Override
    public void input(Scanner sc) {
        System.out.println("Please enter the content of the answer: ");
        this.setContent(sc.nextLine());
    }
}
