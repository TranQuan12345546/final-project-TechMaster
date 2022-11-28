package secret.logichandle;

import secret.entity.CoupleQuesAns;
import static secret.main.Main.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import static secret.logichandle.QuesAnsLogic.checkNumberException;
import static secret.main.Main.coupleQuesAnsArrayList;

public class QuizLogic {
    public void quizLogic(Scanner sc) {

        if (coupleQuesAnsArrayList.size() < 4) {
            System.out.println("Your database is too small to use this function, please create at least 4 question and answer pairs");
        } else {
            view.quizForeword();
            createQuestion(sc);
            boolean flag = true;
            while (flag){
                view.askForNextQuestion();
                int choose = checkNumberException(sc, 1, 2);
                switch (choose) {
                    case 1: createQuestion(sc);
                        break;
                    case 2: flag = false;
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
        int idQues = randomQuestion(rd, answers);
        String answer = getAnswer(idQues);
        // 1st answer
        int idQues1 = randomFirstAnswer(rd, answers, idQues);
        // 2nd answer
        int idQues2 = randomSecondAnswer(rd, answers, idQues, idQues1);
        //3rd answer
        randomThirdAnswer(rd, answers, idQues, idQues1, idQues2);
        //shuffle element of array answers
        Collections.shuffle(Arrays.asList(answers));

        view.showQuizz(answers);

        chooseCorrectAnswer(sc, answers, answer);
    }

    private void chooseCorrectAnswer(Scanner sc, String[] answers, String answer) {
        int choose = checkNumberException(sc, 1, 4);
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
        for (CoupleQuesAns i : coupleQuesAnsArrayList) {
            if (i.getId() == id) {
                answer = i.getAnswer();
            }
        }
        return answer;
    }

    private void randomThirdAnswer(Random rd, String[] answers, int idQues, int idQues1, int idQues2) {
        int idQues3;
        String answer3 = null;
        boolean flag3 = true;
        while (flag3) {
            idQues3 = rd.nextInt(coupleQuesAnsArrayList.size());
            if (idQues3 != idQues && idQues3 != idQues1 && idQues3 != idQues2) {
                for (CoupleQuesAns j : coupleQuesAnsArrayList) {
                    if (j.getId() == idQues3) {
                        answer3 = j.getAnswer();
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
            idQues2 = rd.nextInt(coupleQuesAnsArrayList.size());
            if (idQues2 != idQues && idQues2 != idQues1) {
                for (CoupleQuesAns j : coupleQuesAnsArrayList) {
                    if (j.getId() == idQues2) {
                        answer2 = j.getAnswer();
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
            idQues1 = rd.nextInt(coupleQuesAnsArrayList.size());
            if (idQues1 != idQues) {
                for (CoupleQuesAns j : coupleQuesAnsArrayList) {
                    if (j.getId() == idQues1) {
                        answer1 = j.getAnswer();
                        flag1 = false;
                    }
                }
            }
        }
        answers[1] = answer1;
        return idQues1;
    }

    private int randomQuestion(Random rd, String[] answers) {
        int idQues = 0;
        String answer = null;
        boolean flag = true;
        while (flag) {
            idQues = rd.nextInt(coupleQuesAnsArrayList.size());
            for (CoupleQuesAns j : coupleQuesAnsArrayList) {
                if (j.getId() == idQues) {
                    System.out.print("Question: ");
                    System.out.println(j.getQuestion());
                    answer = j.getAnswer();
                    flag = false;
                }
            }
        }
        answers[0] = answer;
        return idQues;
    }

    private void checkCorrectAnswer(String string, String answer) {
        if (string.equals(answer)) {
            String[] strings = {"Đáp án chính xác", "Tuyệt vời", "Bạn trả lời đúng rồi", "Làm tốt lắm"};
            Random rd = new Random();
            int num = rd.nextInt(4);
            System.out.println(strings[num]);
        } else {
            String[] strings = {"Đáp án không chính xác", "Ops, sai rồi :(", "Bạn trả lời sai rồi", "Ôn tập lại đi nhé!"};
            Random rd = new Random();
            int num = rd.nextInt(4);
            System.out.println(strings[num]);

        }
    }

}
