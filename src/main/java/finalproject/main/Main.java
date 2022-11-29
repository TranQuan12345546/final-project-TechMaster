package finalproject.main;


import finalproject.entity.Account;
import finalproject.entity.ExcelWriterAccount;
import finalproject.entity.ExcelWriterQuestion;
import finalproject.entity.QuesAnsDetail;
import finalproject.view.View;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static ArrayList<QuesAnsDetail> quesAnsDetailArrayList = new ArrayList<>();
    public static ArrayList<Account> accounts = new ArrayList<>();
    public static View view = new View();

    public static void main(String[] args) throws IOException {
        ExcelWriterAccount excelWriterAccount = new ExcelWriterAccount();
        accounts = (ArrayList<Account>) excelWriterAccount.readExcel(accounts);
        ExcelWriterQuestion excelWriterQuestion = new ExcelWriterQuestion();
        quesAnsDetailArrayList = (ArrayList<QuesAnsDetail>) excelWriterQuestion.readExcel(quesAnsDetailArrayList);
        view.mainMenu();
    }
}