package finalproject.logichandle;

import finalproject.constant.StatusValue;
import finalproject.entity.QuesAnsDetail;
import finalproject.view.View;


import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import static finalproject.main.Main.quesAnsDetailArrayList;

public class QuizLogic extends View {
    public void quizLogic(Scanner sc) {
        if (quesAnsDetailArrayList.size() < 4) {
            System.out.println("Your database is too small to use this function, please create at least 4 question and answer pairs");
        } else {
            quizForeword();
            int idQues = createQuestion(sc);
            if (checkNotMemorized()) {
                boolean flag = true;
                int idQuesOld = idQues;
                do {
                    if (checkNotMemorized()) {
                        askForNextQuestion();
                        int choose = checkNumberException(sc, 1, 2);
                        if (choose == 1) {
                            int idQuesNew;
                            do {
                                String[] answers = new String[4];
                                Random rd = new Random();
                                //id question chose
                                idQuesNew = randomQuizQuestion(rd, answers);
                                if (checkOneMemorized()) {
                                    createQuestion(sc);
                                    break;
                                }
                                if (idQuesNew != idQuesOld) {
                                    getQuestion(idQuesNew);
                                    String answer = getAnswer(idQuesNew);
                                    // 1st answer
                                    int idQues1 = randomFirstAnswer(rd, answers, idQuesNew);
                                    // 2nd answer
                                    int idQues2 = randomSecondAnswer(rd, answers, idQuesNew, idQues1);
                                    //3rd answer
                                    randomThirdAnswer(rd, answers, idQuesNew, idQues1, idQues2);
                                    //shuffle element of array answers
                                    Collections.shuffle(Arrays.asList(answers));
                                    showQuiz(answers);

                                    chooseCorrectAnswer(sc, answers, answer);
                                }

                            } while (idQuesNew == idQuesOld);
                            idQuesOld = idQuesNew;
                        } else {
                            flag = false;
                        }
                    } else {
                        System.out.println("You have memorized all question.");
                        flag = false;
                    }
                } while (flag);
            }
        }
    }

    private boolean checkOneMemorized() {
        int count = 0;
        for (QuesAnsDetail i : quesAnsDetailArrayList) {
            if (i.getStatus().equals(StatusValue.MEMORIZED.value)) {
                count++;
            }
        }
        return count == 1;
    }

    private int createQuestion(Scanner sc) {
        int idQues = 0;
        if (checkNotMemorized()) {
            //chose 1 CoupleQuesAns and 3 random answer from answerList
            String[] answers = new String[4];
            Random rd = new Random();
            //id question chose
            idQues = randomQuizQuestion(rd, answers);
            getQuestion(idQues);
            String answer = getAnswer(idQues);
            // 1st answer
            int idQues1 = randomFirstAnswer(rd, answers, idQues);
            // 2nd answer
            int idQues2 = randomSecondAnswer(rd, answers, idQues, idQues1);
            //3rd answer
            randomThirdAnswer(rd, answers, idQues, idQues1, idQues2);
            //shuffle element of array answers
            Collections.shuffle(Arrays.asList(answers));
            showQuiz(answers);

            chooseCorrectAnswer(sc, answers, answer);
        } else  {
            System.out.println("You have memorized all question.");
        }
        return idQues;
    }

    private void getQuestion(int idQues) {
        quesAnsDetailArrayList.forEach(i -> {
            if (i.getId() == idQues) {
                System.out.println(i.getQuestion());
            }
        });
    }

    private boolean checkNotMemorized() {
        boolean flag = false;
        for (QuesAnsDetail i : quesAnsDetailArrayList) {
            if (i.getStatus().equals(StatusValue.NOT_MEMORIZED.value)) {
                flag = true;
                break;
            }
        }
        return flag;
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
                if (j.getId() == idQues && j.getStatus().equals(StatusValue.NOT_MEMORIZED.value)) {
                    answer = j.getAnswer().getContent();
                    flag = false;
                }
            }
        }
        answers[0] = answer;
        return idQues;
    }

    private void checkCorrectAnswer(String string, String answer) {
        Random rd = new Random();
        int num = rd.nextInt(4);
        String[] strings;
        if (string.equals(answer)) {
            strings = new String[]{"Đáp án chính xác", "Tuyệt vời", "Bạn trả lời đúng rồi", "Làm tốt lắm"};
            System.out.println(strings[num]);
            System.out.println("Đáp án đúng là: " + answer);
            addMemorized(answer);
        } else {
            strings = new String[]{"Đáp án không chính xác", "Ops, sai rồi :(", "Bạn trả lời sai rồi", "Ôn tập lại đi nhé!"};
            System.out.println(strings[num]);
            System.out.println("Đáp án đúng là: " + answer);
        }


    }

    private void addMemorized(String answer) {
        for (QuesAnsDetail i : quesAnsDetailArrayList) {
            if (i.getAnswer().getContent().equals(answer)) {
                i.setCheckMemorized();
                if(i.getCheckMemorized() == 3) {
                    i.setStatus(StatusValue.MEMORIZED.value);
                    System.out.println("You have memorized this question. It will not be displayed anymore. You can change it in the Learning Status.");
                }
                break;
            }
        }
    }

}
