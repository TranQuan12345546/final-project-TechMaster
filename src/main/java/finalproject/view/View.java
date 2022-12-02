package finalproject.view;


import finalproject.entity.*;
import finalproject.logichandle.AccountLogic;
import finalproject.logichandle.QuesAnsLogic;
import finalproject.logichandle.QuizLogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static finalproject.main.Main.*;


public class View {
    public void mainMenu(Scanner sc) throws IOException {
        AccountLogic accountLogic = new AccountLogic();
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
        System.out.println("5. Return to main menu");
    }

    public void showMenu() {
        if (mode) {
            System.out.println("-------JAVA INTERVIEW QUESTION PROGRAM--------");
        } else {
            System.out.println("-----------ENGLISH QUESTION PROGRAM-----------");
        }

        System.out.println("1. Add Question and Answer.");
        System.out.println("2. Show already existing Question and Answer.");
        System.out.println("3. Edit Question and Answer.");
        System.out.println("4. Search question.");
        System.out.println("5. Practice.");
        System.out.println("6. Quiz.");
        System.out.println("7. Account setting.");
        System.out.println("8. Change mode.");
        System.out.println("9. Exit.");
        System.out.print("Choose: ");
    }

    public void chooseMenu(Scanner sc, QuesAnsLogic quesAnsLogic, Account account) throws IOException {
        int choice = checkNumberException(sc, 1, 9);
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
                quesAnsLogic.practice(sc);
                break;
            case 6:
                QuizLogic quizLogic = new QuizLogic();
                quizLogic.quizLogic(sc);
                break;
            case 7:
                settingAccount(sc, account);
                break;
            case 8:
                changeMode(sc);
                break;
            case 9:
                writeData();
                System.exit(0);
        }
    }

    private void settingAccount(Scanner sc, Account account) throws IOException {
        AccountLogic accountLogic = new AccountLogic();
        boolean flag = true;
        while (flag) {
            view.LoginView();
            int choice7 = view.checkNumberException(sc, 1, 5);
            switch (choice7) {
                case 1: accountLogic.changeUsername(sc, account);
                    break;
                case 2: accountLogic.changeEmail(sc, account);
                    break;
                case 3: accountLogic.changePassword(sc, account);
                    break;
                case 4:
                    view.mainMenu(sc);
                    break;
                case 5:
                    ExcelWriterAccount excelWriterAccount = new ExcelWriterAccount();
                    excelWriterAccount.writeExcel(accounts);
                    flag = false;
                    break;
            }
        }
    }

    private void writeData() throws IOException {
        ExcelWriterAccount excelWriterAccount = new ExcelWriterAccount();
        excelWriterAccount.writeExcel(accounts);
        if (mode) {
            ExcelWriterJava excelWriterJava = new ExcelWriterJava();
            excelWriterJava.writeExcel(quesAnsDetailArrayList);
        } else {
            ExcelWriterEnglish excelWriterEnglish = new ExcelWriterEnglish();
            excelWriterEnglish.writeExcel(quesAnsDetailArrayList);
        }
    }

    private void changeMode(Scanner sc) throws IOException {
        writeData();
        new Question(1);
        new Answer(1);
        new QuesAnsDetail(1);
        quesAnsDetailArrayList.clear();
        mode(sc);
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
        System.out.println("------------Welcome to Quiz Test-----------");
        System.out.println("Think carefully before choosing the answer!");
    }

    public void askForNextQuestion() {
        System.out.println("Would you like to try another question?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Choose: ");
    }

    public int checkNumberException(Scanner sc, int firstNumber, int lastNumber) {
        int choose = -1;
        do {
            try {
                choose = Integer.parseInt(sc.nextLine());
                if (choose < firstNumber || choose > lastNumber) {
                    System.out.println("Invalid selection, please choose again");
                }
            } catch (Exception e ) {
                System.out.println("You need to enter a number.");
            }
        } while (choose < firstNumber || choose > lastNumber);
        return choose;
    }

    public void mode(Scanner sc) throws IOException {
        System.out.println("1. Java Mode");
        System.out.println("2. English Mode");
        int choose = view.checkNumberException(sc, 1, 2);
        if (choose == 1) {
            mode = true;
            ExcelWriterJava excelWriterJava = new ExcelWriterJava();
            quesAnsDetailArrayList = (ArrayList<QuesAnsDetail>) excelWriterJava.readExcel(quesAnsDetailArrayList);
        } else {
            mode = false;
            ExcelWriterEnglish excelWriterEnglish = new ExcelWriterEnglish();
            quesAnsDetailArrayList = (ArrayList<QuesAnsDetail>) excelWriterEnglish.readExcel(quesAnsDetailArrayList);
        }
    }

    public void viewPractice() {
        System.out.println("Choose Topic to Practice: ");
        System.out.println("1. Java Core");
        System.out.println("2. OOP - Object Oriented");
        System.out.println("3. Java Threads");
        System.out.println("4. Java Collections");
        System.out.println("5. Exception");
        System.out.println("6. All topic");
        System.out.println("Choose: ");
    }
}
