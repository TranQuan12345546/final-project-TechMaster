package finalproject.logichandle;

import finalproject.constant.TopicQuestion;
import finalproject.entity.QuesAnsDetail;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static finalproject.main.Main.*;


public class QuesAnsLogic {
    public void showCouple() {
        if (quesAnsDetailArrayList.size() == 0) {
            System.out.println("You have not added any questions or answers yet.");
        } else {
            for (QuesAnsDetail i : quesAnsDetailArrayList) {
                System.out.println(i);
            }
        }

    }

    public void addQuesAns(Scanner sc) {
        QuesAnsDetail quesAnsDetail = new QuesAnsDetail();
        quesAnsDetail.inputQuesAns(sc);
        quesAnsDetailArrayList.add(quesAnsDetail);
        do {
            System.out.println("Would you like to add a follow-up question?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choose = view.checkNumberException(sc, 1, 2);
            if (choose == 1) {
                QuesAnsDetail quesAnsDetail2 = new QuesAnsDetail();
                quesAnsDetail2.inputQuesAns(sc);
                quesAnsDetailArrayList.add(quesAnsDetail2);
            }
            if (choose == 2) {
                break;
            }
        } while (true);
    }

    public void editQuesAns(Scanner sc) {
        if (quesAnsDetailArrayList.size() == 0) {
            System.out.println("You have not added any questions or answers yet.");
        } else {
            System.out.println("Do you want to edit the question or the answer? ");
            System.out.println("1. Question.");
            System.out.println("2. Answer.");
            int choose = view.checkNumberException(sc , 1, 2);
            if (choose == 1) {
                editQuestion(sc);
            }

            if (choose == 2) {
                editAnswer(sc);
            }
        }
    }

    public void editQuestion(Scanner sc) {
        System.out.print("Enter the question ID you want to edit: ");
        boolean flag = true;
        int idQues;
        do {
            try {
                idQues = Integer.parseInt(sc.nextLine());
                for (QuesAnsDetail i : quesAnsDetailArrayList) {
                    if (i.getId() == idQues) {
                        System.out.print("Enter edit content: ");
                        String editQues = sc.nextLine();
                        i.getQuestion().setContent(editQues);
                        System.out.println("Edited question with id " + idQues);
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    System.out.println("No questions with id " + idQues);
                }
            } catch (Exception e) {
                System.out.println("You need to enter a number.");
            }
        } while (flag);
    }

    public void editAnswer(Scanner sc) {
        System.out.print("Enter the question ID you want to edit: ");
        boolean flag = true;
        int idAns;
        do {
            try {
                idAns = Integer.parseInt(sc.nextLine());
                for (QuesAnsDetail i : quesAnsDetailArrayList) {
                    if (i.getId() == idAns) {
                        System.out.print("Enter edit content: ");
                        String editAns = sc.nextLine();
                        i.getAnswer().setContent(editAns);
                        System.out.println("Edited answer with id " + idAns);
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    System.out.println("No answer with id " + idAns);
                }
            } catch (Exception e) {
                System.out.println("You need to enter a number.");
            }
        } while (flag);
    }

    public void findQuestion(Scanner sc) {
        if (quesAnsDetailArrayList.size() == 0) {
            System.out.println("You have not added any questions or answers yet.");
        } else {
            if (mode) {
                System.out.println("1. Search by keyword");
                System.out.println("2. Search by topic");
                int choice = view.checkNumberException(sc, 1, 2);
                if (choice == 1) {
                    findQuestionByKey(sc);
                }
                if (choice == 2) {
                    findQuestionByTopic(sc);
                }
            } else {
                findQuestionByKey(sc);
            }
        }
    }

    private void findQuestionByTopic(Scanner sc) {
        view.viewTopic();
        int choose = view.checkNumberException(sc, 1, 5);
        switch (choose) {
            case 1:
                findTopic(TopicQuestion.JAVA_CORE.value);
                break;
            case 2:
                findTopic(TopicQuestion.OOP.value);
                break;
            case 3:
                findTopic(TopicQuestion.JAVA_THREADS.value);
                break;
            case 4:
                findTopic(TopicQuestion.JAVA_COLLECTIONS.value);
                break;
            case 5:
                findTopic(TopicQuestion.EXCEPTION.value);
                break;
        }
    }

    private void findTopic(String string) {
        ArrayList<QuesAnsDetail> quesAnsDetailArrayList1 = new ArrayList<>();
        int count = 0;
        for (QuesAnsDetail i : quesAnsDetailArrayList) {
            if (i.getQuestion().getTopic().equals(string)){
                count++;
                quesAnsDetailArrayList1.add(i);
            }
        }
        if (count == 0) {
            System.out.println("There are no question with topic " + string);
        }
        if (count != 0) {
            System.out.println("There are " + count + " question with topic : " + string);
            for(QuesAnsDetail i : quesAnsDetailArrayList1) {
                System.out.println(i);
            }
        }
    }

    private void findQuestionByKey(Scanner sc) {
        System.out.println("Enter the keyword you want to search for: ");
        String findQues = sc.nextLine();
        boolean flag = true;
        for (QuesAnsDetail i : quesAnsDetailArrayList) {
            String term = i.getQuestion().getContent();
            if (term.contains(findQues)) {
                System.out.println("This is the question you need to find: ");
                System.out.println(i);
                flag = false;
            }
        }
        if (flag) {
            System.out.println("No questions found with keyword " + findQues);
        }
    }

    public void practice(Scanner sc) {
        int idQues = randomQuestion();
        System.out.println("Press Enter to see the answer");
        String term  = sc.nextLine();
        for (QuesAnsDetail i : quesAnsDetailArrayList) {
            if (i.getId() == idQues) {
                System.out.println(i.getAnswer().getContent());
            }
        }
        nextQuestion(sc);
    }

    private void nextQuestion(Scanner sc) {
        boolean flag = true;
        while (flag){
            view.askForNextQuestion();
            int choose = view.checkNumberException(sc, 1, 2);
            switch (choose) {
                case 1: practice(sc);
                    break;
                case 2: flag = false;
                    break;
            }
        }
    }

    private int randomQuestion() {
        Random rd = new Random();
        int idQues = 0;
        boolean flag = true;
        while (flag) {
            idQues = rd.nextInt(quesAnsDetailArrayList.size());
            for (QuesAnsDetail j : quesAnsDetailArrayList) {
                if (j.getId() == idQues) {
                    System.out.println(j.getQuestion().getContent());
                    flag = false;
                }
            }
        }
        return idQues;
    }

}
