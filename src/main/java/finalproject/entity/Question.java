package finalproject.entity;

import finalproject.constant.TopicQuestion;

import java.util.Scanner;

import static finalproject.main.Main.mode;
import static finalproject.main.Main.view;


public class Question implements Inputable{
    public static int ID_QUESTION = 1;

    private final int id;
    private String content;
    private String Topic;

    public Question() {
        this.id = ID_QUESTION;
        ID_QUESTION++;
    }

    public Question(int id) {
        ID_QUESTION = 0;
        this.id = ID_QUESTION;
        ID_QUESTION++;
    }

    public Question(String content, String topic) {
        this.id = ID_QUESTION++;
        this.content = content;
        Topic = topic;
    }

    public Question(String content) {
        this.id = ID_QUESTION++;
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

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    @Override
    public String toString() {
        if (mode) {
            return  "Topic: '" + Topic + '\'' +
                    "." +
                    content + '\'' + ". ";
        } else {
            return content + " ";
        }

    }

    @Override
    public void input(Scanner sc) {
        if (mode) {
            view .viewTopic();
            int choice = view.checkNumberException(sc, 1, 5);
            switch (choice) {
                case 1:
                    this.setTopic(TopicQuestion.JAVA_CORE.value);
                    break;
                case 2:
                    this.setTopic(TopicQuestion.OOP.value);
                    break;
                case 3:
                    this.setTopic(TopicQuestion.JAVA_THREADS.value);
                    break;
                case 4:
                    this.setTopic(TopicQuestion.JAVA_COLLECTIONS.value);
                    break;
                case 5:
                    this.setTopic(TopicQuestion.EXCEPTION.value);
                    break;
            }
        }
        System.out.println("Please enter the content of the question: ");
        this.setContent(sc.nextLine());
    }
}
