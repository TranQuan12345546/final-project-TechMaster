package finalproject.logichandle;

import finalproject.constant.StatusValue;
import finalproject.entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static finalproject.main.Main.*;
import static finalproject.main.Main.quesAnsDetailArrayList;

public class GeneralLogic {
    public void chooseMenu(Scanner sc, QuesAnsLogic quesAnsLogic, Account account) throws IOException {
        GeneralLogic generalLogic = new GeneralLogic();
        int choice = view.checkNumberException(sc, 1, 10);
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
                generalLogic.changeMode(sc);
                break;
            case 9:
                learningStatus(sc);
                break;
            case 10:
                generalLogic.writeData();
                System.exit(0);
        }
    }

    private void learningStatus(Scanner sc) {
        view.menuStatus();
        int choose = view.checkNumberException(sc, 1, 2);
        if (choose == 1) {
            for (QuesAnsDetail i : quesAnsDetailArrayList) {
                System.out.println(i.getQuestion() + " Status: " + i.getStatus());
            }
        } else {
            System.out.println("Enter the question id you want to change the status : ");
            boolean flag = true;
            int id;
            do {
                try {
                    id = Integer.parseInt(sc.nextLine());
                    for (QuesAnsDetail i : quesAnsDetailArrayList) {
                        if (i.getId() == id) {
                            if(i.getStatus().equals(StatusValue.MEMORIZED.value)) {
                                i.setStatus(StatusValue.NOT_MEMORIZED.value);
                            } else {
                                i.setStatus(StatusValue.MEMORIZED.value);
                            }
                            flag = false;
                        }
                    }
                    if (flag) {
                        System.out.println("No questions with id " + id);
                    }
                } catch (Exception e) {
                    System.out.println("You need to enter a number. ");
                }
            } while (flag);
        }

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

    public void changeMode(Scanner sc) throws IOException {
        writeData();
        new Question(1);
        new Answer(1);
        new QuesAnsDetail(1);
        quesAnsDetailArrayList.clear();
        mode(sc);
    }

    public void writeData() throws IOException {
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
}
