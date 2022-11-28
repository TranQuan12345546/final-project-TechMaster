package secret.entity;

public class CoupleQuesAns {

    public static int ID_COUPLE = 0;

    private final int id;
    private String question;
    private String topic;
    private String answer;

    public CoupleQuesAns() {
        this.id = ID_COUPLE++;
    }

    public CoupleQuesAns(String question, String topic, String answer) {
        this.id = ID_COUPLE++;
        this.question = question;
        this.topic = topic;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "CoupleQuesAns{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", topic='" + topic + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
