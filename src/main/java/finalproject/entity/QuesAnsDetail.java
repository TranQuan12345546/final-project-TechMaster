package finalproject.entity;


import java.util.Scanner;
import static finalproject.main.Main.mode;

public class QuesAnsDetail {

    public static int ID_COUPLE = 1;

    private final int id;
    private Question question;
    private Answer answer;

    public QuesAnsDetail(int id) {
        ID_COUPLE = 0;
        this.id = ID_COUPLE;
        ID_COUPLE++;
    }

    public QuesAnsDetail(Question question, Answer answer) {
        this.id = ID_COUPLE++;
        this.question = question;
        this.answer = answer;
    }

    public QuesAnsDetail() {
        this.id = ID_COUPLE++;
    }

    public int getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        if(mode) {
            return "Question " + id + ": " +
                    question +
                    answer;
        } else {
            return "Từ vựng " + id + ": " +
                    question +
                    answer;
        }
    }

    public void inputQuesAns(Scanner sc) {
        Question question = new Question();
        Answer answer = new Answer();
        question.input(sc);
        answer.input(sc);
        this.question = question;
        this.answer = answer;
    }

}
