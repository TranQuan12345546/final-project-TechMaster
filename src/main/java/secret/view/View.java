package secret.view;


import secret.entity.Account;
import secret.entity.ExcelWriterAccount;
import secret.entity.ExcelWriterQuestion;
import secret.logichandle.AccountLogic;
import secret.logichandle.QuesAnsLogic;
import secret.logichandle.QuizLogic;

import java.io.IOException;
import java.util.Scanner;

import static secret.main.Main.*;


public class View {
    public void mainMenu() throws IOException {
        Scanner sc = new Scanner(System.in);

        AccountLogic accountLogic = new AccountLogic();
//        accountLogic.inputDefaultAccount();
        while (true) {
            showMenuLogin();
            int choose = checkNumberException(sc, 1, 2);
            if (choose == 1) {
                accountLogic.register(sc);
            }
            if (choose == 2) {
                accountLogic.logIn(sc);
            }
        }
    }

    public void showMenuLogin() {
        System.out.println("Welcome to the program, please choose: ");
        System.out.println("1. Register");
        System.out.println("2. Log in");
    }



    public void LoginView() {
        System.out.println("1. Change username.");
        System.out.println("2. Change email.");
        System.out.println("3. Change password.");
        System.out.println("4. Log out.");
        System.out.println("0. Exit");
    }

    public void showMenu() {
        System.out.println("-------JAVA INTERVIEW QUESTION PROGRAM--------");
        System.out.println("1. Add Question and Answer.");
        System.out.println("2. Show already existing Question and Answer.");
        System.out.println("3. Edit Question and Answer.");
        System.out.println("4. Search question.");
        System.out.println("5. Practice");
        System.out.println("6. Quiz.");
        System.out.println("7. Account setting");
        System.out.println("8. Exit.");
        System.out.print("Choose: ");
    }

    public void chooseMenu(Scanner sc, QuesAnsLogic quesAnsLogic, Account account) throws IOException {
        int choice = checkNumberException(sc, 1, 8);
        switch (choice) {
            case 1:
                quesAnsLogic.addQuesAns(sc);
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
                AccountLogic accountLogic = new AccountLogic();
                while (true) {
                    view.LoginView();
                    accountLogic.menuLogin(sc, account);
                }
            case 8:
                ExcelWriterAccount excelWriterAccount = new ExcelWriterAccount();
                excelWriterAccount.writeExcel(accounts);
                ExcelWriterQuestion excelWriterQuestion = new ExcelWriterQuestion();
                excelWriterQuestion.writeExcel(quesAnsDetailArrayList);
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

    public void showQuiz(String[] answers) {
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

    public int checkNumberException(Scanner sc, int firstNumber, int lastNumber) {
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

}
