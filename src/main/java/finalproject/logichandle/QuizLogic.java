package finalproject.logichandle;

import finalproject.entity.QuesAnsDetail;

import java.util.*;

import static finalproject.main.Main.quesAnsDetailArrayList;
import static finalproject.main.Main.view;

public class QuizLogic {
    public void quizLogic(Scanner sc) {
        if (quesAnsDetailArrayList.size() < 4) {
            System.out.println("Your database is too small to use this function, please create at least 4 question and answer pairs");
        } else {
            view.quizForeword();
            createQuestion(sc);
            boolean flag = true;
            while (flag) {
                view.askForNextQuestion();
                int choose = view.checkNumberException(sc, 1, 2);
                switch (choose) {
                    case 1:
                        createQuestion(sc);
                        break;
                    case 2:
                        flag = false;
                        break;
                }
            }
        }
    }

    private void createQuestion(Scanner sc) {
        //chose 1 CoupleQuesAns and 3 random answer from answerList
        String[] answers = new String[4];
        Random rd = new Random();
        //id question chose
        int idQues = randomQuizQuestion(rd, answers);
        String answer = getAnswer(idQues);
        // 1st answer
        int idQues1 = randomFirstAnswer(rd, answers, idQues);
        // 2nd answer
        int idQues2 = randomSecondAnswer(rd, answers, idQues, idQues1);
        //3rd answer
        randomThirdAnswer(rd, answers, idQues, idQues1, idQues2);
        //shuffle element of array answers
        Collections.shuffle(Arrays.asList(answers));

        view.showQuiz(answers);

        chooseCorrectAnswer(sc, answers, answer);
    }

    private void chooseCorrectAnswer(Scanner sc, String[] answers, String answer) {
        int choose = view.checkNumberException(sc, 1, 4);
        switch (choose) {
            case 1: checkCorrectAnswer(answers[0], answer);
                break;
            case 2: checkCorrectAnswer(answers[1], answer);
                break;
            case 3: checkCorrectAnswer(answers[2], answer);
                break;
            case 4: checkCorrectAnswer(answers[3], answer);
                break;
        }
    }

    private String getAnswer(int id) {
        String answer = null;
        for (QuesAnsDetail i : quesAnsDetailArrayList) {
            if (i.getId() == id) {
                answer = i.getAnswer().getContent();
            }
        }
        return answer;
    }

    private void randomThirdAnswer(Random rd, String[] answers, int idQues, int idQues1, int idQues2) {
        int idQues3;
        String answer3 = null;
        boolean flag3 = true;
        while (flag3) {
            idQues3 = rd.nextInt(quesAnsDetailArrayList.size()) + 1;
            if (idQues3 != idQues && idQues3 != idQues1 && idQues3 != idQues2) {
                for (QuesAnsDetail j : quesAnsDetailArrayList) {
                    if (j.getId() == idQues3) {
                        answer3 = j.getAnswer().getContent();
                        flag3 = false;
                    }
                }
            }
        }
        answers[3] = answer3;
    }

    private int randomSecondAnswer(Random rd, String[] answers, int idQues, int idQues1) {
        int idQues2 = 0;
        String answer2 = null;
        boolean flag2 = true;
        while (flag2) {
            idQues2 = rd.nextInt(quesAnsDetailArrayList.size()) + 1;
            if (idQues2 != idQues && idQues2 != idQues1) {
                for (QuesAnsDetail j : quesAnsDetailArrayList) {
                    if (j.getId() == idQues2) {
                        answer2 = j.getAnswer().getContent();
                        flag2 = false;
                    }
                }
            }
        }
        answers[2] = answer2;
        return idQues2;
    }

    private int randomFirstAnswer(Random rd, String[] answers, int idQues) {
        int idQues1 = 0;
        String answer1 = null;
        boolean flag1 = true;
        while (flag1) {
            idQues1 = rd.nextInt(quesAnsDetailArrayList.size()) + 1;
            if (idQues1 != idQues) {
                for (QuesAnsDetail j : quesAnsDetailArrayList) {
                    if (j.getId() == idQues1) {
                        answer1 = j.getAnswer().getContent();
                        flag1 = false;
                    }
                }
            }
        }
        answers[1] = answer1;
        return idQues1;
    }

    private int randomQuizQuestion(Random rd, String[] answers) {
        int idQues = 0;
        String answer = null;
        boolean flag = true;
        while (flag) {
            idQues = rd.nextInt(quesAnsDetailArrayList.size()) + 1;
            for (QuesAnsDetail j : quesAnsDetailArrayList) {
                if (j.getId() == idQues) {
                    System.out.println(j.getQuestion());
                    answer = j.getAnswer().getContent();
                    flag = false;
                }
            }
        }
        answers[0] = answer;
        return idQues;
    }

    private void checkCorrectAnswer(String string, String answer) {
        String[] strings;
        if (string.equals(answer)) {
            strings = new String[]{"Đáp án chính xác", "Tuyệt vời", "Bạn trả lời đúng rồi", "Làm tốt lắm"};
        } else {
            strings = new String[]{"Đáp án không chính xác", "Ops, sai rồi :(", "Bạn trả lời sai rồi", "Ôn tập lại đi nhé!"};
        }
        Random rd = new Random();
        int num = rd.nextInt(4);
        System.out.println(strings[num]);
        System.out.println("Đáp án đúng là: " + answer);
    }

}
