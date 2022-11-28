package secret.logichandle;

import secret.constant.TopicQuestion;
import secret.entity.CoupleQuesAns;

import java.util.ArrayList;
import java.util.Scanner;

import static secret.main.Main.coupleQuesAnsArrayList;
import static secret.main.Main.view;


public class QuesAnsLogic {

    public static int checkNumberException(Scanner sc, int firstNumber, int lastNumber) {
        int choice = -1;
        do {
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice < firstNumber || choice > lastNumber) {
                    System.out.println("Invalid selection, please choose again");
                }
            } catch (Exception e ) {
                System.out.println("You need to enter a number.");
            }
        } while (choice < firstNumber || choice > lastNumber);
        return choice;
    }



    public void showCouple() {
        if (coupleQuesAnsArrayList.size() == 0) {
            System.out.println("You have not added any questions or answers yet.");
        } else {
            System.out.println(coupleQuesAnsArrayList);
        }

    }

    public void inputQuesAns(Scanner sc, CoupleQuesAns coupleQuesAns) {
        view.viewTopic();
        int choice = checkNumberException(sc, 1, 5);
        switch (choice) {
            case 1:
                coupleQuesAns.setTopic(TopicQuestion.JAVA_CORE.value);
                break;
            case 2:
                coupleQuesAns.setTopic(TopicQuestion.OOP.value);
                break;
            case 3:
                coupleQuesAns.setTopic(TopicQuestion.JAVA_THREADS.value);
                break;
            case 4:
                coupleQuesAns.setTopic(TopicQuestion.JAVA_COLLECTIONS.value);
                break;
            case 5:
                coupleQuesAns.setTopic(TopicQuestion.EXCEPTION.value);
                break;
        }
        System.out.println("Please enter the content of the question: ");
        coupleQuesAns.setQuestion(sc.nextLine());
        System.out.println("Please enter the content of the answer: ");
        coupleQuesAns.setAnswer(sc.nextLine());
    }

    public void addQuesAns(Scanner sc, CoupleQuesAns coupleQuesAns) {
        inputQuesAns(sc, coupleQuesAns);
        coupleQuesAnsArrayList.add(coupleQuesAns);
        do {
            System.out.println("Would you like to add a follow-up question?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choose = checkNumberException(sc, 1, 2);
            if (choose == 1) {
                CoupleQuesAns coupleQuesAns1 = new CoupleQuesAns();
                inputQuesAns(sc, coupleQuesAns1);
                coupleQuesAnsArrayList.add(coupleQuesAns1);
            }
            if (choose == 2) {
                break;
            }
        } while (true);
    }

    public void editQuesAns(Scanner sc) {
        if (coupleQuesAnsArrayList.size() == 0) {
            System.out.println("You have not added any questions or answers yet.");
        } else {
            System.out.println("Do you want to edit the question or the answer? ");
            System.out.println("1. Question.");
            System.out.println("2. Answer.");
            int choose = checkNumberException(sc , 1, 2);
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
                for (CoupleQuesAns i : coupleQuesAnsArrayList) {
                    if (i.getId() == idQues) {
                        System.out.print("Enter edit content: ");
                        String editQues = sc.nextLine();
                        i.setQuestion(editQues);
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
                for (CoupleQuesAns i : coupleQuesAnsArrayList) {
                    if (i.getId() == idAns) {
                        System.out.print("Enter edit content: ");
                        String editAns = sc.nextLine();
                        i.setAnswer(editAns);
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
        if (coupleQuesAnsArrayList.size() == 0) {
            System.out.println("You have not added any questions or answers yet.");
        } else {
            System.out.println("1. Search by keyword");
            System.out.println("2. Search by topic");
            int choice = checkNumberException(sc, 1, 2);
            if (choice == 1) {
                findQuestionByKey(sc);
            }
            if (choice == 2) {
                findQuestionByTopic(sc);
            }

        }
    }

    private void findQuestionByTopic(Scanner sc) {
        view.viewTopic();
        int choose = checkNumberException(sc, 1, 5);
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
        ArrayList<CoupleQuesAns> coupleQuesAnsArrayList1 = new ArrayList<>();
        int count = 0;
        for (CoupleQuesAns i : coupleQuesAnsArrayList) {
            if (i.getTopic().equals(string)){
                count++;
                coupleQuesAnsArrayList1.add(i);
            }
        }
        if (count == 0) {
            System.out.println("There are no question with topic " + string);
        }
        if (count != 0) {
            System.out.println("There are " + count + " question with topic : " + string);
            System.out.println(coupleQuesAnsArrayList1);
        }
    }

    private void findQuestionByKey(Scanner sc) {
        System.out.println("Enter the keyword you want to search for: ");
        String findQues = sc.nextLine();
        boolean flag = true;
        for (CoupleQuesAns i : coupleQuesAnsArrayList) {
            String term = i.getQuestion();
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

    public void addDefaultQues() {
        CoupleQuesAns coupleQuesAns1 = new CoupleQuesAns("appeal", "Java Core", "Sức hấp dẫn, lôi cuốn, thu hút.");
        CoupleQuesAns coupleQuesAns2 = new CoupleQuesAns("majority", "qưeqeqwe", "Đa số, phần lớn.");
        CoupleQuesAns coupleQuesAns3 = new CoupleQuesAns("ingredient", "Java Core", "Thành phần.");
        CoupleQuesAns coupleQuesAns4 = new CoupleQuesAns("patron", "qưeqeqwe", "Người bảo trợ, ông chủ.");
        CoupleQuesAns coupleQuesAns5 = new CoupleQuesAns("predict", "qưeqeqwe", "Dự báo, dự đoán.");
        CoupleQuesAns coupleQuesAns6 = new CoupleQuesAns("narrow", "qưeqeqwe", "thu hẹp, hạn chế.");
        coupleQuesAnsArrayList.add(coupleQuesAns1);
        coupleQuesAnsArrayList.add(coupleQuesAns2);
        coupleQuesAnsArrayList.add(coupleQuesAns3);
        coupleQuesAnsArrayList.add(coupleQuesAns4);
        coupleQuesAnsArrayList.add(coupleQuesAns5);
        coupleQuesAnsArrayList.add(coupleQuesAns6);
    }



}
