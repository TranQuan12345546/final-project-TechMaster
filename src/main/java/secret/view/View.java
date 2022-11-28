package secret.view;

import secret.entity.CoupleQuesAns;
import secret.logichandle.QuesAnsLogic;
import secret.logichandle.QuizLogic;

import java.util.Scanner;

import static secret.logichandle.QuesAnsLogic.checkNumberException;

public class View {
    public void showMenu() {
        System.out.println("-------JAVA INTERVIEW QUESTION PROGRAM--------");
        System.out.println("1. Add Question and Answer.");
        System.out.println("2. Show already existing Question and Answer.");
        System.out.println("3. Edit Question and Answer.");
        System.out.println("4. Search question.");
        System.out.println("5. Practice");
        System.out.println("6. Quiz.");
        System.out.println("7. Exit.");
        System.out.print("Choose: ");
    }
// comparable comparator generic
    Scanner sc = new Scanner(System.in);
    public void chooseMenu(QuesAnsLogic quesAnsLogic, CoupleQuesAns coupleQuesAns) {
        int choice = checkNumberException(sc, 1, 7);
        switch (choice) {
            case 1:
                quesAnsLogic.addQuesAns(sc, coupleQuesAns);
                break;
            case 2:
                quesAnsLogic.showCouple();
                break;
            case 3:
                quesAnsLogic.editQuesAns(sc);
                break;
            case 4:
                quesAnsLogic.findQuestion(sc);
                break;
            case 5:
                break;
            case 6:
                QuizLogic quizLogic = new QuizLogic();
                quizLogic.quizLogic(sc);
                break;
            case 7:
                System.exit(0);
        }
    }

    public void viewTopic() {
        System.out.println("Choose Topic: ");
        System.out.println("1. Java Core");
        System.out.println("2. OOP - Object Oriented");
        System.out.println("3. Java Threads");
        System.out.println("4. Java Collections");
        System.out.println("5. Exception");
    }

    public void showQuizz(String[] answers) {
        System.out.println("Choose 1 of the following answers");
        System.out.println("1. " + answers[0]);
        System.out.println("2. " + answers[1]);
        System.out.println("3. " + answers[2]);
        System.out.println("4. " + answers[3]);
        System.out.print("Your answer is: ");
    }

    public void quizForeword() {
        System.out.println("Welcome to Quiz Test.");
        System.out.println("Think carefully before choosing the answer!");
    }

    public void askForNextQuestion() {
        System.out.println("Would you like to try another question?");
        System.out.println("1. Yes");
        System.out.println("2. No");
    }
}
