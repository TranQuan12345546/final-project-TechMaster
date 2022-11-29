package finalproject.main;


import finalproject.entity.*;
import finalproject.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<QuesAnsDetail> quesAnsDetailArrayList = new ArrayList<>();
    public static ArrayList<Account> accounts = new ArrayList<>();
    public static View view = new View();
    public static boolean mode = true;

    public static void main(String[] args) throws IOException {
        ExcelWriterAccount excelWriterAccount = new ExcelWriterAccount();
        accounts = (ArrayList<Account>) excelWriterAccount.readExcel(accounts);
        Scanner sc = new Scanner(System.in);
        view.mode(sc);
        view.mainMenu(sc);
    }
}
